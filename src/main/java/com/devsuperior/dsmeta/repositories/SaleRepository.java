package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.devsuperior.dsmeta.dto.SaleListingDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.SummaryProjection;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

	@Query("SELECT new com.devsuperior.dsmeta.dto.SaleListingDTO(sale.id, sale.date, sale.amount, sale.seller.name) "
			+ "FROM Sale sale " + "WHERE sale.date BETWEEN :dataInicial AND :dataFinal "
			+ "AND (:trechoNomeVendedor IS NULL OR sale.seller.name LIKE %:trechoNomeVendedor%)")
	Page<SaleListingDTO> search(LocalDate dataInicial, LocalDate dataFinal, String trechoNomeVendedor,
			Pageable pageable);

	@Query(nativeQuery = true, value = "SELECT se.name as sellerName , SUM(sa.amount) as total " + "FROM tb_sales sa "
			+ "INNER JOIN tb_seller se ON sa.seller_id = se.id "
			+ "WHERE sa.date BETWEEN '2022-01-01' AND '2022-06-30' " + "GROUP BY se.name")
	List<SummaryProjection> searchBySaleBetweenDate(LocalDate dataFinal, LocalDate dataInicial);

}
