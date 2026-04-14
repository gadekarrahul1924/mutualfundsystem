package com.mutual.fund.repo;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mutual.fund.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Optional<Transaction> findByIdempotencyKey(String key);
    
    boolean existsByIdempotencyKey(String idempotencyKey);
}
