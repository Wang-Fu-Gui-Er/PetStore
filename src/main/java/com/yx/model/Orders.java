package com.yx.model;

import java.util.Date;

public class Orders extends OrdersKey {
    private Date orderDate;

    private Integer totleprice;

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Integer getTotleprice() {
        return totleprice;
    }

    public void setTotleprice(Integer totleprice) {
        this.totleprice = totleprice;
    }
}