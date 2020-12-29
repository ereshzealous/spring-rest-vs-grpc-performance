package com.rest.grpc.performance.user.controller;

import com.rest.grpc.performance.user.model.UserDetails;
import com.rest.grpc.performance.user.service.UserDetailsService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 28/December/2020 By Author Eresh, Gorantla
 **/
@RestController
@RequestMapping("/api")
public class UserDetailsController {

	final UserDetailsService userDetailsService;

	public UserDetailsController(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@GetMapping("/rest/users")
	public UserDetails generateUserDetails() {
		return this.userDetailsService.generateUsers();
	}
}