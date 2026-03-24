package com.madan.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.madan.model.SensorReading;
import com.madan.repository.SensorRepository;

@Service
public class SensorService {

    @Autowired
    private SensorRepository repo;

    public SensorReading processReading(double level) {

        final int NORMAL_THRESHOLD = 70;
        final int CRITICAL_THRESHOLD = 30;

        // ✅ Step 1: Status
        String status;
        if (level > NORMAL_THRESHOLD) {
            status = "NORMAL";
        } else if (level > CRITICAL_THRESHOLD) {
            status = "LOW";
        } else {
            status = "CRITICAL";
        }

        List<SensorReading> lastReadings = repo.findTop5ByOrderByTimestampDesc();

        String deviceStatus = "ACTIVE";

        // ✅ Step 2: Motor Logic
        boolean motorOn = false;

        if (deviceStatus.equals("ACTIVE")) {

            // check last 2 readings < 30
            if (lastReadings.size() >= 2) {

                boolean last2Low = true;

                for (int i = 0; i < 2; i++) {
                    if (lastReadings.get(i).getLevel() >= CRITICAL_THRESHOLD) {
                        last2Low = false;
                        break;
                    }
                }

                if (last2Low && level < CRITICAL_THRESHOLD) {
                    motorOn = true;
                }
            }
        }

        // ✅ Motor OFF conditions
        if (level > NORMAL_THRESHOLD || deviceStatus.equals("INACTIVE")) {
            motorOn = false;
        }

        String motorStatus = motorOn ? "ON" : "OFF";

        // ✅ Step 3: Sensor Error (5 same values)
        if (lastReadings.size() >= 4) {

            boolean same = true;

            for (int i = 0; i < 4; i++) {
                if (lastReadings.get(i).getLevel() != level) {
                    same = false;
                    break;
                }
            }

            if (same) {
                status = "SENSOR_ERROR";
            }
        }

        // ✅ Step 4: Save
        SensorReading reading = new SensorReading();
        reading.setLevel(level);
        reading.setStatus(status);
        reading.setMotorStatus(motorStatus);
        reading.setDeviceStatus(deviceStatus);
        reading.setTimestamp(LocalDateTime.now());

        return repo.save(reading);
    }

    // ✅ Get latest reading
    public SensorReading getLatest() {
        List<SensorReading> list = repo.findTop5ByOrderByTimestampDesc();

        if (list.size() > 0) {
            return list.get(0);
        }

        return null;
    }
}