package com.policy.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.policy.model.ConsumerPolicy;
import com.policy.model.PolicyMaster;
import com.policy.repository.ConsumerPolicyRepository;
import com.policy.repository.PolicyMasterRepository;
import com.policy.request.CreatePolicyRequest;
import com.policy.request.IssuePolicyRequest;
import com.policy.response.ConsumerBusinessDetails;
import com.policy.response.PolicyDetailsResponse;
import com.policy.response.QuotesDetailsResponse;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PolicyServiceImpl implements PolicyService {
	@Autowired
	ConsumerPolicyRepository consumerPolicyRepository;

	@Autowired
	PolicyMasterRepository policyMasterRepository;
	@Autowired
	private RestTemplate restTemplate;

	public String issuePolicy(IssuePolicyRequest issuePolicyRequest) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		ConsumerPolicy consumerPolicy = consumerPolicyRepository // finding the costomer
				.findByConsumerIdAndBusinessId(issuePolicyRequest.getConsumerid(), issuePolicyRequest.getBusinessid());
		PolicyMaster policyMaster = policyMasterRepository.findByPid(issuePolicyRequest.getPolicyid()); // finding the
																										// policy
																										// associated to
																										// the given
																										// consumer
		consumerPolicy.setPolicyId(issuePolicyRequest.getPolicyid());
		consumerPolicy.setPaymentDetails(issuePolicyRequest.getPaymentdetails());
		consumerPolicy.setAcceptanceStatus(issuePolicyRequest.getAcceptancestatus());
		consumerPolicy.setPolicyStatus("Policy Issued");
		consumerPolicy.setEffectiveDate(dtf.format(now));
		consumerPolicy.setDuration(policyMaster.getTenure());
		consumerPolicy.setCovered_sum(policyMaster.getAssured_sum());
		ConsumerPolicy consumerPolicySave = consumerPolicyRepository.save(consumerPolicy);
		return "issued";
	}

	@Override
	public String createPolicy(CreatePolicyRequest createPolicyRequest) {
		ConsumerBusinessDetails consumerBusinessDetails = getConsumerBusiness(createPolicyRequest.getConsumerid());
		if (consumerBusinessDetails == null) {
			return "No Consumer Business Found !!";
		}
		ConsumerPolicy consumerPolicy = new ConsumerPolicy(consumerBusinessDetails.getConsumerId(),
				consumerBusinessDetails.getBusinessId(), "Initiated", createPolicyRequest.getAcceptedquotes());
		ConsumerPolicy consumerPolicysave = consumerPolicyRepository.save(consumerPolicy);
		return "Policy Has been Created ";
	}

	@Override
	public ConsumerBusinessDetails getConsumerBusiness(Long consumerid) {
		ConsumerBusinessDetails consumerBusinessDetails = this.restTemplate.getForObject(
				"http://localhost:8080/viewConsumerBusiness?consumerId=" + consumerid, ConsumerBusinessDetails.class);
		return consumerBusinessDetails;
	}

	@Override
	public QuotesDetailsResponse getQuotes(Long businessValue, Long propertyValue, String propertyType) {

		String s = this.restTemplate.getForObject("http://localhost:8666/getQuotesForPolicy?businessValue="
				+ businessValue + "&propertyValue=" + propertyValue + "&propertyType=" + propertyType, String.class);
		return (new QuotesDetailsResponse(s));
	}

	@Override
	public PolicyDetailsResponse viewPolicy(Long consumerid, String policyid) {
		PolicyMaster policyMaster = policyMasterRepository.findByPid(policyid);
		ConsumerPolicy consumerPolicy = consumerPolicyRepository.findByConsumerId(consumerid);
		PolicyDetailsResponse policyDetailsResponse = new PolicyDetailsResponse(consumerid, policyMaster.getPid(),
				policyMaster.getProperty_type(), policyMaster.getConsumer_type(), policyMaster.getAssured_sum(),
				policyMaster.getTenure(), policyMaster.getBusiness_value(), policyMaster.getProperty_value(),
				policyMaster.getBase_location(), policyMaster.getType(), consumerPolicy.getBusinessId(),
				consumerPolicy.getPaymentDetails(), consumerPolicy.getAcceptanceStatus(),
				consumerPolicy.getPolicyStatus(), consumerPolicy.getEffectiveDate(), consumerPolicy.getCovered_sum(),
				consumerPolicy.getDuration(), consumerPolicy.getAcceptedQuote());
		return policyDetailsResponse;
	}
}
