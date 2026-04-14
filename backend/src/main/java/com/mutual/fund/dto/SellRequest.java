package com.mutual.fund.dto;

import java.math.BigDecimal;

public class SellRequest {
    private Long fundId;
    private BigDecimal units;

    public Long getFundId() { return fundId; }
    public void setFundId(Long fundId) { this.fundId = fundId; }

    public BigDecimal getUnits() { return units; }
    public void setUnits(BigDecimal units) { this.units = units; }
}