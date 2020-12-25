package com.example.connecttlus.Object;

public class User {
    private String iduser;
    private String username;
    private String phone;
    private String idstudent;
    private String password;
    private String detailname;
    private String course;
    private String lop;
    private String avartar;
    private String email;
    private String sex;
    private String address;
    private String permission;
    private String dateofbirth;

    public User(String iduser, String username, String phone, String idstudent, String password, String detailname, String course, String lop, String avartar, String email, String sex, String address, String permission, String dateofbirth) {
        this.iduser = iduser;
        this.username = username;
        this.phone = phone;
        this.idstudent = idstudent;
        this.password = password;
        this.detailname = detailname;
        this.course = course;
        this.lop = lop;
        this.avartar = avartar;
        this.email = email;
        this.sex = sex;
        this.address = address;
        this.permission = permission;
        this.dateofbirth = dateofbirth;
    }

    public String getIduser() {
        return iduser;
    }

    public void setIduser(String iduser) {
        this.iduser = iduser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdstudent() {
        return idstudent;
    }

    public void setIdstudent(String idstudent) {
        this.idstudent = idstudent;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDetailname() {
        return detailname;
    }

    public void setDetailname(String detailname) {
        this.detailname = detailname;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }

    public String getAvartar() {
        return avartar;
    }

    public void setAvartar(String avartar) {
        this.avartar = avartar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(String dateofbirth) {
        this.dateofbirth = dateofbirth;
    }
}