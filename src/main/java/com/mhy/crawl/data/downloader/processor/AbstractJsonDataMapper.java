package com.mhy.crawl.data.downloader.processor;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.downloader.HttpClientDownloader;

/**
 * Created by mhy on 2017/4/14.
 */
public abstract class AbstractJsonDataMapper<T> implements JsonDataMapper {
    Logger LOGGER = LoggerFactory.getLogger(getClass());
    static HttpClientDownloader httpClientDownloader = new HttpClientDownloader();

    public abstract Request getRequest();

    public abstract Site getSite();

    public JSONObject getJson() {
        Page page = httpClientDownloader.download(getRequest(),getSite().toTask());
        if(page.getStatusCode() == HttpStatus.SC_OK){
            return JSONObject.parseObject(page.getRawText()) ;
        }
        LOGGER.warn("failed to get the data from "+getRequest());
        return null;
    }

    public abstract T getBean();
}
