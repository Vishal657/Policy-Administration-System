package com.cts.consumer_microservice.response;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConsumerBusinessDetails {
	
	@NotBlank
	@Size(max = 20)
	private String name;
	
	@NotBlank
	@Size(max = 20)
	private String dob;
	@NotBlank
	@Size(max = 30)
	private String businessName;
	@NotBlank
	@Size(max = 12)
	private String panDetails;
	@NotBlank
	@Size(max = 50)
	private String email;
	@NotBlank
	@Size(max = 10)
	private String phone;
	
	@NotBlank
	@Size(max = 150)
	private String businessOverview;
	@NotBlank
	@Size(max = 30)
	private String validity;
	@NotBlank
	@Size(max = 50)
	private String agentName;
	@NotNull
	private Long agentId;
	@NotNull
	private Long businessId;
	@NotNull
	private Long consumerId;
	@NotBlank
	@Size(max = 40)
	private String businessCategory;
	@NotBlank
	@Size(max = 40)
	private String businessType;
	@NotNull
	private Long businessTurnover;
	@NotNull
	private Long capitalInvested;
	@NotNull
	private Long totalEmployees;
	@NotNull
	private Long businessValue;
	@NotNull
	private Long businessAge;

}
