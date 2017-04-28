package com.mhy.crawl.dao;

import com.mhy.crawl.data.model.StockIdName;

import java.util.List;

/**
 * Created by mhy on 2017/4/27.
 */
public interface StockNameIdDao {

    public void insert(StockIdName stockIdName);

    public void batchInsert(List<StockIdName> stockIdNames);

}
