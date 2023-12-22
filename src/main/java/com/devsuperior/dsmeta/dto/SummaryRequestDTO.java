package com.devsuperior.dsmeta.dto;

import java.time.LocalDate;

public class SummaryRequestDTO {

	private LocalDate minDate;
	private LocalDate maxDate;

	public SummaryRequestDTO(LocalDate minDate, LocalDate maxDate) {
		this.minDate = minDate;
		this.maxDate = maxDate;
	}

	public LocalDate getMinDate() {
		return minDate;
	}

	public LocalDate getMaxDate() {
		return maxDate;
	}

}
