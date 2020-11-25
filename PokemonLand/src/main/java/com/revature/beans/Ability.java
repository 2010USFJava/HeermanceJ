package com.revature.beans;

public class Ability {

    private int aid;
    private String aname;
    private String adescription;

    public Ability() {
        super();
    }


    public Ability(int aid, String aname, String adescription) {
        super();
        this.aid = aid;
        this.aname = aname;
        this.adescription = adescription;
    }


    public int getAid() {
        return aid;
    }
    public void setAid(int aid) {
        this.aid = aid;
    }
    public String getAname() {
        return aname;
    }
    public void setAname(String aname) {
        this.aname = aname;
    }
    public String getAdescription() {
        return adescription;
    }
    public void setAdescription(String adescription) {
        this.adescription = adescription;
    }
    @Override
    public String toString() {
        return "Ability [aid=" + aid + ", aname=" + aname + ", adescription=" + adescription + "]";
    }
}