package com.mhy.crawl.updator;

import com.mhy.crawl.dao.DDLDao;
import com.mhy.crawl.data.getter.StockNameIdProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mhy on 2017/5/2.
 */
public class StockIdNameUpdator extends Updator {

    @Autowired
    DDLDao ddlDao;

    public List<Request> getRequest() {
        List<Request> list = new ArrayList<Request>();
        list.add(new Request("http://quote.eastmoney.com/stocklist.html"));
        return list;
    }

    public void prepare() {
        ddlDao.truncateTable("stock_id_name");
    }

    public Spider getSpider() {
        Spider spider = Spider.create(new StockNameIdProcessor());
        HttpClientDownloader downloader = new HttpClientDownloader();
        downloader.setThread(1);
        spider.setExitWhenComplete(false);
        return spider;
    }

    public Strategy getStrategy() {
        Strategy strategy = new FixTimeCycleStategy("2017-05-02 17:00",true);
        return strategy;
    }
}
