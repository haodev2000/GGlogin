package com.example.crud;

import java.io.Serializable;

public class Noti implements Serializable {
    private String tv_tieude;

    public Noti(String tv_tieude) {
        this.tv_tieude = tv_tieude;
    }

    public String getTv_tieude() {
        return tv_tieude;
    }

    public void setTv_tieude(String tv_tieude) {
        this.tv_tieude = tv_tieude;
    }

    @Override
    public String toString() {
        return "Noti{" +
                "tv_tieude='" + tv_tieude + '\'' +
                '}';
    }
}
