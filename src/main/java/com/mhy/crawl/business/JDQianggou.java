package com.mhy.crawl.business;

import com.mhy.crawl.data.downloader.jxbrowser.JxBrowserDownloader;
import com.mhy.crawl.data.downloader.jxbrowser.LoadListenerWrapper;
import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.BrowserType;
import com.teamdev.jxbrowser.chromium.Callback;
import com.teamdev.jxbrowser.chromium.dom.By;
import com.teamdev.jxbrowser.chromium.dom.DOMElement;
import com.teamdev.jxbrowser.chromium.dom.internal.InputElement;
import com.teamdev.jxbrowser.chromium.events.FinishLoadingEvent;
import com.teamdev.jxbrowser.chromium.events.ScriptContextAdapter;
import com.teamdev.jxbrowser.chromium.events.ScriptContextEvent;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.model.HttpRequestBody;
import us.codecraft.webmagic.processor.PageProcessor;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by mhy on 2017/11/15.
 */
public class JDQianggou {
    private static Logger LOGGER = LoggerFactory.getLogger(JDQianggou.class);

    private Spider spider;
    public static void main(String[] args) {
        final boolean login = false;
        try {
            Browser browser = new Browser(BrowserType.LIGHTWEIGHT);
            browser.loadURL("https://item.jd.com/4993751.html");
            Browser.invokeAndWaitFinishLoadingMainFrame(browser, new Callback<Browser>(){
                public void invoke(Browser browser) {
                    try {
                        if(login("18613828660","2015mhyxcy",browser)){
                            buy(browser);
                        };
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean login(final String userName,final String passwd,Browser browser){
        DOMElement loginItem = browser.getDocument().findElement(By.xpath("//*[@id=\"ttbar-login\"]/a[1]"));
        if(loginItem==null){
            loginItem = browser.getDocument().findElement(By.xpath(" //*[@id=\"ttbar-login\"]/div[1]/i[1]"));
            if(loginItem!=null){
                return true;
            }
            LOGGER.error("找不到登录按钮...");
        }
        loginItem.click();
        Browser.invokeAndWaitFinishLoadingMainFrame(browser, new Callback<Browser>() {
            public void invoke(Browser browser) {
                browser.getDocument().findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[1]/div/div[3]/a")).click();
                InputElement userNameItem = (InputElement)browser.getDocument().findElement(By.id("loginname"));
                userNameItem.setValue(userName);
                InputElement passwdItem = (InputElement)browser.getDocument().findElement(By.id("password"));
                passwdItem.setValue(passwd);
                browser.getDocument().findElement(By.id("loginsubmit")).click();

            }
        });
        loginItem = browser.getDocument().findElement(By.xpath(" //*[@id=\"ttbar-login\"]/div[1]/i[1]"));
        if(loginItem!=null){
            return true;
        }
        return false;
    }

    public static void buy(Browser browser) throws Exception{
        while(true){
            DOMElement qianggouBtn = browser.getDocument().findElement(By.id("btn-qiang"));
            if(qianggouBtn!=null){
                qianggouBtn.click();
                Browser.invokeAndWaitFinishLoadingMainFrame(browser, new Callback<Browser>() {
                    public void invoke(Browser browser) {
                        browser.getDocument().findElement(By.id("order-submit")).click();
                    }
                });
                Thread.sleep(1);
            }
        }
    }


}
