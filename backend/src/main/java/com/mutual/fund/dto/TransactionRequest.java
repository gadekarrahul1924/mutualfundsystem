package com.mutual.fund.dto;

import java.math.BigDecimal;

public class TransactionRequest {
    private Long fundId;
    private BigDecimal amount;
    private BigDecimal units;
    private String type;
    private String idempotencyKey;

    public Long getFundId() { return fundId; }
    public void setFundId(Long fundId) { this.fundId = fundId; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public BigDecimal getUnits() { return units; }
    public void setUnits(BigDecimal units) { this.units = units; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getIdempotencyKey() { return idempotencyKey; }
    public void setIdempotencyKey(String idempotencyKey) { this.idempotencyKey = idempotencyKey; }
}