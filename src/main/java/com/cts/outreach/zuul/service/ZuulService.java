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
	public String getUserCount() {
		LOGGER.info("User Count requested");
		return restTemplate.getForEntity("http://eventserver/getUserCount", String.class).getBody();
	}
	@HystrixCommand(fallbackMethod = "failure")
	public String addEvent() {
		LOGGER.info("Add event requested");
		return restTemplate.getForEntity("http://eventserver/addEvent", String.class).getBody();
	}
	@HystrixCommand(fallbackMethod = "failure")
	public String getAllEvents() {
		LOGGER.info("All events requested");
		return restTemplate.getForEntity("http://eventserver/getAllEvents", String.class).getBody();
	}
	@HystrixCommand(fallbackMethod = "failure")
	public String addUserForEvent() {
		LOGGER.info("Add  user for a event requested");
		return restTemplate.getForEntity("http://eventserver/addUserForEvent", String.class).getBody();
	}
	@HystrixCommand(fallbackMethod = "failure")
	public String getEventReport() {
		LOGGER.info("Events report requested");
		return restTemplate.getForEntity("http://eventserver/getEventReport", String.class).getBody();
	}
	@HystrixCommand(fallbackMethod = "failure")
	public String getUserReport() {
		LOGGER.info("Users report requested");
		return restTemplate.getForEntity("http://eventserver/getUserReport", String.class).getBody();
	}
	@HystrixCommand(fallbackMethod = "failure")
	public String getEventsForUser() {
		LOGGER.info("Events for a user requested");
		return restTemplate.getForEntity("http://eventserver/getEventsForUser", String.class).getBody();
	}
	
	public String failure() {
		return "Service unavailble";
	}
}
