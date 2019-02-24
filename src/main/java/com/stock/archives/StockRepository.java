package com.stock.archives;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StockRepository extends CrudRepository<Stock, Long> {
    Stock save(Stock stock);

    <S extends Stock> Iterable<S> saveAll(Iterable<S> entities);

    Stock getById(long id);

    @Query(value = "SELECT DISTINCT symbol FROM stock ORDER BY symbol ASC", nativeQuery = true)
    List<String> findDistinctBySymbol();

    List<Stock> getStockBySymbolOrderByInstanceDesc(String symbol);

    long count();
}