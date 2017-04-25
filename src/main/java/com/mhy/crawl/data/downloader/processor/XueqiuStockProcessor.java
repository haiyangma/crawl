package com.mhy.crawl.data.downloader.processor;

import com.alibaba.fastjson.JSONObject;
import com.mhy.crawl.data.downloader.http.CookieRepo;
import com.mhy.crawl.util.CookieProcessor;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import com.mhy.crawl.data.model.Stock;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.selector.Selectable;
import us.codecraft.webmagic.utils.HttpConstant;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mhy on 2017/4/14.
 */
public class XueqiuStockProcessor extends JsonDataProcessor<Stock> {

    Logger LOGGER = LoggerFactory.getLogger(getClass());

    private Site site = new Site().setRetryTimes(1).setDomain("xueqiu.com").setSleepTime(200);

    public XueqiuStockProcessor(Class<Stock> claz) {
        super(claz);
    }

    public Site getSite() {
        site.addHeader("Cookie","*");
        site.addHeader("Referer","https://xueqiu.com");
        site.addHeader("Host","xueqiu.com");
        site.addHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        site.addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.133 Safari/537.36");
        if(CookieRepo.getCookies(site.getDomain())!=null){
            for(Pair<String,String> pair : CookieRepo.getCookies(site.getDomain()))
            site.addCookie(pair.getKey(),pair.getValue());
        }
        return site;
    }

    public List<Stock> transToObj(JSONObject json) {
        List<Stock> ret = null;
        for(Map.Entry<String,Object> entry : json.entrySet()){
            if(ret == null){
                ret = new ArrayList<Stock>();
            }
            ret.add(JSONObject.toJavaObject((JSONObject)entry.getValue(),Stock.class));
        }
        return ret;
    }

    public void doWithObj(Stock stock) {
        LOGGER.info("",stock);
//        System.out.println(stock);
    }

    public static void main(String[] args) {
        CookieProcessor.updateCookie(new Request("http://xueqiu.com"));
        Spider spider = Spider.create(new XueqiuStockProcessor(Stock.class));
        HttpClientDownloader downloader = new HttpClientDownloader();
        downloader.setThread(1);
        getAllStockNumberAndName(downloader);
        spider.setDownloader(downloader);
        for(Request request:getAllStockNumberAndName(downloader)){
            spider.addRequest(request);
        }
        spider.start();
    }


//List<Request>
    public static List<Request> getAllStockNumberAndName(HttpClientDownloader httpClientDownloader){
        String stockListUrl = "http://quote.eastmoney.com/stocklist.html";
        Request request = new Request(stockListUrl);
        List<Request> toReturn = new ArrayList<Request>();
        Site site = new Site();
        Page page = httpClientDownloader.download(request,site.toTask());
        List<String> list = page.getHtml().xpath("//*[@id=\"quotesearch\"]/ul").$("li a").links().all();
        Pattern pattern = Pattern.compile("http://quote.eastmoney.com/(.*).html");
        for(String s : list){
            Matcher matcher = pattern.matcher(s);
            if(matcher.find()){
                String content = matcher.group(1);
                Request stockRequest = new Request("https://xueqiu.com/v4/stock/quote.json?code="+content);
                request.setMethod(HttpConstant.Method.GET);
                toReturn.add(stockRequest);
            }
        }

        return toReturn;
    }
}
