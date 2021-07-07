package com.policy.policyController;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.policy.repository.ConsumerPolicyRepository;
import com.policy.repository.PolicyMasterRepository;
import com.policy.request.CreatePolicyRequest;
import com.policy.request.IssuePolicyRequest;
import com.policy.response.PolicyDetailsResponse;
import com.policy.response.QuotesDetailsResponse;
import com.policy.service.PolicyService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class PolicyController {
	@Autowired
	PolicyService policyService; // huhuhuug

	@Autowired
	PolicyMasterRepository policyMasterRepository;

	@Autowired
	ConsumerPolicyRepository consumerPolicyRepository;

	@GetMapping("/getQuotes")
	// @HystrixCommand(fallbackMethod = "sendPolicyErrorResponse")
	public ResponseEntity<QuotesDetailsResponse> getQuotes(@Valid @RequestParam Long businessValue,
			@RequestParam Long propertyValue, @RequestParam String propertyType) {
		QuotesDetailsResponse quotesDetailsResponse = policyService.getQuotes(businessValue, propertyValue,
				propertyType);

		return ResponseEntity.ok(quotesDetailsResponse);
	}

	@PostMapping("/createPolicy")
	// @HystrixCommand(fallbackMethod = "sendPolicyErrorResponse")
	public String createPolicy(@Valid @RequestBody CreatePolicyRequest createPolicyRequest) {
		String res = policyService.createPolicy(createPolicyRequest);

		return res;
	}

	@PostMapping("/issuePolicy")
	public String issuePolicy(@Valid @RequestBody IssuePolicyRequest issuePolicyRequest) {
		if (!consumerPolicyRepository.existsByConsumerId(issuePolicyRequest.getConsumerid())) {// to check weather
																								// customer is there in
																								// database
			return ("Sorry!!, No Consumer Found!!");
		}
		if (!policyMasterRepository.existsByPid(issuePolicyRequest.getPolicyid())) {// to check weather policy exists in
																					// database
			return ("Sorry!!, No Policy Found!!");
		}
		if (!(issuePolicyRequest.getPaymentdetails().equals("Success"))) {// to check weather success is the status in Paymentdetails
			return ("Sorry!!, Payment Failed!! Try Again");
		}
		if (!(issuePolicyRequest.getAcceptancestatus().equals("Accepted"))) {// to check weather accepted is the status in Acceptancestatus
			return ("Sorry!!, Accepted Failed !! Try Again");
		}
		String res = policyService.issuePolicy(issuePolicyRequest);
		return (res);
	}

	@GetMapping("/viewPolicy")
	// @HystrixCommand(fallbackMethod = "sendPolicyErrorResponse")
	public ResponseEntity<?> viewPolicy(@Valid @RequestParam Long consumerid, @RequestParam String policyid) {
		if (!policyMasterRepository.existsByPid(policyid)) {		// checking weather policy exists or not
			return ResponseEntity.badRequest().body("Sorry!!, No Policy Found!!");
		}
		if (!consumerPolicyRepository.existsByConsumerId(consumerid)) {		// checking weather consumer exists
			return ResponseEntity.badRequest().body("Sorry!!, No Consumer Found!!");
		}
		PolicyDetailsResponse policyDetailsResponse = policyService.viewPolicy(consumerid, policyid);

		return ResponseEntity.ok(policyDetailsResponse);
	}
}
