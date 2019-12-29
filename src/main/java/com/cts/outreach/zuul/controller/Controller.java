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
	@GetMapping("eventserver/v1/getUserCount")
	public String getUserCount() {
		return zuulService.getUserCount();
	}
	@GetMapping("eventserver/v1/addEvent")
	public String addEvent() {
		return zuulService.addEvent();
	}
	@GetMapping("eventserver/v1/getAllEvents")
	public String getAllEvents() {
		return zuulService.getAllEvents();
	}
	@GetMapping("eventserver/v1/addUserForEvent")
	public String addUserForEvent() {
		return zuulService.addUserForEvent();
	}
	@GetMapping("eventserver/v1/getEventReport")
	public String getEventReport() {
		return zuulService.getEventReport();
	}
	@GetMapping("eventserver/v1/getUserReport")
	public String getUserReport() {
		return zuulService.getUserReport();
	}
	@GetMapping("eventserver/v1/getEventsForUser")
	public String getEventsForUser() {
		return zuulService.getEventsForUser();
	}
	@GetMapping("emailserver/v1/sendNewEventMail")
	public String sendNewEventMail() {
		return zuulService.sendNewEventMail();
	}
	@GetMapping("emailserver/v1/sendReportInsightMail")
	public String sendReportInsightMail() {
		return zuulService.sendReportInsightMail();
	}
	@GetMapping("logserver/v1/getAllLogs")
	public String getAllLogs() {
		return zuulService.getAllLogs();
	}

}
