package com.example.connecttlus.Object;

public class NoiDung {
    private String idsub;
    private String item;
    private String link;
    private String iduser;
    private String detailname;

    public NoiDung(String idsub, String item, String link, String iduser, String detailname) {
        this.idsub = idsub;
        this.item = item;
        this.link = link;
        this.iduser = iduser;
        this.detailname = detailname;
    }

    public String getIdsub() {
        return idsub;
    }

    public void setIdsub(String idsub) {
        this.idsub = idsub;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getIduser() {
        return iduser;
    }

    public void setIduser(String iduser) {
        this.iduser = iduser;
    }

    public String getDetailname() {
        return detailname;
    }

    public void setDetailname(String detailname) {
        this.detailname = detailname;
    }
}
