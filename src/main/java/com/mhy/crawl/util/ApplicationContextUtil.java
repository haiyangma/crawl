package com.mhy.crawl.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Created by mhy on 2017/5/2.
 */
@Component
public class ApplicationContextUtil {

    private static ApplicationContext ac = new ClassPathXmlApplicationContext("spring.xml");

    public static ApplicationContext getContext(){
        if(ac == null){
            ac = new ClassPathXmlApplicationContext("spring.xml");
        }
        return ac;
    }


}
