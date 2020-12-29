package com.rest.grpc.performance.service;

import com.userdetails.model.UserDetailsRequest;
import com.userdetails.model.UserDetailsResponse;
import com.userdetails.model.UserDetailsServiceGrpc;
import io.grpc.stub.StreamObserver;

import org.apache.commons.lang3.RandomStringUtils;
import org.lognet.springboot.grpc.GRpcService;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created on 28/December/2020 By Author Eresh, Gorantla
 **/
@GRpcService
public class UserDetailsService extends UserDetailsServiceGrpc.UserDetailsServiceImplBase {

	private AtomicInteger ID_GENERATOR = new AtomicInteger();

	@Override
	public void generateRandomUser(UserDetailsRequest request, StreamObserver<UserDetailsResponse> responseObserver) {
		var city = request.getCity();
		UserDetailsResponse output = UserDetailsResponse.newBuilder()
		                                                .setCity(city)
		                                                .setId(UUID.randomUUID().toString())
		                                                .setNumericId(ID_GENERATOR.incrementAndGet())
		                                                .setFirstName(request.getFirstName())
		                                                .setLastName(request.getLastName())
		                                                .build();
		responseObserver.onNext(output);
		responseObserver.onCompleted();
	}

	@Override
	public StreamObserver<UserDetailsRequest> generateRandomUserStream(StreamObserver<UserDetailsResponse> responseObserver) {
		return new StreamObserver<UserDetailsRequest>() {
			@Override
			public void onNext(UserDetailsRequest input) {
				UserDetailsResponse output = UserDetailsResponse.newBuilder()
				                                                .setCity(RandomStringUtils.randomAlphabetic(10))
				                                                .setId(UUID.randomUUID().toString())
				                                                .setNumericId(ID_GENERATOR.incrementAndGet())
				                                                .setFirstName(RandomStringUtils.randomAlphabetic(10))
				                                                .setLastName(RandomStringUtils.randomAlphabetic(10))
				                                                .build();
				responseObserver.onNext(output);
			}

			@Override
			public void onError(Throwable throwable) {

			}

			@Override
			public void onCompleted() {
				responseObserver.onCompleted();
			}
		};
	}

}