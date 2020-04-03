package com.chathome;

public class Home{
    private String Owner; //房主
    private String[] Players = new String[1024]; //房间成员
    private String name;//房间名
    private int nums = 0;
    public int getNums() {
        return nums;
    }

    public void setNums(int nums) {
        this.nums = nums;
    }

    Home(String owner, String name){
        this.Owner = owner; //房主
        this.name = name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setOwner(String owner) {
        Owner = owner;
    }

    public void setPlayers(String[] players) {
        Players = players;
    }

    public String getOwner() {
        return Owner;
    }

    public String[] getPlayers() {
        return Players;
    }
}
