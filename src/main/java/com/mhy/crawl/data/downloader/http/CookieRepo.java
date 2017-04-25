package com.mhy.crawl.data.downloader.http;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by mhy on 2017/4/14.
 */
public class CookieRepo {

   private static Map<String,List<Pair>> cookies =  new ConcurrentHashMap<String, List<Pair>>();

    public static void addCookie(String domain,String key,String value){
        synchronized (cookies) {
            List<Pair> list = cookies.get(domain);
            if(list == null){
                list = new ArrayList<Pair>();
                cookies.put(domain,list);
            }
            list.add(Pair.of(key,value));
        }
    }

    public static List<Pair> getCookies(String domain){
        synchronized (cookies) {
            return cookies.get(domain);
        }
    }

}
