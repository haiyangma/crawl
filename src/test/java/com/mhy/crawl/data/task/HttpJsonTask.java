package com.mhy.crawl.data.task;

import com.mhy.crawl.dao.StockNameIdDao;
import com.mhy.crawl.data.downloader.processor.JsonDataProcessor;
import com.mhy.crawl.data.downloader.processor.XueqiuStockHistoryProcessor;
import com.mhy.crawl.data.model.StockIdName;
import com.mhy.crawl.data.model.StockSimple;
import com.mhy.crawl.updator.StockSimpleUpdator;
import com.mhy.crawl.updator.Strategy;
import com.mhy.crawl.updator.Updator;
import com.mhy.crawl.util.ApplicationContextUtil;
import com.mhy.crawl.util.CookieProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.processor.PageProcessor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by mhy on 2017/5/3.
 */
public class HttpJsonTask extends Updator{

    private String start;
    private String end;
    private List<StockIdName> toGet;
    private JsonDataProcessor pageProcessor;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public HttpJsonTask(JsonDataProcessor pageProcessor, String start, String end, List<StockIdName> toGet) {
        this.start = start;
        this.end = end;
        this.toGet = toGet;
        this.pageProcessor = pageProcessor;
    }

    public Strategy getStrategy() {
        return new Strategy() {
            public boolean start() {
                return true;
            }

            public int getRequestInterval() {
                return 100;
            }

            public int getEmptySleepTime() {
                return 100;
            }
        };
    }

    public List<Request> getRequest() {
        long start = 0;
        long end = 0;
        try {
            start = simpleDateFormat.parse(this.start).getTime();
            end = simpleDateFormat.parse(this.end).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(end==start){
            end = start + 3600*1000*24;
        }
        return StockSimpleUpdator.produceRequest(toGet,start,end);
    }

    public void prepare() {
        CookieProcessor.updateCookie(new Request("http://xueqiu.com"));
    }

    public Spider getSpider() {
        Spider spider = Spider.create(pageProcessor);
        HttpClientDownloader downloader = new HttpClientDownloader();
        downloader.setThread(3);
        spider.setExitWhenComplete(false);
        return spider;
    }

    public static void main(String[] args) {
        StockNameIdDao stockNameIdDao = ApplicationContextUtil.getContext().getBean(StockNameIdDao.class);
        List<StockIdName> toGet = stockNameIdDao.getAll();
        XueqiuStockHistoryProcessor processor = new XueqiuStockHistoryProcessor(StockSimple.class);
        HttpJsonTask stockSimpleTask   = new HttpJsonTask(processor,"2015-04-29","2015-04-29",toGet);
        stockSimpleTask.run();
    }


}
