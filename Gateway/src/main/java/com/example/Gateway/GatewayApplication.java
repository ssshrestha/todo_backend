package com.example.Gateway;

import com.example.Gateway.filter.JwtFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.*;

@EnableEurekaClient
@SpringBootApplication
@Configuration
public class GatewayApplication {

	 public static void main(String[] args) {
		 SpringApplication.run(GatewayApplication.class, args); }

		 @Bean
		 public RouteLocator myRoutes (RouteLocatorBuilder builder){
			 return builder.routes()//http://localhost:9000/api/v1/, http://localhost:9000/api/v2/
				 .route(p -> p.path("/api/v1/**" )
						 .uri("lb://USER-AUTHENTICATION-SERVICE"))

		         .route(p -> p.path("/api/v2/**" )
						 .uri("lb://REGISTER"))

					 .route(p -> p.path("/api/v3/**" )
							 .uri("lb://TASK-SERVICE"))

					 .route(p -> p.path("/api/v4/**" )
							 .uri("lb://ARCHIVE-SERVICE"))

					 .route(p -> p.path("/api/v5/**" )
							 .uri("lb://ORGANIZATION-SERVICE"))
		         .build();
		 }

		@Bean
	public FilterRegistrationBean jwtFilter(){
		FilterRegistrationBean filterBean=new FilterRegistrationBean();
		filterBean.setFilter(new JwtFilter());
		filterBean.addUrlPatterns("/api/v1/*");
		return filterBean;
	}

	 }

//						 .uri("http://localhost:8082"))
//						 .uri("http://localhost:8085"))