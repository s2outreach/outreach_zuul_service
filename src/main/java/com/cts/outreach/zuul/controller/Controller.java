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
	@GetMapping("rideserver/admin")
	public String rideAdmin() {
		return zuulService.rideAdmin();
	}
	@GetMapping("rideserver/driver")
	public String rideDriver() {
		return zuulService.rideDriver();
	}
	@GetMapping("rideserver/rider")
	public String rideRider() {
		return zuulService.rideRider();
	}
	@PostMapping("driverserver/updateride")
	public String driverUpdateRide() {
		return zuulService.driverUpdateRide();
	}
	@PostMapping("riderserver/addride")
	public String riderAddRide() {
		return zuulService.riderAddRide();
	}
	@PostMapping("riderserver/cancelride")
	public String riderCancelRide() {
		return zuulService.riderCancelRide();
	}

}
