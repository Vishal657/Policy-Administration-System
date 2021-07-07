package com.policy.service;

import org.springframework.stereotype.Service;

import com.policy.request.CreatePolicyRequest;
import com.policy.request.IssuePolicyRequest;
import com.policy.response.ConsumerBusinessDetails;
import com.policy.response.PolicyDetailsResponse;
import com.policy.response.QuotesDetailsResponse;

@Service
public interface PolicyService {

	String issuePolicy(IssuePolicyRequest issuePolicyRequest);

	public String createPolicy(CreatePolicyRequest createPolicyRequest);

	ConsumerBusinessDetails getConsumerBusiness(Long consumerid);

	QuotesDetailsResponse getQuotes(Long businessValue, Long propertyValue, String propertyType);

	PolicyDetailsResponse viewPolicy(Long consumerid, String policyid);

}
