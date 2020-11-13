package com.revature.beans;

public class Pokemon {

    private int iD;
    private String name;

    public Pokemon(){
        super();
    }

    public Pokemon(int iD, String name) {
        super();
        this.iD = iD;
        this.name = name;
    }

    public int getiD() {
        return iD;
    }

    public void setiD(int iD) {
        this.iD = iD;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Pokemon [iD=" + iD + ", name=" + name + "]";
    }


}