package com.mhy.crawl.updator;

/**
 * Created by mhy on 2017/5/2.
 */
public interface Strategy {

    public boolean start();

    public int getRequestInterval();

    public int getEmptySleepTime();

}
