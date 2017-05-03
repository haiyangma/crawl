package com.mhy.crawl.data.downloader.processor;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;
import java.util.Map;

/**
 * Created by mhy on 2017/4/14.
 */
public abstract class JsonDataProcessor<T> implements PageProcessor {
    Logger LOGGER = LoggerFactory.getLogger(getClass());

    private Class<T> claz;

    public JsonDataProcessor(Class<T> claz) {
        this.claz = claz;
    }

    public void process(Page page) {
        if(page.getStatusCode() != HttpStatus.SC_OK){
            LOGGER.error("failed to get the data from "+page);
            return;
        }

        if(page.getStatusCode() == HttpStatus.SC_OK){
            JSONObject json = JSONObject.parseObject(page.getRawText());
            List<T> list = transToObj(json);
            if(list==null||list.size()==0){
                return;
            }
            doWithObj(list);
        }
    }

    public abstract List<T> transToObj(JSONObject json);

    public abstract Site getSite();

    public abstract void doWithObj(List<T> t);
}
