package com.example.connecttlus.Object;

public class Sche {
    private String sub_name;
    private String schedule;

    public Sche(String sub_name, String schedule) {
        this.sub_name = sub_name;
        this.schedule = schedule;
    }

    public String getSub_name() {
        return sub_name;
    }

    public void setSub_name(String sub_name) {
        this.sub_name = sub_name;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }
}
