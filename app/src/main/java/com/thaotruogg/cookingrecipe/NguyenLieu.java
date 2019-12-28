package com.thaotruogg.cookingrecipe;

public class NguyenLieu {
    private String id, ten, donvi, soluong;

    public NguyenLieu() {
    }

    public NguyenLieu(String id, String ten, String donvi, String soluong) {
        this.id = id;
        this.ten = ten;
        this.donvi = donvi;
        this.soluong = soluong;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getDonvi() {
        return donvi;
    }

    public void setDonvi(String donvi) {
        this.donvi = donvi;
    }

    public String getSoluong() {
        return soluong;
    }

    public void setSoluong(String soluong) {
        this.soluong = soluong;
    }
}
