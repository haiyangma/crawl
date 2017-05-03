package com.mhy.crawl.data.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mhy on 2017/4/18.
 */
public class StockSimple {
    private Integer id;
    private String name; //股票名称
    private String symbol; //股票代码
    private String volume;
    private String open;
    private String high;
    private String close;
    private String low;
    private String chg;
    private String percent;
    private String turnrate;
    private String ma5;
    private String ma10;
    private String ma30;
    private String dif;
    private String dea;
    private String macd;
    private Date time;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getClose() {
        return close;
    }

    public void setClose(String close) {
        this.close = close;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getChg() {
        return chg;
    }

    public void setChg(String chg) {
        this.chg = chg;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public String getTurnrate() {
        return turnrate;
    }

    public void setTurnrate(String turnrate) {
        this.turnrate = turnrate;
    }

    public String getMa5() {
        return ma5;
    }

    public void setMa5(String ma5) {
        this.ma5 = ma5;
    }

    public String getMa10() {
        return ma10;
    }

    public void setMa10(String ma10) {
        this.ma10 = ma10;
    }

    public String getMa30() {
        return ma30;
    }

    public void setMa30(String ma30) {
        this.ma30 = ma30;
    }

    public String getDif() {
        return dif;
    }

    public void setDif(String dif) {
        this.dif = dif;
    }

    public String getDea() {
        return dea;
    }

    public void setDea(String dea) {
        this.dea = dea;
    }

    public String getMacd() {
        return macd;
    }

    public void setMacd(String macd) {
        this.macd = macd;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = new Date(time);
    }

    @Override
    public String toString() {
        return "StockSimple{" +
                "volume='" + volume + '\'' +
                ", open='" + open + '\'' +
                ", high='" + high + '\'' +
                ", close='" + close + '\'' +
                ", low='" + low + '\'' +
                ", chg='" + chg + '\'' +
                ", percent='" + percent + '\'' +
                ", turnrate='" + turnrate + '\'' +
                ", ma5='" + ma5 + '\'' +
                ", ma10='" + ma10 + '\'' +
                ", ma30='" + ma30 + '\'' +
                ", dif='" + dif + '\'' +
                ", dea='" + dea + '\'' +
                ", macd='" + macd + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
