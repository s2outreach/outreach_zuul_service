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
	
	private RestTemplate restTemplate;
	
	@Autowired
    public ZuulService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

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
		return restTemplate.getForEntity("http://eventserver/v1/getUserCount", String.class).getBody();
	}
	@HystrixCommand(fallbackMethod = "failure")
	public String addEvent() {
		LOGGER.info("Add event requested");
		return restTemplate.getForEntity("http://eventserver/v1/addEvent", String.class).getBody();
	}
	@HystrixCommand(fallbackMethod = "failure")
	public String getAllEvents() {
		LOGGER.info("All events requested");
		return restTemplate.getForEntity("http://eventserver/v1/getAllEvents", String.class).getBody();
	}
	@HystrixCommand(fallbackMethod = "failure")
	public String addUserForEvent() {
		LOGGER.info("Add  user for a event requested");
		return restTemplate.getForEntity("http://eventserver/v1/addUserForEvent", String.class).getBody();
	}
	@HystrixCommand(fallbackMethod = "failure")
	public String getEventReport() {
		LOGGER.info("Events report requested");
		return restTemplate.getForEntity("http://eventserver/v1/getEventReport", String.class).getBody();
	}
	@HystrixCommand(fallbackMethod = "failure")
	public String getUserReport() {
		LOGGER.info("Users report requested");
		return restTemplate.getForEntity("http://eventserver/v1/getUserReport", String.class).getBody();
	}
	@HystrixCommand(fallbackMethod = "failure")
	public String getEventsForUser() {
		LOGGER.info("Events for a user requested");
		return restTemplate.getForEntity("http://eventserver/v1/getEventsForUser", String.class).getBody();
	}
	@HystrixCommand(fallbackMethod = "failure")
	public String sendNewEventMail() {
		LOGGER.info("New event mail requested");
		return restTemplate.getForEntity("http://emailserver/v1/sendNewEventMail", String.class).getBody();
	}
	@HystrixCommand(fallbackMethod = "failure")
	public String sendReportInsightMail() {
		LOGGER.info("Report mail requested");
		return restTemplate.getForEntity("http://emailserver/v1/sendReportInsightMail", String.class).getBody();
	}
	@HystrixCommand(fallbackMethod = "failure")
	public String getAllLogs() {
		LOGGER.info("Log requested");
		return restTemplate.getForEntity("http://logserver/v1/getAllLogs", String.class).getBody();
	}
	
	public String failure() {
		return "Service unavailble";
	}
}
