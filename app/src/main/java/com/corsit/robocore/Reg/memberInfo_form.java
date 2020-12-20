package com.corsit.robocore.Reg;

public class memberInfo_form {

    String member_name, member_email, member_contact;

    public memberInfo_form() {
    }

    public memberInfo_form(String member_name, String member_email, String member_contact) {
        this.member_name = member_name;
        this.member_email = member_email;
        this.member_contact = member_contact;
    }

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public String getMember_email() {
        return member_email;
    }

    public void setMember_email(String member_email) {
        this.member_email = member_email;
    }

    public String getMember_contact() {
        return member_contact;
    }

    public void setMember_contact(String member_contact) {
        this.member_contact = member_contact;
    }

}
