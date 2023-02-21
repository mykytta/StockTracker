package com.mykyta.iexstocks.repository;

import com.mykyta.iexstocks.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long> {
    @Query(value = "SELECT * FROM stocks ORDER BY volume DESC LIMIT 5", nativeQuery = true)
    List<Stock> getTopByVolume();
    @Query(value = "SELECT DISTINCT * FROM stocks_history ORDER BY stock_change DESC LIMIT 5", nativeQuery = true)
    List<Stock> getTopByChange();

}
