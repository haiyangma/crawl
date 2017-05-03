package com.mhy.crawl.dao;

import com.mhy.crawl.data.model.Stock;
import com.mhy.crawl.data.model.StockIdName;
import com.mhy.crawl.data.model.StockSimple;

import java.util.List;

/**
 * Created by mhy on 2017/4/27.
 */
public interface StockDao {

    public void insert(Stock stock);

    public void batchInsert(List<Stock> stocks);

}
