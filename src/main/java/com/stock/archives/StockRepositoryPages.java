package com.stock.archives;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StockRepositoryPages extends PagingAndSortingRepository<Stock, Long> {
    Page<Stock> findAll(Pageable pageable);
}