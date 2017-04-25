package com.mhy.crawl.data.downloader.jxbrowser;

import com.teamdev.jxbrowser.chromium.events.FinishLoadingEvent;
import org.junit.Test;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Task;

/**
 * Created by mhy on 2017/4/12.
 */

public class TestJxbrowserDownloader {

    @Test
    public void test(){
        Page page = new Page();
        LoadListenerWrapper loadListener = new LoadListenerWrapper(page) {


            public void process(Page page, FinishLoadingEvent event) {

            }
        };
        JxBrowserDownloader jxBrowserDownloader = new JxBrowserDownloader(1,loadListener);
        page = jxBrowserDownloader.download(new Request("http://wenku.baidu.com"), new Task() {

            public String getUUID() {
                return "huaban.com";
            }

            public Site getSite() {
                return Site.me();
            }
        });
        System.out.println(page.getHtml().getDocument().title());
//        System.out.println(page.getHtml());
    }

}
