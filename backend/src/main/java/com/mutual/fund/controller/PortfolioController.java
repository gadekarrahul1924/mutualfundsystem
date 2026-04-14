package com.mutual.fund.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mutual.fund.model.Portfolio;
import com.mutual.fund.service.PortfolioService;

@RestController
public class PortfolioController {

    @Autowired
    private PortfolioService portfolioService;

    @GetMapping("/portfolio")
    public ResponseEntity<List<Portfolio>> getPortfolio(Principal principal) {
        return ResponseEntity.ok(
                portfolioService.getUserPortfolio(principal.getName())
        );
    }
}