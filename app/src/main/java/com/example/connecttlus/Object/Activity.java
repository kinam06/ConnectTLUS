package com.example.connecttlus.Object;

public class Activity {
    private String idac;
    private String nameactivity;
    private String createat;
    private String urlfile;
    private String idalbum;
    private String iduser;
    private String album;
    private String detailname;

    public Activity(String idac, String nameactivity, String createat, String urlfile, String idalbum, String iduser, String album, String detailname) {
        this.idac = idac;
        this.nameactivity = nameactivity;
        this.createat = createat;
        this.urlfile = urlfile;
        this.idalbum = idalbum;
        this.iduser = iduser;
        this.album = album;
        this.detailname = detailname;
    }

    public String getIdac() {
        return idac;
    }

    public void setIdac(String idac) {
        this.idac = idac;
    }

    public String getNameactivity() {
        return nameactivity;
    }

    public void setNameactivity(String nameactivity) {
        this.nameactivity = nameactivity;
    }

    public String getCreateat() {
        return createat;
    }

    public void setCreateat(String createat) {
        this.createat = createat;
    }

    public String getUrlfile() {
        return urlfile;
    }

    public void setUrlfile(String urlfile) {
        this.urlfile = urlfile;
    }

    public String getIdalbum() {
        return idalbum;
    }

    public void setIdalbum(String idalbum) {
        this.idalbum = idalbum;
    }

    public String getIduser() {
        return iduser;
    }

    public void setIduser(String iduser) {
        this.iduser = iduser;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getDetailname() {
        return detailname;
    }

    public void setDetailname(String detailname) {
        this.detailname = detailname;
    }
}
