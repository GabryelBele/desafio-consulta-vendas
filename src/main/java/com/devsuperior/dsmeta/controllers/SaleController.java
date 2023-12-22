package com.devsuperior.dsmeta.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dsmeta.dto.ReportRequestDTO;
import com.devsuperior.dsmeta.dto.SaleListingDTO;
import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SummaryRequestDTO;
import com.devsuperior.dsmeta.dto.SummarySaleDTO;
import com.devsuperior.dsmeta.services.SaleService;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

    @Autowired
    private SaleService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
        SaleMinDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/report")
    public ResponseEntity<Page<SaleListingDTO>> getReport(
            @RequestParam(name = "minDate", required = false, defaultValue = "") String minDate,
            @RequestParam(name = "maxDate", required = false, defaultValue = "") String maxDate,
            @RequestParam(name = "sellerName", required = false, defaultValue = "") String sellerName,
            Pageable pageable) {

        LocalDate initialDate = minDate.isEmpty() ? LocalDate.now().minusYears(1) : LocalDate.parse(minDate);
        LocalDate finalDate = maxDate.isEmpty() ? LocalDate.now() : LocalDate.parse(maxDate);

        ReportRequestDTO dto = new ReportRequestDTO(initialDate, finalDate, sellerName);

        try {
            Page<SaleListingDTO> result = service.gerarRelatorio(dto, pageable);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping(value = "/summary")
    public ResponseEntity<List<SummarySaleDTO>> getSummary(
            @RequestParam(name = "minDate", required = false, defaultValue = "") String minDate,
            @RequestParam(name = "maxDate", required = false, defaultValue = "") String maxDate) {

        LocalDate initialDate = minDate.isEmpty() ? LocalDate.now().minusYears(1) : LocalDate.parse(minDate);
        LocalDate finalDate = maxDate.isEmpty() ? LocalDate.now() : LocalDate.parse(maxDate);

        SummaryRequestDTO dto = new SummaryRequestDTO(initialDate, finalDate);

        List<SummarySaleDTO> result = service.getSummarySale(dto);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
