package com.mhy.crawl.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.mhy.crawl.dao.StockNameIdDao;
import com.mhy.crawl.data.model.StockIdName;
import com.mhy.crawl.util.ApplicationContextUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

/**
 * Created by mhy on 2017/2/13.
 */
public class GuavaCacheFactory {

    private static Cache<String,StockIdName> stockIdNameCache = CacheBuilder
            .newBuilder().expireAfterWrite(24, TimeUnit.DAYS).maximumSize(50000)
            .build();

    public static Cache<String, StockIdName> getStockIdNameCache() {
        return stockIdNameCache;
    }


}
