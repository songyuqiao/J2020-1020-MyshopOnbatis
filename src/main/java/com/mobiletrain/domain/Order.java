package com.mobiletrain.domain;

public class Order {
    private int id;
    private int uid;
    private int money;
    private String status;
    private String time;
    private int aid;

    @Override
    public String toString() {
        return "order{" +
                "id=" + id +
                ", uid=" + uid +
                ", money=" + money +
                ", status='" + status + '\'' +
                ", time='" + time + '\'' +
                ", aid=" + aid +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }
}
