package com.policy.repository;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.policy.model.ConsumerPolicy;


@Repository
@Transactional
@DynamicUpdate
public interface ConsumerPolicyRepository extends JpaRepository<ConsumerPolicy, Long> {

	Boolean existsByConsumerId(Long consumerid);

	ConsumerPolicy findByConsumerId(Long consumerid);

	ConsumerPolicy findByConsumerIdAndBusinessId(Long consumerid, Long businessid);

}
