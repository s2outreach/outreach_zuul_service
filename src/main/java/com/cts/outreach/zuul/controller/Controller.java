package com.cts.outreach.zuul.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.outreach.zuul.service.ZuulService;

@RestController
public class Controller {
	
	@Autowired
	public ZuulService zuulService;
	
	@PostMapping("authserver/register")
	public String authRegister() {
		return zuulService.authRegister();
	}
	@PostMapping("authserver/authenticate")
	public String authAuthenticate() {
		return zuulService.authAuthenticate();
	}
	@GetMapping("eventserver/getUserCount")
	public String getUserCount() {
		return zuulService.getUserCount();
	}
	@GetMapping("eventserver/addEvent")
	public String addEvent() {
		return zuulService.addEvent();
	}
	@GetMapping("eventserver/getAllEvents")
	public String getAllEvents() {
		return zuulService.getAllEvents();
	}
	@GetMapping("eventserver/addUserForEvent")
	public String addUserForEvent() {
		return zuulService.addUserForEvent();
	}
	@GetMapping("eventserver/getEventReport")
	public String getEventReport() {
		return zuulService.getEventReport();
	}
	@GetMapping("eventserver/getUserReport")
	public String getUserReport() {
		return zuulService.getUserReport();
	}
	@GetMapping("eventserver/getEventsForUser")
	public String getEventsForUser() {
		return zuulService.getEventsForUser();
	}

}
