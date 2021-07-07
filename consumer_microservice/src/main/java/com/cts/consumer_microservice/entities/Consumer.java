package com.cts.consumer_microservice.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Consumer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Consumer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="ID")
	private Long id;
	
	
	@NotBlank
	@Size(max = 20)
	@Column(name = "Name")
	private String name;
	
	@NotBlank
	@Size(max = 20)
	@Column(name = "DOB")
	private String dob;
	
	@NotBlank
	@Size(max = 30)
	@Column(name = "Business_Name")
	private String businessName;
	
	@NotBlank
	@Size(max = 12)
	@Column(name = "PAN_Details")
	private String panDetails;
	
	@NotBlank
	@Size(max = 50)
	@Column(name = "Email")
	private String email;
	
	@NotBlank
	@Size(max = 10)
	@Column(name = "Phone")
	private String phone;
	
	
	@NotBlank
	@Size(max = 150)
	@Column(name = "Business_Overview")
	private String businessOverview;
	
	@NotBlank
	@Size(max = 30)
	@Column(name = "Validity_of_Consumer")
	private String validity;
	
	@NotBlank
	@Size(max = 50)
	@Column(name = "Agent_Name")
	private String agentName;
	
	@NotNull
	@Column(name = "Agent_ID")
	private Long agentId;

	public Consumer(@NotBlank @Size(max = 50) String name,
			@NotBlank @Size(max = 20) String dob, @NotBlank @Size(max = 50) String businessName,
			@NotBlank @Size(max = 12) String panDetails, @NotBlank @Size(max = 50) String email,
			@NotBlank @Size(max = 10) String phone,
			@NotBlank @Size(max = 150) String businessOverview, @NotBlank @Size(max = 30) String validity,
			@NotBlank @Size(max = 50) String agentName, @NotNull Long agentId) {
		super();
		this.name = name;
		this.dob = dob;
		this.businessName = businessName;
		this.panDetails = panDetails;
		this.email = email;
		this.phone = phone;
		this.businessOverview = businessOverview;
		this.validity = validity;
		this.agentName = agentName;
		this.agentId = agentId;
	}
	
}

