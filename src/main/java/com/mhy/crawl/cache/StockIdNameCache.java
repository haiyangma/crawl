package com.mhy.crawl.cache;

import com.google.common.cache.Cache;
import com.mhy.crawl.dao.StockNameIdDao;
import com.mhy.crawl.data.model.StockIdName;
import com.mhy.crawl.util.ApplicationContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

/**
 * Created by mhy on 2017/5/2.
 */
@Component
public class StockIdNameCache {

    Logger LOGGER = LoggerFactory.getLogger(getClass());

   static StockNameIdDao stockNameIdDao = ApplicationContextUtil.getContext().getBean(StockNameIdDao.class);

    private static Cache<String,StockIdName> cache = GuavaCacheFactory.getStockIdNameCache();

    public StockIdName getStockIdName(final String symbol){
        StockIdName stockIdName = null;
        try {
            stockIdName = cache.get(symbol, new Callable<StockIdName>() {

                public StockIdName call() throws Exception {
                    StockIdName stockIdName = stockNameIdDao.getStockIdName(symbol);
                    return stockIdName;
                }
            });
        } catch (ExecutionException e) {
            LOGGER.error("get stock id name info {}",symbol,e);
        }
        return stockIdName;
    }
}
