package com.example.connecttlus.Object;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class getUser {
    private int event;
    @SerializedName("items")
    private User user;

    public getUser(int event, User user) {
        this.event = event;
        this.user = user;
    }

    public int getEvent() {
        return event;
    }

    public void setEvent(int event) {
        this.event = event;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
