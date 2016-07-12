package com.example.vanthanh.yourcardvisit.model;

/**
 * Created by Van Thanh on 7/11/2016.
 */
public class Data_Info {
    private String card_name;
    private String card_congty;
    private String card_sodienthoai;
    private String card_diachi;
    private String card_chucvu;
    private String card_email;

    private String linklogo;
    private String linkbackground;
    public Data_Info() {
    }

    public String getCard_name() {
        return card_name;
    }

    public void setCard_name(String card_name) {
        this.card_name = card_name;
    }

    public String getCard_congty() {
        return card_congty;
    }

    public void setCard_congty(String card_congty) {
        this.card_congty = card_congty;
    }

    public String getCard_sodienthoai() {
        return card_sodienthoai;
    }

    public void setCard_sodienthoai(String card_sodienthoai) {
        this.card_sodienthoai = card_sodienthoai;
    }

    public String getCard_diachi() {
        return card_diachi;
    }

    public void setCard_diachi(String card_diachi) {
        this.card_diachi = card_diachi;
    }

    public String getCard_chucvu() {
        return card_chucvu;
    }

    public void setCard_chucvu(String card_chucvu) {
        this.card_chucvu = card_chucvu;
    }

    public String getCard_email() {
        return card_email;
    }

    public void setCard_email(String card_email) {
        this.card_email = card_email;
    }

    public Data_Info(String card_name, String card_congty, String card_sodienthoai, String card_diachi, String card_chucvu, String card_email) {
        this.card_name = card_name;
        this.card_congty = card_congty;
        this.card_sodienthoai = card_sodienthoai;
        this.card_diachi = card_diachi;
        this.card_chucvu = card_chucvu;
        this.card_email = card_email;
    }

    public String getLinklogo() {
        return linklogo;
    }

    public void setLinklogo(String linklogo) {
        this.linklogo = linklogo;
    }

    public String getLinkbackground() {
        return linkbackground;
    }

    public void setLinkbackground(String linkbackground) {
        this.linkbackground = linkbackground;
    }

    public Data_Info(String linklogo, String linkbackground) {
        this.linklogo = linklogo;
        this.linkbackground = linkbackground;
    }
}
