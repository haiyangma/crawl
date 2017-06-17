package com.mhy.crawl.xunlei;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.Callback;
import com.teamdev.jxbrowser.chromium.dom.By;
import com.teamdev.jxbrowser.chromium.dom.DOMDocument;
import com.teamdev.jxbrowser.chromium.dom.DOMElement;
import com.teamdev.jxbrowser.chromium.dom.internal.InputElement;

/**
 * Created by mhy on 2017/6/17.
 */
public class XunleiTask {

    public static void main(String[] args) {
        final Browser browser = new Browser();

        // Load https://www.facebook.com/login.php and wait until web page is loaded completely.
        Browser.invokeAndWaitFinishLoadingMainFrame(browser, new Callback<Browser>() {
            public void invoke(Browser browser) {
                browser.loadURL("http://lixian.xunlei.com/login.html");
            }
        });
        Long frameID = null;
        System.out.println(browser.getURL());
        for(Long frameId : browser.getFramesIds()){
            InputElement userId = (InputElement)browser.getDocument(frameId).findElement(By.id("al_u"));
            if(userId!=null){
                frameID = frameId;
                break;
            }
        }
        final long fId = frameID;
        final DOMDocument document = browser.getDocument();
        Browser.invokeAndWaitFinishLoadingMainFrame(browser, new Callback<Browser>() {
            public void invoke(Browser browser) {
                InputElement userId = (InputElement)browser.getDocument(fId).findElement(By.id("al_u"));
                userId.setValue("18613828660");
                InputElement passwd = (InputElement) browser.getDocument(fId).findElement(By.id("al_p"));
                DOMElement submit = browser.getDocument(fId).findElement(By.id("al_submit"));
                passwd.setValue("2015mhyxcy");
                submit.click();
            }
        });
        System.out.print(browser.getURL());
    }
}
