package com.cts.outreach.zuul;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.cloud.netflix.eureka.EurekaInstanceConfigBean;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.AmazonInfo;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@EnableZuulProxy
@EnableCircuitBreaker
@EnableDiscoveryClient
//@EnableHystrix
public class OutreachZuulServiceApplication {
	
	private Logger LOGGER = LoggerFactory.getLogger(OutreachZuulServiceApplication.class);
	
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(OutreachZuulServiceApplication.class, args);
	}
	
	@Primary
	@Bean
	@Autowired
	@Profile("aws")
	public EurekaInstanceConfigBean eurekaInstanceConfig(InetUtils inetUtils) {
	    EurekaInstanceConfigBean config = new EurekaInstanceConfigBean(inetUtils);
	    AmazonInfo info = AmazonInfo.Builder.newBuilder().autoBuild("eureka");
	    config.setHostname(info.get(AmazonInfo.MetaDataKey.publicHostname));
	    config.setIpAddress(info.get(AmazonInfo.MetaDataKey.publicIpv4));
	    config.setNonSecurePort(8080);
	    config.setDataCenterInfo(info);
	    LOGGER.info(info.get(AmazonInfo.MetaDataKey.publicIpv4));
	    LOGGER.info(info.get(AmazonInfo.MetaDataKey.publicHostname));
	    return config;
	   }

}
