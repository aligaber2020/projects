package com.app.dataaccessobject;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.app.domainobject.StockMarketDO;


public interface StockMarketRepository extends CrudRepository<StockMarketDO, Long>{

    @Modifying
    @Query(
            value = "truncate table stock_market",
            nativeQuery = true
    )
    @Transactional
	void truncateStockMarketDO();

}
