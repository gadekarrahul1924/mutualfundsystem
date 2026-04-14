package com.mutual.fund.model;

import java.math.BigDecimal;

import jakarta.persistence.*;


@Entity
@Table(name = "funds")
public class Fund {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private BigDecimal nav;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getNav() {
		return nav;
	}
	public void setNav(BigDecimal nav) {
		this.nav = nav;
	}
    
}
