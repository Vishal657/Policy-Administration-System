package com.cts.consumer_microservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.consumer_microservice.entities.Business;
import com.cts.consumer_microservice.entities.Consumer;
import com.cts.consumer_microservice.repository.BusinessRepository;
import com.cts.consumer_microservice.repository.ConsumerRepository;
import com.cts.consumer_microservice.request.ConsumerBusinessRequest;
import com.cts.consumer_microservice.response.ConsumerBusinessDetails;

@Service
public class ConsumerServiceImpl implements ConsumerService {

	@Autowired
	ConsumerRepository consumerRepository;

	@Autowired
	BusinessRepository businessRepository;

	@Override
	public String createConsumerBusiness(ConsumerBusinessRequest consumerBusinessRequest) {
		// TODO Auto-generated method stub

		Consumer consumer = new Consumer(consumerBusinessRequest.getName(), consumerBusinessRequest.getDob(),
				consumerBusinessRequest.getBusinessName(), consumerBusinessRequest.getPanDetails(),
				consumerBusinessRequest.getEmail(), consumerBusinessRequest.getPhone(),
				consumerBusinessRequest.getBusinessOverview(), consumerBusinessRequest.getValidity(),
				consumerBusinessRequest.getAgentName(), consumerBusinessRequest.getAgentId());
		Consumer consumersave = consumerRepository.save(consumer);
		Long businessvalue = calculateBusinessValue(consumerBusinessRequest.getBusinessTurnover(),
				consumerBusinessRequest.getCapitalInvested());
		Business business = new Business(consumersave.getId(), consumerBusinessRequest.getBusinessCategory(),
				consumerBusinessRequest.getBusinessType(), consumerBusinessRequest.getBusinessTurnover(),
				consumerBusinessRequest.getCapitalInvested(), consumerBusinessRequest.getTotalEmployees(),
				businessvalue, consumerBusinessRequest.getBusinessAge());
		Business businesssave = businessRepository.save(business);
		return "Successfull";

	}

	@Override
	public Long calculateBusinessValue(Long businessturnover, Long capitalinvested) {
		// TODO Auto-generated method stub
		if ((businessturnover == capitalinvested) || businessturnover == 0 || capitalinvested == 0) {
			throw new NullPointerException("NullPointerException in CalBusinessValue");
		}

		return 20l;
	}

	@Override
	public String updateConsumerBusiness(ConsumerBusinessDetails consumerBusinessDetails)
	/* throws BusinessPropertyNotFoundException */ {
		Optional<Consumer> consumer = consumerRepository.findById(consumerBusinessDetails.getConsumerId());
		Consumer consumers = consumer.get();

		Business business = businessRepository.findByConsumerId(consumerBusinessDetails.getConsumerId());
		consumers.setName(consumerBusinessDetails.getName());
		consumers.setDob(consumerBusinessDetails.getDob());
		consumers.setBusinessName(consumerBusinessDetails.getBusinessName());
		consumers.setPanDetails(consumerBusinessDetails.getPanDetails());
		consumers.setEmail(consumerBusinessDetails.getEmail());
		consumers.setPhone(consumerBusinessDetails.getPhone());
		consumers.setBusinessName(consumerBusinessDetails.getBusinessName());
		consumers.setBusinessOverview(consumerBusinessDetails.getBusinessOverview());
		consumers.setValidity(consumerBusinessDetails.getValidity());
		consumers.setAgentName(consumerBusinessDetails.getAgentName());
		consumers.setAgentId(consumerBusinessDetails.getAgentId());

		Consumer consumersave = consumerRepository.save(consumers);
		business.setBusinessCategory(consumerBusinessDetails.getBusinessCategory());
		business.setBusinessType(consumerBusinessDetails.getBusinessType());
		business.setBusinessTurnover(consumerBusinessDetails.getBusinessTurnover());
		business.setCapitalInvested(consumerBusinessDetails.getCapitalInvested());
		business.setTotalEmployees(consumerBusinessDetails.getTotalEmployees());
		business.setBusinessValue(calculateBusinessValue(business.getBusinessTurnover(), business.getCapitalInvested()));
		business.setBusinessAge(consumerBusinessDetails.getBusinessAge());

		Business businesssave = businessRepository.save(business);

		return "Updated";
	}
	
	@Override
	public ConsumerBusinessDetails viewConsumerBusiness(Long consumerid) //throws ConsumerBusinessNotFoundException
	{
		Optional<Consumer> consumer = consumerRepository.findById(consumerid);
		Consumer consumers = consumer.get();
		Business business = businessRepository.findByConsumerId(consumerid);
		ConsumerBusinessDetails consumerBusinessDetails = new ConsumerBusinessDetails(consumers.getName(),
				 consumers.getDob(), consumers.getBusinessName(), consumers.getPanDetails(),
				consumers.getEmail(), consumers.getPhone(), consumers.getBusinessOverview(),
				consumers.getValidity(), consumers.getAgentName(), consumers.getAgentId(), business.getId(),
				business.getConsumerId(), business.getBusinessCategory(), business.getBusinessType(),
				business.getBusinessTurnover(), business.getCapitalInvested(), business.getTotalEmployees(),
				business.getBusinessValue(), business.getBusinessAge()

		);
		return consumerBusinessDetails;
	}


}
