package com.mhy.crawl.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CrossInterceptor implements HandlerInterceptor{

    private static Logger LOGGER = LoggerFactory.getLogger(CrossInterceptor.class);
    
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler) throws Exception {
        String origin = request.getHeader("Origin"); 
        LOGGER.info("Orginal:"+request.getHeader("Origin"));
//        if(activityConfig.getCrossDomainList().contains(origin)){
//            response.setHeader("Access-Control-Allow-Origin", origin);
//            response.setHeader("Access-Control-Allow-Methods", "POST,GET,DELETE");
//            response.setHeader("Access-Control-Allow-Headers", "app_name,app_token,user_id");
//        }
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
            Object handler, Exception ex) throws Exception {
        
    }
    
}
