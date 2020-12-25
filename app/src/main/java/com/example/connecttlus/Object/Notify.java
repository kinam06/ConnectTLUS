package com.example.connecttlus.Object;

public class Notify {
    private String id;
    private String content;
    private String createat;
    private String attachfile;
    private String isnew;
    private String detailname;

    public Notify(String id, String content, String createat, String attachfile, String isnew, String detailname) {
        this.id = id;
        this.content = content;
        this.createat = createat;
        this.attachfile = attachfile;
        this.isnew = isnew;
        this.detailname = detailname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateat() {
        return createat;
    }

    public void setCreateat(String createat) {
        this.createat = createat;
    }

    public String getAttachfile() {
        return attachfile;
    }

    public void setAttachfile(String attachfile) {
        this.attachfile = attachfile;
    }

    public String getIsnew() {
        return isnew;
    }

    public void setIsnew(String isnew) {
        this.isnew = isnew;
    }

    public String getDetailname() {
        return detailname;
    }

    public void setDetailname(String detailname) {
        this.detailname = detailname;
    }
}
