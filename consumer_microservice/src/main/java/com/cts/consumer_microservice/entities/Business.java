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
@Table(name = "Business")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Business {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="ID")
	private Long id;

	@NotNull
	@Column(name = "Consumer_ID")
	private Long consumerId;

	@NotBlank
	@Size(max = 40)
	@Column(name = "Business_Category")
	private String businessCategory;

	@NotBlank
	@Size(max = 40)
	@Column(name = "Business_Type")
	private String businessType;

	@NotNull
	@Column(name = "Business_Turnover")
	private Long businessTurnover;

	@NotNull
	@Column(name = "Capital_Invested ")
	private Long capitalInvested;

	@NotNull
	@Column(name = "Total_Employees")
	private Long totalEmployees;

	@NotNull
	@Column(name = "Business_Value")
	private Long businessValue;

	@NotNull
	@Column(name = "Business_Age")
	private Long businessAge;

	public Business(@NotNull Long consumerId, @NotBlank @Size(max = 40) String businessCategory,
			@NotBlank @Size(max = 40) String businessType, @NotNull Long businessTurnover,
			@NotNull Long capitalInvested, @NotNull Long totalEmployees, @NotNull Long businessValue,
			@NotNull Long businessAge) {
		super();
		this.consumerId = consumerId;
		this.businessCategory = businessCategory;
		this.businessType = businessType;
		this.businessTurnover = businessTurnover;
		this.capitalInvested = capitalInvested;
		this.totalEmployees = totalEmployees;
		this.businessValue = businessValue;
		this.businessAge = businessAge;
	}



}
