package com.mutual.fund.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mutual.fund.model.Fund;

public interface FundRepository extends JpaRepository<Fund, Long> {
	
}

