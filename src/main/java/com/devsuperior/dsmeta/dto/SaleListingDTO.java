package com.devsuperior.dsmeta.dto;

import java.time.LocalDate;

public class SaleListingDTO {

	private Long id;
	private LocalDate date;
	private double amount;
	private String sellerName;

	public SaleListingDTO() {
	}

	public SaleListingDTO(Long id, LocalDate date, double amount, String sellerName) {
		this.id = id;
		this.date = date;
		this.amount = amount;
		this.sellerName = sellerName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

}