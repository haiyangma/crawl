package com.mhy.crawl.updator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by mhy on 2017/5/2.
 */
public class FixTimeCycleStategy implements Strategy{

    private Date time;


    public Date getTime() {
        return time;
    }

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    /**
     * HH:mm
     * @param time
     */
    public FixTimeCycleStategy(String time,boolean startNow) {
        try {
            this.time = simpleDateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(!startNow){
            this.time.setTime(this.time.getTime()+3600*1000*24);
        }
    }

    public boolean start() {
        Date date = new Date();
        if(date.after(time)){
            time.setTime(time.getTime()+3600*1000*24);
            return true;
        }
        return false;
    }

    public int getRequestInterval() {
        return 1000;
    }

    public int getEmptySleepTime() {
        return 1000;
    }

    public static void main(String[] args) {
        FixTimeCycleStategy fixTimeStategy = new FixTimeCycleStategy("2017-05-02 17:00",true);
        System.out.println(fixTimeStategy.start());
        System.out.println(fixTimeStategy.start());
    }
}
