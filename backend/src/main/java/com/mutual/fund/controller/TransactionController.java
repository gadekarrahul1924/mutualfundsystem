package com.mutual.fund.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.mutual.fund.dto.*;
import com.mutual.fund.service.TransactionService;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService service;

    @PostMapping("/buy")
    public ResponseEntity<String> buy(@RequestBody BuyRequest request,
                                     @RequestHeader("Idempotency-Key") String key,
                                     Authentication auth) {

        TransactionRequest txn = new TransactionRequest();
        txn.setFundId(request.getFundId());
        txn.setAmount(request.getAmount());
        txn.setType("BUY");
        txn.setIdempotencyKey(key);

        return ResponseEntity.ok(service.processTransaction(txn, auth.getName()));
    }

    @PostMapping("/sell")
    public ResponseEntity<String> sell(@RequestBody SellRequest request,
                                      @RequestHeader("Idempotency-Key") String key,
                                      Authentication auth) {

        TransactionRequest txn = new TransactionRequest();
        txn.setFundId(request.getFundId());
        txn.setUnits(request.getUnits());
        txn.setType("SELL");
        txn.setIdempotencyKey(key);

        return ResponseEntity.ok(service.processTransaction(txn, auth.getName()));
    }
}