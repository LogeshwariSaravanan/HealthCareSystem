package com.capgemini.healthcaresystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.capgemini.healthcaresystem.service.AppointmentService;

@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {
	@Autowired
	AppointmentService appointmentService;
	


}
