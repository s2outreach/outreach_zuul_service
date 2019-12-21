package com.cts.outreach.zuul.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class ZuulService {
	
	private Logger LOGGER = LoggerFactory.getLogger(ZuulService.class);
	
	@Autowired
	private RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "failure")
	public String authRegister() {
		LOGGER.info("register requested");
		return restTemplate.getForEntity("http://authserver/register", String.class).getBody();
	}	
	@HystrixCommand(fallbackMethod = "failure")
	public String authAuthenticate() {
		LOGGER.info("Auth requested");
		return restTemplate.getForEntity("http://authserver/authenticate", String.class).getBody();
	}
	@HystrixCommand(fallbackMethod = "failure")
	public String rideAdmin() {
		LOGGER.info("all rides requested");
		return restTemplate.getForEntity("http://rideserver/admin", String.class).getBody();
	}
	@HystrixCommand(fallbackMethod = "failure")
	public String rideDriver() {
		LOGGER.info("driver rides requested");
		return restTemplate.getForEntity("http://rideserver/driver", String.class).getBody();
	}
	@HystrixCommand(fallbackMethod = "failure")
	public String rideRider() {
		LOGGER.info("rider rides requested");
		return restTemplate.getForEntity("http://rideserver/rider", String.class).getBody();
	}
	@HystrixCommand(fallbackMethod = "failure")
	public String driverUpdateRide() {
		LOGGER.info("driver update ride requested");
		return restTemplate.getForEntity("http://driverserver/updateride", String.class).getBody();
	}
	@HystrixCommand(fallbackMethod = "failure")
	public String riderAddRide() {
		LOGGER.info("rider add ride requested");
		return restTemplate.getForEntity("http://riderserver/addride", String.class).getBody();
	}
	@HystrixCommand(fallbackMethod = "failure")
	public String riderCancelRide() {
		LOGGER.info("rider cancel ride requested");
		return restTemplate.getForEntity("http://riderserver/cancelride", String.class).getBody();
	}
	public String failure() {
		return "Service unavailble. Please try after sometime";
	}
}
