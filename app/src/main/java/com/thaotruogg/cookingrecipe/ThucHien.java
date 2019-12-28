package com.thaotruogg.cookingrecipe;

public class ThucHien {
    int idThuchien;
    String acThuchien;

    public ThucHien() {
    }

    public ThucHien(int idThuchien, String acThuchien) {
        this.idThuchien = idThuchien;
        this.acThuchien = acThuchien;
    }

    public int getIdThuchien() {
        return idThuchien;
    }

    public void setIdThuchien(int idThuchien) {
        this.idThuchien = idThuchien;
    }

    public String getAcThuchien() {
        return acThuchien;
    }

    public void setAcThuchien(String acThuchien) {
        this.acThuchien = acThuchien;
    }
}
