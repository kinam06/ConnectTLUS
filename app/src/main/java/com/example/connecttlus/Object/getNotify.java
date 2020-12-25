package com.example.connecttlus.Object;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class getNotify {
    private int total;
    private int totalpage;
    private int page;
    @SerializedName("items")
    private ArrayList<Notify> items;

    public getNotify(int total, int totalpage, int page, ArrayList<Notify> items) {
        this.total = total;
        this.totalpage = totalpage;
        this.page = page;
        this.items = items;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotalpage() {
        return totalpage;
    }

    public void setTotalpage(int totalpage) {
        this.totalpage = totalpage;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public ArrayList<Notify> getItems() {
        return items;
    }

    public void setItems(ArrayList<Notify> items) {
        this.items = items;
    }
}

