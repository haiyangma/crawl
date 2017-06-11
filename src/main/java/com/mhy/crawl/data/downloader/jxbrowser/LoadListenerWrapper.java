package com.mhy.crawl.data.downloader.jxbrowser;

import com.teamdev.jxbrowser.chromium.events.FailLoadingEvent;
import com.teamdev.jxbrowser.chromium.events.FinishLoadingEvent;
import com.teamdev.jxbrowser.chromium.events.LoadAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by mhy on 2017/4/11.
 */
public abstract class LoadListenerWrapper extends LoadAdapter {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private Page page;

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    private BrowserWrapper browserWrapper;

    public AtomicBoolean isOk = null;

    public LoadListenerWrapper(Page page) {
        this.page = page;
    }

    public BrowserWrapper getBrowserWrapper() {
        return browserWrapper;
    }

    public void setBrowserWrapper(BrowserWrapper browserWrapper) {
        this.browserWrapper = browserWrapper;
    }

    public abstract void process(Page page,FinishLoadingEvent event);

    @Override
    public void onFinishLoadingFrame(FinishLoadingEvent event) {
        super.onFinishLoadingFrame(event);
        if(event.isMainFrame()){
            if(isOk == null){
                isOk = new AtomicBoolean();
            }
            process(page,event);
            browserWrapper.getBrowserPool().returnOne(browserWrapper);
            isOk.set(true);
        }
    }

    @Override
    public void onFailLoadingFrame(FailLoadingEvent event) {
        if(event.isMainFrame()){
            if(isOk == null){
                isOk = new AtomicBoolean();
            }
            browserWrapper.getBrowserPool().returnOne(browserWrapper);
            logger.warn("failed to load the url ...");
            page = null;
            isOk.set(false);
        }
    }
}
