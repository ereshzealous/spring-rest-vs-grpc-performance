package com.rest.grpc.performance.user.service;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created on 28/December/2020 By Author Eresh, Gorantla
 **/
@Service
public class UserDetailsRestService {

	@Value("${rest.UserDetailsService.service.endpoint}")
	private String baseUrl;

	private WebClient webClient;

	@PostConstruct
	private void init(){
		this.webClient = WebClient.builder().baseUrl(baseUrl).build();
	}

	private AtomicInteger ID_GENERATOR = new AtomicInteger();

	public Flux<Object> generateUsers(Integer range) {
		return Flux.range(1, range)
		           .flatMap(i -> this.webClient.get()
		                           .uri("/api/rest/users")
		                           .retrieve()
		                           .bodyToMono(Object.class)
		                           .map(o -> (Object) Map.of(UUID.randomUUID().toString(), o)))
				.subscribeOn(Schedulers.boundedElastic());
	}
}