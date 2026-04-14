package com.mutual.fund.repo;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import com.mutual.fund.model.*;

import jakarta.persistence.LockModeType;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Portfolio> findByUserAndFund(User user, Fund fund);

	List<Portfolio> findByUser(User user);
}