package com.yx.model;

public class ProductWithBLOBs extends Product {
    private String prodDesc;

    private byte[] procPic;

    private Category category;

    @Override
    public Category getCategory() {
        return category;
    }

    @Override
    public void setCategory(Category category) {
        this.category = category;
    }

    public String getProdDesc() {
        return prodDesc;
    }

    public void setProdDesc(String prodDesc) {
        this.prodDesc = prodDesc == null ? null : prodDesc.trim();
    }

    public byte[] getProcPic() {
        return procPic;
    }

    public void setProcPic(byte[] procPic) {
        this.procPic = procPic;
    }
}