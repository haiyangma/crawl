package com.mhy.crawl.data.model;


import java.util.Date;

/**
 * @author: decaywood
 * @date: 2015/11/23 19:14
 */
public class Stock {

    private static final String Stock_Page_PreFix = "http://xueqiu.com/S/";

    private String name; //股票名称
    private String symbol; //股票代码
    private String exchange; //前缀
    private String code;//股票号码
    private String currency_unit ;//币种
    private String current ; //当前价
    private String percentage ;//百分比
    private String change ;//涨跌幅
    private String open ;//开盘价
    private String high ;//最高
    private String low ;//最低
    private String close ;//收盘价
    private String last_close ;//昨收
    private String high52Week ;//52周最高
    private String low52week ;//52周最低
    private String volume ;//成交量
    private String volumeAverage;//平均成交量
    private String marketCapital ;//总市值
    private String eps ;//每股收益

    private String amplitude ;//振幅
    private String fall_stop ;//跌停价
    private String rise_stop ;//涨停价
    private String pe_ttm ;//动态市盈率
    private String pe_lyr ;//静态市盈率
    private String beta; //个别投资工具相对整个市场的波动
    private String totalShares ;//总股本
    private String time ;//雪球系统时间
    private String afterHours;
    private String updateAt;//更新时间
    private String dividend ;//每股股息
    private String float_shares ;//流通股本
    private String float_market_capital ;//流通值
    private String net_assets ;//每股净资产
    private String psr ;//市销率
    private String turnover_rate ;//换手
    private String amount ;//成交额


    private Date stockQueryDate = new Date(0);

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

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getVolumeAverage() {
        return volumeAverage;
    }

    public void setVolumeAverage(String volumeAverage) {
        this.volumeAverage = volumeAverage;
    }

    public String getBeta() {
        return beta;
    }

    public void setBeta(String beta) {
        this.beta = beta;
    }

    public String getAfterHours() {
        return afterHours;
    }

    public void setAfterHours(String afterHours) {
        this.afterHours = afterHours;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

    //币种
    public String getCurrency_unit() {
        return currency_unit;
    }

    //币种
    public void setCurrency_unit(String currency_unit) {
        this.currency_unit = currency_unit;
    }

    //当前价
    public String getCurrent() {
        return current;
    }

    //当前价
    public void setCurrent(String current) {
        this.current = current;
    }
    //成交量
    public String getVolume() {
        return volume;
    }

    //成交量
    public void setVolume(String volume) {
        this.volume = volume;
    }

    //涨幅百分比
    public String getPercentage() {
        return percentage;
    }

    //涨幅百分比
    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    //涨跌幅
    public String getChange() {
        return change;
    }

    //涨跌幅
    public void setChange(String change) {
        this.change = change;
    }

    //开盘价
    public String getOpen() {
        return open;
    }

    //开盘价
    public void setOpen(String open) {
        this.open = open;
    }

    //最高
    public String getHigh() {
        return high;
    }

    //最高
    public void setHigh(String high) {
        this.high = high;
    }

    //最低
    public String getLow() {
        return low;
    }

    //最低
    public void setLow(String low) {
        this.low = low;
    }

    //振幅
    public String getAmplitude() {
        return amplitude;
    }

    //换手
    public void setTurnover_rate(String turnover_rate) {
        this.turnover_rate = turnover_rate;
    }

    //换手
    public String getTurnover_rate() {
        return turnover_rate;
    }

    //振幅
    public void setAmplitude(String amplitude) {
        this.amplitude = amplitude;
    }

    //跌停价
    public String getFall_stop() {
        return fall_stop;
    }

    //跌停价
    public void setFall_stop(String fall_stop) {
        this.fall_stop = fall_stop;
    }

    //涨停价
    public String getRise_stop() {
        return rise_stop;
    }

    //涨停价
    public void setRise_stop(String rise_stop) {
        this.rise_stop = rise_stop;
    }

    //收盘价
    public String getClose() {
        return close;
    }

    //收盘价
    public void setClose(String close) {
        this.close = close;
    }

    //昨收
    public String getLast_close() {
        return last_close;
    }

    //昨收
    public void setLast_close(String last_close) {
        this.last_close = last_close;
    }

    //52周最高
    public String getHigh52Week() {
        return high52Week;
    }

    //52周最高
    public void setHigh52Week(String high52Week) {
        this.high52Week = high52Week;
    }

    //52周最低
    public String getLow52week() {
        return low52week;
    }

    //52周最低
    public void setLow52week(String low52week) {
        this.low52week = low52week;
    }

    //总市值
    public String getMarketCapital() {
        return marketCapital;
    }

    //总市值
    public void setMarketCapital(String marketCapital) {
        this.marketCapital = marketCapital;
    }

    //流通值
    public String getFloat_market_capital() {
        return float_market_capital;
    }

    //流通值
    public void setFloat_market_capital(String float_market_capital) {
        this.float_market_capital = float_market_capital;
    }

    //流通股本
    public String getFloat_shares() {
        return float_shares;
    }

    //流通股本
    public void setFloat_shares(String float_shares) {
        this.float_shares = float_shares;
    }

    //总股本
    public String getTotalShares() {
        return totalShares;
    }

    //总股本
    public void setTotalShares(String totalShares) {
        this.totalShares = totalShares;
    }

    //每股收益
    public String getEps() {
        return eps;
    }

    //每股收益
    public void setEps(String eps) {
        this.eps = eps;
    }

    //每股净资产
    public String getNet_assets() {
        return net_assets;
    }

    //每股净资产
    public void setNet_assets(String net_assets) {
        this.net_assets = net_assets;

    }
    //动态市盈率
    public String getPe_ttm() {
        return pe_ttm;
    }

    //动态市盈率
    public void setPe_ttm(String pe_ttm) {
        this.pe_ttm = pe_ttm;
    }

    //静态市盈率
    public String getPe_lyr() {
        return pe_lyr;
    }

    //静态市盈率
    public void setPe_lyr(String pe_lyr) {
        this.pe_lyr = pe_lyr;
    }

    //每股股息
    public String getDividend() {
        return dividend;
    }

    //每股股息
    public void setDividend(String dividend) {
        this.dividend = dividend;
    }

    //市销率
    public String getPsr() {
        return psr;
    }

    //市销率
    public void setPsr(String psr) {
        this.psr = psr;
    }

    //雪球系统时间
    public void setTime(String time) {
        this.time = time;
    }

    //雪球系统时间
    public String getTime() {
        return time;
    }

    public String getStockPageSite() {
        return Stock_Page_PreFix + symbol;
    }

    //成交额
    public String getAmount() {
        return amount;
    }

    //成交额
    public void setAmount(String amount) {
        this.amount = amount;
    }

    //股票收集时间（程序发起请求时间）
    public void setStockQueryDate(Date stockQueryDate) {
        this.stockQueryDate = stockQueryDate;
    }

    //股票收集时间（程序发起请求时间）
    public Date getStockQueryDate() {
        return stockQueryDate;
    }


    public Stock copy() {
        Stock stock = new Stock();
        stock.name = name;
        stock.symbol = symbol;
        stock.currency_unit = currency_unit;
        stock.current = current;
        stock.volume = volume;
        stock.percentage = percentage;
        stock.change = change;
        stock.open = open;
        stock.high = high;
        stock.low = low;
        stock.amplitude = amplitude;
        stock.rise_stop = rise_stop;
        stock.fall_stop = fall_stop;
        stock.close = close;
        stock.last_close = last_close;
        stock.high52Week = high52Week;
        stock.low52week = low52week;
        stock.marketCapital = marketCapital;
        stock.float_market_capital = float_market_capital;
        stock.float_shares = float_shares;
        stock.totalShares = totalShares;
        stock.eps = eps;
        stock.net_assets = net_assets;
        stock.pe_ttm = pe_ttm;
        stock.pe_lyr = pe_lyr;
        stock.dividend = dividend;
        stock.psr = psr;
        stock.time = time;
        stock.amount = amount;
        stock.turnover_rate = turnover_rate;
        return stock;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "name='" + name + '\'' +
                ", symbol='" + symbol + '\'' +
                ", exchange='" + exchange + '\'' +
                ", code=" + code +
                ", currency_unit='" + currency_unit + '\'' +
                ", current='" + current + '\'' +
                ", percentage='" + percentage + '\'' +
                ", change='" + change + '\'' +
                ", open='" + open + '\'' +
                ", high='" + high + '\'' +
                ", low='" + low + '\'' +
                ", close='" + close + '\'' +
                ", last_close='" + last_close + '\'' +
                ", high52Week='" + high52Week + '\'' +
                ", low52week='" + low52week + '\'' +
                ", volume='" + volume + '\'' +
                ", volumeAverage='" + volumeAverage + '\'' +
                ", marketCapital='" + marketCapital + '\'' +
                ", eps='" + eps + '\'' +
                ", amplitude='" + amplitude + '\'' +
                ", fall_stop='" + fall_stop + '\'' +
                ", rise_stop='" + rise_stop + '\'' +
                ", pe_ttm='" + pe_ttm + '\'' +
                ", pe_lyr='" + pe_lyr + '\'' +
                ", beta='" + beta + '\'' +
                ", totalShares='" + totalShares + '\'' +
                ", time=" + time +
                ", afterHours='" + afterHours + '\'' +
                ", updateAt='" + updateAt + '\'' +
                ", dividend='" + dividend + '\'' +
                ", float_shares='" + float_shares + '\'' +
                ", float_market_capital='" + float_market_capital + '\'' +
                ", net_assets='" + net_assets + '\'' +
                ", psr='" + psr + '\'' +
                ", turnover_rate='" + turnover_rate + '\'' +
                ", amount='" + amount + '\'' +
                ", stockQueryDate=" + stockQueryDate +
                '}';
    }
}
