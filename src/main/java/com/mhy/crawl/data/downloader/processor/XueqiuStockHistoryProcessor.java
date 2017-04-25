package com.mhy.crawl.data.downloader.processor;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mhy.crawl.data.downloader.http.CookieRepo;
import com.mhy.crawl.data.model.HitoStock;
import com.mhy.crawl.data.model.Stock;
import com.mhy.crawl.util.CookieProcessor;
import org.apache.commons.lang3.tuple.Pair;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.utils.HttpConstant;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mhy on 2017/4/14.
 */
public class XueqiuStockHistoryProcessor extends JsonDataProcessor<HitoStock> {

    private Site site = new Site().setRetryTimes(1).setDomain("xueqiu.com").setSleepTime(200);


    public XueqiuStockHistoryProcessor(Class<HitoStock> claz) {
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

//    {
//        stock: {
//            symbol: "SZ300289"
//        },
//        success: "true",
//                chartlist: [
//        {
//            volume: 3192419,
//                    open: 11.86,
//                high: 11.88,
//                close: 11.77,
//                low: 11.66,
//                chg: -0.12,
//                percent: -1.0093,
//                turnrate: 0.8187,
//                ma5: 11.968,
//                ma10: 12.319,
//                ma20: 12.294,
//                ma30: 12.079,
//                dif: 0.05,
//                dea: 0.16,
//                macd: -0.22,
//                time: "Mon Apr 10 00:00:00 +0800 2017"
//        },
//        {
//            volume: 2034221,
//                    open: 11.8,
//                high: 11.95,
//                close: 11.95,
//                low: 11.74,
//                chg: 0.18,
//                percent: 1.5293,
//                turnrate: 0.5217,
//                ma5: 11.952,
//                ma10: 12.235,
//                ma20: 12.287,
//                ma30: 12.093,
//                dif: 0.03,
//                dea: 0.13,
//                macd: -0.21,
//                time: "Tue Apr 11 00:00:00 +0800 2017"
//        }
//    }

    public List<HitoStock> transToObj(JSONObject json) {
        List<HitoStock> toReturn = null;
        if(json.containsKey("chartlist")){
            JSONArray arr = json.getJSONArray("chartlist");
            if(arr.size()>0){
                for(Object jobj : arr){
                    HitoStock hitoStock = JSONObject.toJavaObject((JSONObject)jobj,HitoStock.class);
                    if(toReturn==null){
                        toReturn = new ArrayList<HitoStock>();
                    }
                    toReturn.add(hitoStock);
                }
            }
        }
        return toReturn;
    }

    public void doWithObj(HitoStock stock) {
//        System.out.println(stock);
        LOGGER.info(" ",stock,2);
    }

    public static void main(String[] args) {
        CookieProcessor.updateCookie(new Request("http://xueqiu.com"));
        Spider spider = Spider.create(new XueqiuStockHistoryProcessor(HitoStock.class));
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
//                https://xueqiu.com/stock/forchartk/stocklist.json?symbol=SZ300289&period=1day&type=normal&begin=1491706148390&end=1492138148390
                Request stockRequest = new Request("https://xueqiu.com/stock/forchartk/stocklist.json?symbol="+content+"&period=1day&type=normal&begin=1491706148390&end=1492138148390");
                request.setMethod(HttpConstant.Method.GET);
                toReturn.add(stockRequest);
            }
        }
        return toReturn;
    }
}
