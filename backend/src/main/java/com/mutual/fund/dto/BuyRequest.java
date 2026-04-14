package com.mutual.fund.dto;

import java.math.BigDecimal;

public class BuyRequest {
    private Long fundId;
    private BigDecimal amount;

    public Long getFundId() { return fundId; }
    public void setFundId(Long fundId) { this.fundId = fundId; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
}