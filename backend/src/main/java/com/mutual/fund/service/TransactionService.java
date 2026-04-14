package com.mutual.fund.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mutual.fund.dto.TransactionRequest;
import com.mutual.fund.model.*;
import com.mutual.fund.repo.*;

import jakarta.transaction.Transactional;

@Service
public class TransactionService {

    @Autowired private UserRepository userRepo;
    @Autowired private FundRepository fundRepo;
    @Autowired private PortfolioRepository portfolioRepo;
    @Autowired private TransactionRepository transactionRepo;

    @Transactional
    public String processTransaction(TransactionRequest request, String username) {

        if (transactionRepo.existsByIdempotencyKey(request.getIdempotencyKey())) {
            return "Duplicate transaction";
        }

        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Fund fund = fundRepo.findById(request.getFundId())
                .orElseThrow(() -> new RuntimeException("Fund not found"));

        Portfolio portfolio = portfolioRepo.findByUserAndFund(user, fund)
                .orElseGet(() -> {
                    Portfolio p = new Portfolio();
                    p.setUser(user);
                    p.setFund(fund);
                    p.setUnits(BigDecimal.ZERO);
                    p.setInvestedAmount(BigDecimal.ZERO);
                    return p;
                });

        BigDecimal nav = fund.getNav();

        if ("BUY".equalsIgnoreCase(request.getType())) {

            BigDecimal units = request.getAmount()
                    .divide(nav, 8, RoundingMode.HALF_UP);

            portfolio.setUnits(portfolio.getUnits().add(units));
            portfolio.setInvestedAmount(
                    portfolio.getInvestedAmount().add(request.getAmount())
            );

            saveTransaction(request, user, fund, units);
        }

        else if ("SELL".equalsIgnoreCase(request.getType())) {

            BigDecimal units = request.getUnits();

            if (portfolio.getUnits().compareTo(units) < 0) {
                throw new RuntimeException("Insufficient units");
            }

            portfolio.setUnits(portfolio.getUnits().subtract(units));

            saveTransaction(request, user, fund, units);
        }

        portfolioRepo.save(portfolio);
        return "Transaction successful";
    }

    private void saveTransaction(TransactionRequest request, User user, Fund fund, BigDecimal units) {
        Transaction txn = new Transaction();
        txn.setUser(user);
        txn.setFund(fund);
        txn.setAmount(request.getAmount());
        txn.setUnits(units);
        txn.setType(request.getType());
        txn.setTransactionTime(LocalDateTime.now());
        txn.setIdempotencyKey(request.getIdempotencyKey());

        transactionRepo.save(txn);
    }
}