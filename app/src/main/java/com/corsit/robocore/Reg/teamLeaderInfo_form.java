package com.corsit.robocore.Reg;

public class teamLeaderInfo_form {

    String teamLeader_name, teamName, teamLeader_email, teamLeader_contact, teamLeader_college;

    public teamLeaderInfo_form() {
    }

    public teamLeaderInfo_form(String teamLeader_name, String teamName, String teamLeader_email, String teamLeader_contact, String teamLeader_college) {
        this.teamLeader_name = teamLeader_name;
        this.teamName = teamName;
        this.teamLeader_email = teamLeader_email;
        this.teamLeader_contact = teamLeader_contact;
        this.teamLeader_college = teamLeader_college;
    }

    public String getTeamLeader_name() {
        return teamLeader_name;
    }

    public void setTeamLeader_name(String teamLeader_name) {
        this.teamLeader_name = teamLeader_name;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamLeader_email() {
        return teamLeader_email;
    }

    public void setTeamLeader_email(String teamLeader_email) {
        this.teamLeader_email = teamLeader_email;
    }

    public String getTeamLeader_contact() {
        return teamLeader_contact;
    }

    public void setTeamLeader_contact(String teamLeader_contact) {
        this.teamLeader_contact = teamLeader_contact;
    }

    public String getTeamLeader_college() {
        return teamLeader_college;
    }

    public void setTeamLeader_college(String teamLeader_college) {
        this.teamLeader_college = teamLeader_college;
    }
}
