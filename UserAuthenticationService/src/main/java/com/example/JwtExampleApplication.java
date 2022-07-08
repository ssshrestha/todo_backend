package com.example;

import com.example.filter.JwtFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;

import javax.servlet.FilterRegistration;

@EnableHystrix
@SpringBootApplication
public class JwtExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtExampleApplication.class, args);
	}

//	@Bean
//	public FilterRegistrationBean jwtFilter(){
//		FilterRegistrationBean filterBean=new FilterRegistrationBean();
//		filterBean.setFilter(new JwtFilter());
//		filterBean.addUrlPatterns("/api/v1/*");
//		return filterBean;
//	}

}
