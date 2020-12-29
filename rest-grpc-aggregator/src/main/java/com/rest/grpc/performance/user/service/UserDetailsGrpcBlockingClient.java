package com.rest.grpc.performance.user.service;

import com.rest.grpc.performance.user.model.UserDetails;
import com.userdetails.model.UserDetailsRequest;
import com.userdetails.model.UserDetailsResponse;
import com.userdetails.model.UserDetailsServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created on 28/December/2020 By Author Eresh, Gorantla
 **/
@Service
public class UserDetailsGrpcBlockingClient {

	@GrpcClient("UserDetailsService")
	UserDetailsServiceGrpc.UserDetailsServiceBlockingStub userDetailsServiceBlockingStub;

	public Flux<Object> getUserDetailsResponse(Integer range) {
		return
				Flux.range(1, range)
				    .map(i -> UserDetailsRequest.newBuilder()
				                                .setCity(RandomStringUtils.randomAlphabetic(10))
				                                .setLastName(RandomStringUtils.randomAlphabetic(10))
				                                .setFirstName(RandomStringUtils.randomAlphabetic(10))
				                                .build())
				    .map(i -> {
					    UserDetailsResponse response = this.userDetailsServiceBlockingStub.generateRandomUser(i);
					    return (Object) Map.of(response.getId(), new UserDetails(response));
				    })
				    .subscribeOn(Schedulers.boundedElastic());
	}
}