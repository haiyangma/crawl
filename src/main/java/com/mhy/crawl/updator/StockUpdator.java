package com.mhy.crawl.updator;

import com.mhy.crawl.dao.StockDao;
import com.mhy.crawl.dao.StockNameIdDao;
import com.mhy.crawl.data.getter.StockNameIdProcessor;
import com.mhy.crawl.data.model.StockIdName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.utils.HttpConstant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mhy on 2017/5/2.
 */
public class StockUpdator extends Updator {

    @Autowired
    StockDao stockDao ;

    @Autowired
    StockNameIdDao stockNameIdDao ;

    public List<Request> getRequest() {
        List<Request> list = new ArrayList<Request>();
        List<StockIdName> stockIdNames = stockNameIdDao.getAll();
        for(StockIdName stockIdName: stockIdNames){
            Request request = new Request("https://xueqiu.com/v4/stock/quote.json?code="+stockIdName.getSymbol());
            request.setMethod(HttpConstant.Method.GET);
            list.add(request);
        }
        return list;
    }

    public void prepare() {

    }

    public Spider getSpider() {
        Spider spider = Spider.create(new StockNameIdProcessor());
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
        StockUpdator updator = new StockUpdator();
        updator.run();
    }
}
