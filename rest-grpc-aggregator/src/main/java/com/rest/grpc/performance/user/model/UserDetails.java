package com.rest.grpc.performance.user.model;

import com.userdetails.model.UserDetailsResponse;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created on 28/December/2020 By Author Eresh, Gorantla
 **/
@Data
@Getter
@Setter
@NoArgsConstructor
public class UserDetails {
	private String id;
	private Integer numericId;
	private String firstName;
	private String lastName;
	private String city;

	public UserDetails(UserDetailsResponse response) {
		this.firstName = response.getFirstName();
		this.lastName = response.getLastName();
		this.city = response.getCity();
		this.id = response.getId();
		this.numericId = response.getNumericId();
	}
}