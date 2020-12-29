package com.rest.grpc.performance.user.service;

import com.rest.grpc.performance.user.model.UserDetails;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created on 28/December/2020 By Author Eresh, Gorantla
 **/
@Service
public class UserDetailsService {

	private AtomicInteger ID_GENERATOR = new AtomicInteger();

	public UserDetails generateUsers() {
		return generateUserDetails();
	}

	private UserDetails generateUserDetails() {
		UserDetails userDetails = new UserDetails();
		userDetails.setCity(RandomStringUtils.randomAlphabetic(10));
		userDetails.setId(UUID.randomUUID().toString());
		userDetails.setLastName(RandomStringUtils.randomAlphabetic(10));
		userDetails.setNumericId(ID_GENERATOR.getAndIncrement());
		userDetails.setFirstName(RandomStringUtils.randomAlphabetic(10));
		return userDetails;
	}
}