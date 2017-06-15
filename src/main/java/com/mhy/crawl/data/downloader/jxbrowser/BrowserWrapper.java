package com.mhy.crawl.data.downloader.jxbrowser;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.BrowserType;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by mhy on 2017/4/11.
 */
public  class BrowserWrapper extends Browser{
    private AtomicBoolean status = new AtomicBoolean();
    private BrowserPool browserPool;
    private int name;

    public BrowserWrapper(BrowserPool browserPool,int name){
        super(BrowserType.LIGHTWEIGHT);
        this.browserPool = browserPool;
        this.name = name;
    }

    public AtomicBoolean getStatus() {
        return status;
    }

    public void setStatus(AtomicBoolean status) {
        this.status = status;
    }

    public BrowserPool getBrowserPool() {
        return browserPool;
    }

    public void setBrowserPool(BrowserPool browserPool) {
        this.browserPool = browserPool;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public void addLoadListener(LoadListenerWrapper loadAdapter) {
        loadAdapter.setBrowserWrapper(this);
        super.addLoadListener(loadAdapter);
    }






}
