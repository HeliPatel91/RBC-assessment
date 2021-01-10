package com.rbc.dow.jones.index.entity;

import java.io.Serializable;
import java.util.Objects;

public class WeeklyIndexId implements Serializable {

    private String stock;

    private String date;

    public WeeklyIndexId(){}

    public WeeklyIndexId(String stock, String date) {
        this.stock = stock;
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeeklyIndexId that = (WeeklyIndexId) o;
        return Objects.equals(stock, that.stock) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stock, date);
    }
}
