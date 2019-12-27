package com.example.newsAPP.bean;

public class UserBean {

    /**
     * status : success
     * randomID : 23
     * randomName : hello
     */

    private String status;
    private String randomID;
    private String randomName;

    public void setStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }
    public void setRandomID(String randomID) {
        this.randomID = randomID;
    }
    public String getRandomID(){
        return randomID;
    }
    public void setRandomName(String randomName){
        this.randomName = randomName;
    }
    public String getRandomName(){
        return randomName;
    }
}
