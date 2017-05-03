package com.mhy.crawl.updator;

import com.mhy.crawl.dao.StockDao;
import com.mhy.crawl.dao.StockNameIdDao;
import com.mhy.crawl.data.downloader.processor.XueqiuStockHistoryProcessor;
import com.mhy.crawl.data.getter.StockNameIdProcessor;
import com.mhy.crawl.data.model.StockIdName;
import com.mhy.crawl.data.model.StockSimple;
import com.mhy.crawl.util.CookieProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.utils.HttpConstant;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by mhy on 2017/5/2.
 */
public class StockSimpleUpdator extends Updator {

    @Autowired
    StockDao stockDao ;

    @Autowired
    StockNameIdDao stockNameIdDao ;

    public List<Request> getRequest() {
        List<StockIdName> stockIdNames = stockNameIdDao.getAll();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        long start =calendar.getTimeInMillis();
        calendar.add(Calendar.DATE,1);
        long end =calendar.getTimeInMillis();
        return produceRequest(stockIdNames,start,end);
    }

    public void prepare() {
        CookieProcessor.updateCookie(new Request("http://xueqiu.com"));
    }

    public Spider getSpider() {
        Spider spider = Spider.create(new XueqiuStockHistoryProcessor(StockSimple.class));
        HttpClientDownloader downloader = new HttpClientDownloader();
        downloader.setThread(3);
        spider.setExitWhenComplete(false);
        return spider;
    }

    public Strategy getStrategy() {
        Strategy strategy = new FixTimeCycleStategy("2017-05-02 17:00",true);
        return strategy;
    }

    public static void main(String[] args) {
        StockSimpleUpdator updator = new StockSimpleUpdator();
        updator.run();
    }

    public static List<Request> produceRequest(List<StockIdName> stockIdNames,long start,long end){
        List<Request> list = new ArrayList<Request>();
        for(StockIdName stockIdName: stockIdNames){
            Request stockRequest = new Request("https://xueqiu.com/stock/forchartk/stocklist.json?symbol="+stockIdName.getSymbol()+"&period=1day&type=normal&begin="+start+"&end="+end);
            stockRequest.setMethod(HttpConstant.Method.GET);
            list.add(stockRequest);
        }
        return list;
    }
}
