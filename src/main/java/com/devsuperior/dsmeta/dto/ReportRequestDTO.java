package com.devsuperior.dsmeta.dto;

import java.time.LocalDate;

public class ReportRequestDTO {
    private LocalDate initialDate;
    private LocalDate finalDate;
    private String sellerName;

    public ReportRequestDTO() {
    }

    public ReportRequestDTO(LocalDate initialDate, LocalDate finalDate, String sellerNameSubstring) {
        this.initialDate = initialDate;
        this.finalDate = finalDate;
        this.sellerName = sellerNameSubstring;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public void setInitialDate(LocalDate initialDate) {
        this.initialDate = initialDate;
    }

    public void setFinalDate(LocalDate finalDate) {
        this.finalDate = finalDate;
    }

    public LocalDate getInitialDate() {
        if (initialDate == null) {
            return LocalDate.now().minusYears(1);
        }
        return initialDate;
    }

    public LocalDate getFinalDate() {
        if (finalDate == null) {
            return LocalDate.now();
        }
        return finalDate;
    }
}
