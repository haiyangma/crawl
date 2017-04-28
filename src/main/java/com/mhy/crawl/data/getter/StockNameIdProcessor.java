package com.mhy.crawl.data.getter;

import com.mhy.crawl.dao.StockNameIdDao;
import com.mhy.crawl.data.downloader.processor.XueqiuStockHistoryProcessor;
import com.mhy.crawl.data.model.StockIdName;
import com.mhy.crawl.data.model.StockSimple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.example.GithubRepo;
import us.codecraft.webmagic.model.PageMapper;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * Created by mhy on 2017/4/28.
 */
public class StockNameIdProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(0);
    private PageMapper<StockIdName> githubRepoPageMapper = new PageMapper<StockIdName>(StockIdName.class);
    ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
    @Autowired
    StockNameIdDao stockNameIdDao = ctx.getBean(StockNameIdDao.class);

    public void process(Page page) {
        List<StockIdName> list = githubRepoPageMapper.getAll(page);
        stockNameIdDao.batchInsert(list);
        System.currentTimeMillis();
    }

    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider spider = Spider.create(new StockNameIdProcessor());
        HttpClientDownloader downloader = new HttpClientDownloader();
        downloader.setThread(1);
        spider.addUrl("http://quote.eastmoney.com/stocklist.html");
        spider.setExitWhenComplete(false);
        spider.start();

    }
}
