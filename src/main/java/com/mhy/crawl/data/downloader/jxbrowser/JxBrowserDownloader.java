package com.mhy.crawl.data.downloader.jxbrowser;

import com.mhy.crawl.util.RegexUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.AbstractDownloader;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.PlainText;
import us.codecraft.webmagic.utils.UrlUtils;

import java.io.Closeable;
import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mhy on 2017/4/11.
 */
public class JxBrowserDownloader extends AbstractDownloader implements Closeable {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private BrowserPool browserPool;
    private int poolSize;
    private LoadListenerWrapper loadListener;
    public JxBrowserDownloader(int poolSize,LoadListenerWrapper loadListener) {
        this.poolSize = poolSize;
        browserPool = new BrowserPool(this.poolSize);
        this.loadListener = loadListener;
    }

    public Page download(Request request, Task task) {
        BrowserWrapper browserWrapper;
        browserWrapper = browserPool.borrowOne();
        while(browserWrapper == null){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Site site = task.getSite();
        String url = request.getUrl();
        String domain = RegexUtil.getparamByRegex(url,"(?<=//|)((\\w)+\\.)+\\w+");
        String path = "";
        if(url.split(domain).length>1){
            path = url.split(domain)[1];
        }
        if (site.getCookies() != null) {
            for (Map.Entry<String, String> cookieEntry : site.getCookies()
                    .entrySet()) {
                browserWrapper.getCookieStorage().setCookie(request.getUrl(),cookieEntry.getKey(),cookieEntry.getValue(),
                        domain,path,(System.currentTimeMillis()+3600*1000*24),false,false     );
            }
        }
        final Page page = loadListener.getPage();

        page.setUrl(new PlainText(request.getUrl()));
        page.setRequest(request);
        logger.info("downloading page " + request.getUrl());
        browserWrapper.addLoadListener(loadListener);
        browserWrapper.loadURL(request.getUrl());
        int i =0;
        for(;i<100;i++){
            if(loadListener.isOk!=null){
                break;
            }
            if(loadListener.isOk == null){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        if(loadListener.isOk.get()){
            page.setRawText(browserWrapper.getHTML());
        }
        return page;

    }

    public void setThread(int i) {
        this.poolSize = i;
    }

    public void close() throws IOException {
        this.browserPool.close();
    }
}
