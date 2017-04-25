package com.mhy.crawl.data.downloader.processor;

import com.alibaba.fastjson.JSONObject;
import com.mhy.crawl.data.model.Stock;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant;

/**
 * Created by mhy on 2017/4/14.
 */
public class StockProcessor extends AbstractJsonDataMapper<Stock> implements PageProcessor{

    private static final String DOMAIN = "http://xueqiu.com/";

    private static final String Stock_Page_PreFix = "http://xueqiu.com/S/";

    private String symbol;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public StockProcessor(String symbol) {
        this.symbol = symbol;
    }

    public String getUrl() {
        return Stock_Page_PreFix+symbol;
    }

    public Request getRequest() {
        Request request = new Request();
        request.setMethod(HttpConstant.Method.GET);
        request.setUrl(getUrl());
        return request;
    }

    public Site getSite() {
        Site site = new Site();
        site.setCycleRetryTimes(1);
        site.setDomain(DOMAIN);
        return site;
    }

    public Stock getBean() {
        JSONObject json = getJson();
        if(json == null){
            LOGGER.error("failed to get the data of stock,symble is "+symbol);
            return null;
        }
        return json.toJavaObject(Stock.class);
    }

    public void process(Page page) {

    }
}
