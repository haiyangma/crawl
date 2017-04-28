package com.mhy.crawl.dao;

import com.mhy.crawl.data.model.StockIdName;
import com.mhy.crawl.data.model.StockSimple;

import java.util.List;

/**
 * Created by mhy on 2017/4/27.
 */
public interface StockSimpleDao {

    public void insert(StockSimple stockSimple);

    public void batchInsert(List<StockSimple> stockSimples);

}
