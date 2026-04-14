package com.mutual.fund.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

import com.mutual.fund.model.*;
import com.mutual.fund.repo.*;

@Service
public class PortfolioService {
    @Autowired private PortfolioRepository portfolioRepo;
    @Autowired private UserRepository userRepo;

    public List<Portfolio> getUserPortfolio(String username) {
        User user = userRepo.findByUsername(username).orElseThrow();
        return portfolioRepo.findByUser(user);
    }
}
