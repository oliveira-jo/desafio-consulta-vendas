package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsmeta.dto.ReportDTO;
import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SummaryDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;

	private LocalDate maxDateParsed;
	private LocalDate minDateParsed;

	@Transactional(readOnly = true)
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	@Transactional(readOnly = true)
	public List<SummaryDTO> getSummary(String minDate, String maxDate) {
		validateDate(minDate, maxDate);
		return repository.getSummary(minDateParsed, maxDateParsed);
	}

	@Transactional(readOnly = true)
	public Page<ReportDTO> getReport(String minDate, String maxDate, String name, Pageable pageable) {
		validateDate(minDate, maxDate);
		return repository.getReport(minDateParsed, maxDateParsed, name, pageable);
	}

	private void validateDate(String minDate, String maxDate) {
		if (maxDate.isEmpty()) {
			maxDateParsed = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		} else {
			maxDateParsed = LocalDate.parse(maxDate);
		}
		if (minDate.isEmpty()) {
			minDateParsed = maxDateParsed.minusYears(1L);
		} else {
			minDateParsed = LocalDate.parse(minDate);
		}
	}

}
