package com.example.theninethhole.Model;

public class User {
    private String name,membershipNo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMembershipNo() {
        return membershipNo;
    }

    public void setMembershipNo(String membershipNo) {
        this.membershipNo = membershipNo;
    }

    public User() {
    }

    public User(String name, String membershipNo) {
        this.name = name;
        this.membershipNo = membershipNo;
    }
}
