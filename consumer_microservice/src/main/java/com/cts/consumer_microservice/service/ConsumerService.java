package com.cts.consumer_microservice.service;

import com.cts.consumer_microservice.request.ConsumerBusinessRequest;
import com.cts.consumer_microservice.response.ConsumerBusinessDetails;

public interface ConsumerService {

	String createConsumerBusiness(ConsumerBusinessRequest consumerBusinessRequest);
	
	Long calculateBusinessValue(Long businessTurnover,Long capitalInvested);
	String updateConsumerBusiness(ConsumerBusinessDetails consumerBusinessDetails);
	public ConsumerBusinessDetails viewConsumerBusiness(Long consumerid);

}
