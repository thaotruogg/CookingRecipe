package com.thaotruogg.cookingrecipe;

public class Foods {
    private String id, title, image, level, nglieuchinh, categry;
    private int time, khauphan;

    public Foods(){}

    public Foods(String id, String title, String image, String level, int time, String nglieuchinh, int khauphan) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.level = level;
        this.time = time;
        this.nglieuchinh = nglieuchinh;
        this.khauphan = khauphan;
    }

    public String getCategry() {
        return categry;
    }

    public void setCategry(String categry) {
        this.categry = categry;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getKhauphan() {
        return khauphan;
    }

    public void setKhauphan(int khauphan) {
        this.khauphan = khauphan;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getNglieuchinh() {
        return nglieuchinh;
    }

    public void setNglieuchinh(String nglieuchinh) {
        this.nglieuchinh = nglieuchinh;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
