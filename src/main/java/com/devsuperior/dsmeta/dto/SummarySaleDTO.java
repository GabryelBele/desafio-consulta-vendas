package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.projections.SummaryProjection;

public class SummarySaleDTO {

	private String sellerName;
	private double total;
	
	public SummarySaleDTO(String sellerName, double total) {
		this.sellerName = sellerName;
		this.total = total;
	}
	
	public SummarySaleDTO(SummaryProjection entity) {
		this.sellerName = entity.getSellerName();
		this.total = entity.getTotal();
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
	
	
	
}
