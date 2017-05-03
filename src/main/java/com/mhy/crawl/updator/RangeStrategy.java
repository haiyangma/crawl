package com.mhy.crawl.updator;

import java.util.Calendar;
import java.util.Random;

/**
 * Created by mhy on 2017/5/2.
 */
public class RangeStrategy implements Strategy {

    private int up;

    private int down;

    private int dayMillions = 3600*1000*24;

    public RangeStrategy(int up, int down) {
        this.up = up;
        this.down = down;
        if(up<=down){
            throw new IllegalArgumentException("up must bigger than down ...");
        }
    }

    public boolean start() {
        Calendar time= Calendar.getInstance();
        int maxDayNumber=time.getActualMaximum(Calendar.DAY_OF_MONTH);//本月份的天数
        int nowDays = time.get(Calendar.DAY_OF_MONTH);
        return (System.currentTimeMillis()%dayMillions-down)/(up-down)>(nowDays/maxDayNumber)?true:false;
    }

    public int getEmptySleepTime() {
        return 1000;
    }

    public int getRequestInterval() {
        Random random = new Random();
        return random.nextInt(200);
    }

    public boolean isCycle() {
        return true;
    }

    public static void main(String[] args) {
        RangeStrategy strategy = new RangeStrategy(3600*1000*17,3600*1000*16);
        System.out.println(strategy.start());
    }
}
