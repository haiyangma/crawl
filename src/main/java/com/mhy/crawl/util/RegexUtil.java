package com.mhy.crawl.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mhy on 2017/4/18.
 */
public class RegexUtil {

    public static String getparamByRegex(String url,String regex){
        Pattern p = Pattern.compile("(?<=//|)((\\w)+\\.)+\\w+");
        Matcher matcher = p.matcher(url);
        String param = null;
        if (matcher.find()) {
            param = matcher.group();
        }
        return param;
    }

}
