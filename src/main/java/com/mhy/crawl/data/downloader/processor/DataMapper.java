package com.mhy.crawl.data.downloader.processor;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by mhy on 2017/4/14.
 */
public interface DataMapper<T>{

    public String getUrl();

    public T getBean();


}
