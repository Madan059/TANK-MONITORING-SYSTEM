package com.madan.model;


import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class SensorReading {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double level;
    private String status;        // NORMAL / LOW / CRITICAL / SENSOR_ERROR
    private String motorStatus;   // ON / OFF
    private String deviceStatus;  // ACTIVE / INACTIVE
    private LocalDateTime timestamp;

    // Getters & Setters
    public Long getId() { return id; }

    public double getLevel() { return level; }
    public void setLevel(double level) { this.level = level; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getMotorStatus() { return motorStatus; }
    public void setMotorStatus(String motorStatus) { this.motorStatus = motorStatus; }

    public String getDeviceStatus() { return deviceStatus; }
    public void setDeviceStatus(String deviceStatus) { this.deviceStatus = deviceStatus; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}
