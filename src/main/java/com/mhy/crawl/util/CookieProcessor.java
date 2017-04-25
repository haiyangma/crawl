package com.mhy.crawl.util;

import com.mhy.crawl.data.downloader.http.CookieRepo;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class CookieProcessor {
    Logger LOGGER = LoggerFactory.getLogger(getClass());

    public static void updateCookie(Request request) {
        String domain = RegexUtil.getparamByRegex(request.getUrl(),"(?<=//|)((\\w)+\\.)+\\w+");
        String webSite = "https://"+domain;
        HttpURLConnection connection = null;
        try {
            connection = connection == null ?
                    (HttpURLConnection) new URL(webSite).openConnection() : connection;
            connection.connect();
            List<String> list = connection.getHeaderFields().get("Set-Cookie");
            for(String cookie : list){
                String[] nameAndValues = cookie.split(";");
                for(String kv : nameAndValues){
                    String[] kvPair = kv.split("=");
                    if(kvPair.length == 2){
                        CookieRepo.addCookie(domain,kvPair[0],kvPair[1]);
                    }
                }
            }
        }catch (Exception e){

        }finally {
            if (connection != null) connection.disconnect();
        }

    }


}
