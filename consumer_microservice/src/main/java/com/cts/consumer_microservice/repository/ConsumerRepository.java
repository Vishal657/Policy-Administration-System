package com.cts.consumer_microservice.repository;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cts.consumer_microservice.entities.Consumer;


@Repository
@Transactional
@DynamicUpdate
public interface ConsumerRepository extends JpaRepository<Consumer, Long>{
	boolean existsById(Long id);

}
