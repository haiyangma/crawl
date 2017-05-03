package com.mhy.crawl.updator;

import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * Created by mhy on 2017/5/2.
 */
public abstract class Updator implements Runnable {

    private Spider spider;

    private Strategy strategy;

    public Updator() {
        prepare();
        this.spider = getSpider();
        this.strategy = getStrategy();
        spider.setExitWhenComplete(false);
        spider.start();
    }

    public abstract Strategy getStrategy();

    public abstract List<Request> getRequest();

    public abstract void prepare();

    public abstract Spider getSpider();

    public void run() {
        while(true){
            if(this.strategy.start()){
                List<Request> requests = getRequest();
                if(requests!=null){
                    for(Request request : requests){
                        spider.addRequest(request);
                    }
                }
            }else{
                try {
                    Thread.sleep(strategy.getEmptySleepTime());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
