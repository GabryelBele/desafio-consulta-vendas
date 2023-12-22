package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.ReportRequestDTO;
import com.devsuperior.dsmeta.dto.SaleListingDTO;
import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SummaryRequestDTO;
import com.devsuperior.dsmeta.dto.SummarySaleDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.SummaryProjection;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository saleRepository;

	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = saleRepository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public Page<SaleListingDTO> gerarRelatorio(ReportRequestDTO dto, Pageable pageable) {
		LocalDate dataFinal = Objects.requireNonNullElse(dto.getFinalDate(),
				LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault()));

		LocalDate dataInicial = Objects.requireNonNullElse(dto.getInitialDate(), dataFinal.minusYears(1));

		String trechoNomeVendedor = Objects.requireNonNullElse(dto.getSellerName(), "");

		Page<SaleListingDTO> result = saleRepository.search(dataInicial, dataFinal, trechoNomeVendedor, pageable);

		return result;
	}

	public List<SummarySaleDTO> getSummarySale(SummaryRequestDTO dto) {
		LocalDate dataFinal = Objects.requireNonNullElse(dto.getMaxDate(),
				LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault()));

		LocalDate dataInicial = Objects.requireNonNullElse(dto.getMinDate(), dataFinal.minusYears(1));

		List<SummaryProjection> list = saleRepository.searchBySaleBetweenDate(dataFinal, dataInicial);
		List<SummarySaleDTO> result = list.stream().map(SummarySaleDTO::new).toList();
		return result;
	}
}
