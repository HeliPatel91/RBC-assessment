package com.rbc.dow.jones.index.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.math.BigDecimal;
import java.util.Date;

@Entity(name = "WEEKLY_INDEX")
@IdClass(WeeklyIndexId.class)
public class WeeklyIndex {

    public WeeklyIndex() {}

    //int because quarter can be 1-4
    private int quarter;

    //Selected stock and date as composite primary key as the data set can not have multiple entry
    //for the same stock and date
    @Id
    private String stock;

    @Id
    //TODO: type can be changed to java.sql.Date with format of yyyy-mm--dd
    private String date;

    //BigDecimal stores digits as is
    private BigDecimal open;

    private BigDecimal high;

    private BigDecimal low;

    private BigDecimal close;

    //Volume can not be in fractions and Long is suitable for it
    private Long volume;

    //double to preserves precisions
    @Column(name = "percent_change_price")
    private double percentChangePrice;

    //String as data set has few empty values for this field
    @Column(name = "percent_change_volume_over_last_wk")
    private String percentChangeVolumeOverLastWk;

    //String as data set has few empty values for this field
    @Column(name = "previous_weeks_volume")
    private String previousWeeksVolume;

    //BigDecimal stores digits as is
    @Column(name = "next_weeks_open")
    private BigDecimal nextWeeksOpen;

    @Column(name = "next_weeks_close")
    private BigDecimal nextWeeksClose;

    @Column(name = "percent_change_next_weeks_price")
    private double percentChangeNextWeeksPrice;

    @Column(name = "days_to_next_dividend")
    private int daysToNextDividend;

    @Column(name = "percent_return_next_dividend")
    private double percentReturnNextDividend;

    public WeeklyIndex(int quarter, String stock, String date, BigDecimal open, BigDecimal high, BigDecimal low, BigDecimal close, Long volume, double percentChangePrice, String percentChangeVolumeOverLastWk, String previousWeeksVolume, BigDecimal nextWeeksOpen, BigDecimal nextWeeksClose, double percentChangeNextWeeksPrice, int daysToNextDividend, double percentReturnNextDividend) {
        this.quarter = quarter;
        this.stock = stock;
        this.date = date;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
        this.percentChangePrice = percentChangePrice;
        this.percentChangeVolumeOverLastWk = percentChangeVolumeOverLastWk;
        this.previousWeeksVolume = previousWeeksVolume;
        this.nextWeeksOpen = nextWeeksOpen;
        this.nextWeeksClose = nextWeeksClose;
        this.percentChangeNextWeeksPrice = percentChangeNextWeeksPrice;
        this.daysToNextDividend = daysToNextDividend;
        this.percentReturnNextDividend = percentReturnNextDividend;
    }

    public int getQuarter() {
        return quarter;
    }

    public void setQuarter(int quarter) {
        this.quarter = quarter;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public BigDecimal getOpen() {
        return open;
    }

    public void setOpen(BigDecimal open) {
        this.open = open;
    }

    public BigDecimal getHigh() {
        return high;
    }

    public void setHigh(BigDecimal high) {
        this.high = high;
    }

    public BigDecimal getLow() {
        return low;
    }

    public void setLow(BigDecimal low) {
        this.low = low;
    }

    public BigDecimal getClose() {
        return close;
    }

    public void setClose(BigDecimal close) {
        this.close = close;
    }

    public Long getVolume() {
        return volume;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }

    public double getPercentChangePrice() {
        return percentChangePrice;
    }

    public void setPercentChangePrice(double percentChangePrice) {
        this.percentChangePrice = percentChangePrice;
    }

    public String getPercentChangeVolumeOverLastWk() {
        return percentChangeVolumeOverLastWk;
    }

    public void setPercentChangeVolumeOverLastWk(String percentChangeVolumeOverLastWk) {
        this.percentChangeVolumeOverLastWk = percentChangeVolumeOverLastWk;
    }

    public String getPreviousWeeksVolume() {
        return previousWeeksVolume;
    }

    public void setPreviousWeeksVolume(String previousWeeksVolume) {
        this.previousWeeksVolume = previousWeeksVolume;
    }

    public BigDecimal getNextWeeksOpen() {
        return nextWeeksOpen;
    }

    public void setNextWeeksOpen(BigDecimal nextWeeksOpen) {
        this.nextWeeksOpen = nextWeeksOpen;
    }

    public BigDecimal getNextWeeksClose() {
        return nextWeeksClose;
    }

    public void setNextWeeksClose(BigDecimal nextWeeksClose) {
        this.nextWeeksClose = nextWeeksClose;
    }

    public double getPercentChangeNextWeeksPrice() {
        return percentChangeNextWeeksPrice;
    }

    public void setPercentChangeNextWeeksPrice(double percentChangeNextWeeksPrice) {
        this.percentChangeNextWeeksPrice = percentChangeNextWeeksPrice;
    }

    public int getDaysToNextDividend() {
        return daysToNextDividend;
    }

    public void setDaysToNextDividend(int daysToNextDividend) {
        this.daysToNextDividend = daysToNextDividend;
    }

    public double getPercentReturnNextDividend() {
        return percentReturnNextDividend;
    }

    public void setPercentReturnNextDividend(double percentReturnNextDividend) {
        this.percentReturnNextDividend = percentReturnNextDividend;
    }
}
