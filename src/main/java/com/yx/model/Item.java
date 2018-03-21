package com.yx.model;

public class Item {
    private Integer itemId;

    private Integer prodId;

    private Double price;

    private String itemDesc;

    private String itemPicPath;

    private byte[] itemPic;
    private ProductWithBLOBs productWithBLOBs;

    public ProductWithBLOBs getProductWithBLOBs() {
        return productWithBLOBs;
    }

    public void setProductWithBLOBs(ProductWithBLOBs productWithBLOBs) {
        this.productWithBLOBs = productWithBLOBs;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getProdId() {
        return prodId;
    }

    public void setProdId(Integer prodId) {
        this.prodId = prodId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc == null ? null : itemDesc.trim();
    }

    public String getItemPicPath() {
        return itemPicPath;
    }

    public void setItemPicPath(String itemPicPath) {
        this.itemPicPath = itemPicPath == null ? null : itemPicPath.trim();
    }

    public byte[] getItemPic() {
        return itemPic;
    }

    public void setItemPic(byte[] itemPic) {
        this.itemPic = itemPic;
    }
}