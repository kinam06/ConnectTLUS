package com.example.connecttlus.Object;

public class Doc {
    private String mamon;
    private String tenmon;
    private String id_mon;
    private String urlanh;

    public Doc(String mamon, String tenmon, String id_mon, String urlanh) {
        this.mamon = mamon;
        this.tenmon = tenmon;
        this.id_mon = id_mon;
        this.urlanh = urlanh;
    }

    public String getMamon() {
        return mamon;
    }

    public void setMamon(String mamon) {
        this.mamon = mamon;
    }

    public String getTenmon() {
        return tenmon;
    }

    public void setTenmon(String tenmon) {
        this.tenmon = tenmon;
    }

    public String getId_mon() {
        return id_mon;
    }

    public void setId_mon(String id_mon) {
        this.id_mon = id_mon;
    }

    public String getUrlanh() {
        return urlanh;
    }

    public void setUrlanh(String urlanh) {
        this.urlanh = urlanh;
    }
}
