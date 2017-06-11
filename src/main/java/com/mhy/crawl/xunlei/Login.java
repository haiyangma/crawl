package com.mhy.crawl.xunlei;

import com.mhy.crawl.data.downloader.jxbrowser.JxBrowserDownloader;
import com.mhy.crawl.data.downloader.jxbrowser.LoadListenerWrapper;
import com.mhy.crawl.data.downloader.processor.XueqiuStockHistoryProcessor;
import com.mhy.crawl.data.model.StockSimple;
import com.teamdev.jxbrowser.chromium.events.FinishLoadingEvent;
import org.apache.commons.collections.map.HashedMap;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.http.HttpMethod;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.downloader.HttpClientGenerator;
import us.codecraft.webmagic.model.HttpRequestBody;
import us.codecraft.webmagic.processor.PageProcessor;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by mhy on 2017/6/11.
 */
public class Login {

    private static final String loginUrl = "https://login.xunlei.com/sec2login/?csrf_token=a28fa754c1dcf55e09d1b0b221b36660";

    public static void main(String[] args) {
        final Site site = new Site();
        site.addHeader("Connection","keep-alive");
        site.addHeader("Origin","http://i.xunlei.com");
        site.addHeader("Accept-Encoding","gzip, deflate, br");
        site.addHeader("Accept-Language","zh-CN,zh;q=0.8");
        site.addHeader("Upgrade-Insecure-Requests","1");
        site.addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36");
        site.addHeader("Content-Type","application/x-www-form-urlencoded");
        site.addHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        site.addHeader("Referer","http://i.xunlei.com/login/?r_d=1&use_cdn=0&timestamp=1497151270749&refurl=http%3A%2F%2Flixian.xunlei.com%2Flogin.htm");
        site.addHeader("Host", "login.xunlei.com");
        site.setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36");

        Request request = new Request(loginUrl);
        request.setMethod(HttpMethod.POST.name());
        Map<String,Object> param = new HashedMap();
        param.put("p", "2015mhyxcy");
        param.put("u", "18613828660");
        param.put("verifycode", "");
        param.put("login_enable", "0");
        param.put("business_type", "108");
        param.put("v", "101");
        param.put("cachetime", "1497167258000");

        HttpRequestBody httpRequestBody = null;
        try {
            httpRequestBody = HttpRequestBody.form(param,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        request.setRequestBody(httpRequestBody);
        Spider spider = Spider.create(new PageProcessor() {
            public void process(Page page) {
                System.out.print(page);
            }

            public Site getSite() {
                return site;
            }
        });

        Page page = new Page();
        LoadListenerWrapper loadListener = new LoadListenerWrapper(page) {

            public void process(Page page, FinishLoadingEvent event) {
                if(event.isMainFrame()){
                    System.out.print(event.getBrowser().getCookieStorage());;
                }
            }
        };
        JxBrowserDownloader downloader = new JxBrowserDownloader(1,loadListener);
        downloader.setThread(1);
        spider.setDownloader(downloader);
//        spider.addRequest(request);
        Request loginPage = new Request("http://lixian.xunlei.com/login.html");
        spider.addRequest(loginPage);
        spider.setExitWhenComplete(false);
        spider.start();
    }
}
