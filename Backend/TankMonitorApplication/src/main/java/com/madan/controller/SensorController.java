package com.madan.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.madan.model.SensorReading;
import com.madan.service.SensorService;

@RestController
@CrossOrigin(origins = "http://localhost:5173") 

public class SensorController {
	
	   @Autowired
	    private SensorService service;

	    @PostMapping("/reading")
	    public SensorReading addReading(@RequestBody Map<String, Double> req) {
	        return service.processReading(req.get("level"));
	    }

	    @GetMapping("/latest")
	    public SensorReading getLatest() {
	        return service.getLatest();
	    }

}
