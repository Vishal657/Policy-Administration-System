package com.cts.consumer_microservice.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cts.consumer_microservice.repository.BusinessRepository;
import com.cts.consumer_microservice.repository.ConsumerRepository;
import com.cts.consumer_microservice.request.ConsumerBusinessRequest;
import com.cts.consumer_microservice.response.ConsumerBusinessDetails;
import com.cts.consumer_microservice.service.ConsumerService;

@RestController
public class ConsumerController {
	@Autowired
	private BusinessRepository businessRepository;

	@Autowired
	private ConsumerRepository consumerRepository;

	@Autowired
	private ConsumerService consumerService;
	@PostMapping("/createConsumerBusiness")
	public String createConsumerBusiness(@Valid @RequestBody ConsumerBusinessRequest consumerBusinessRequest) throws Exception {
		
		String res = consumerService.createConsumerBusiness(consumerBusinessRequest);
		
		return res;
	}
	
	@PostMapping("/updateConsumerBusiness")
	public String updateConsumerBusiness(@Valid @RequestBody ConsumerBusinessDetails consumerBusinessDetails) {
		if (!consumerRepository.existsById(consumerBusinessDetails.getConsumerId())) {
			return "Sorry!!, No Consumer Found!!";
		}
		if (!businessRepository.existsByConsumerId(consumerBusinessDetails.getConsumerId())) {
			return "Sorry!!, No Business Found!!";
		}
		if (!businessRepository.existsById(consumerBusinessDetails.getBusinessId())) {
			return "Sorry!!, No Business Found!!";
		}   
		String res = consumerService.updateConsumerBusiness(consumerBusinessDetails);
	
		return res;
	}
	
	@GetMapping("/viewConsumerBusiness")
	public ConsumerBusinessDetails viewConsumerBusiness(@Valid @RequestParam("consumerId") Long consumerid) {
		
		return consumerService.viewConsumerBusiness(consumerid);
	}
}
