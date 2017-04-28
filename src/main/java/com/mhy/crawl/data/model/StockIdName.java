package com.mhy.crawl.data.model;

import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.TargetUrl;

/**
 * Created by mhy on 2017/4/27.
 */
//@TargetUrl(value = "http://quote.eastmoney.com/stocklist.html")
@ExtractBy(value = "//*[@id=\"quotesearch\"]/ul/li/a",multi = true)
public class StockIdName {

    private Integer id;
    @ExtractBy(value = "//a/regex('@href','http://quote.eastmoney.com/(.*).html','1')")
    private String symbol;
    @ExtractBy(value = ">(.*)\\(.*<",type = ExtractBy.Type.Regex)
    private String name;
    @ExtractBy(value = ">.*\\((.*)\\)<",type = ExtractBy.Type.Regex)
    private Integer code;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "StockIdName{" +
                "id=" + id +
                ", symbol='" + symbol + '\'' +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
