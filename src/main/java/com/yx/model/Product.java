package com.yx.model;

public class Product {
    private Integer prodId;

    private String prodName;

    private String procPicPath;

    private Integer cateId;

    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getProdId() {
        return prodId;
    }

    public void setProdId(Integer prodId) {
        this.prodId = prodId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName == null ? null : prodName.trim();
    }

    public String getProcPicPath() {
        return procPicPath;
    }

    public void setProcPicPath(String procPicPath) {
        this.procPicPath = procPicPath == null ? null : procPicPath.trim();
    }

    public Integer getCateId() {
        return cateId;
    }

    public void setCateId(Integer cateId) {
        this.cateId = cateId;
    }
}