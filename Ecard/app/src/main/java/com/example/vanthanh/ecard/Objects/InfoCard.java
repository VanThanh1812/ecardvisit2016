package com.example.vanthanh.ecard.Objects;

/**
 * Created by Van Thanh on 6/28/2016.
 */
public class InfoCard {
        private String ec_id;
        private String ec_imgCongty;
        private String ec_imgBackground;
        private String ec_fullname;
        private String ec_company;
        private String ec_level;
        private String ec_email;
        private String ec_address;
        private String ec_phone;
        private String ec_fbhomework;


        public InfoCard() {
        }

        public InfoCard(String ec_id, String ec_imgCongty, String ec_imgBackground, String ec_fullname, String ec_company, String ec_level, String ec_email, String ec_address, String ec_phone, String ec_fbhomework) {
            this.ec_id = ec_id;
            this.ec_imgCongty = ec_imgCongty;
            this.ec_imgBackground = ec_imgBackground;
            this.ec_fullname = ec_fullname;
            this.ec_company = ec_company;
            this.ec_level = ec_level;
            this.ec_email = ec_email;
            this.ec_address = ec_address;
            this.ec_phone = ec_phone;
            this.ec_fbhomework = ec_fbhomework;
        }


    public String getEc_id() {
        return ec_id;
    }

    public void setEc_id(String ec_id) {
        this.ec_id = ec_id;
    }

    public String getEc_imgCongty() {
        return ec_imgCongty;
    }

    public void setEc_imgCongty(String ec_imgCongty) {
        this.ec_imgCongty = ec_imgCongty;
    }

    public String getEc_imgBackground() {
        return ec_imgBackground;
    }

    public void setEc_imgBackground(String ec_imgBackground) {
        this.ec_imgBackground = ec_imgBackground;
    }

    public String getEc_fullname() {
        return ec_fullname;
    }

    public void setEc_fullname(String ec_fullname) {
        this.ec_fullname = ec_fullname;
    }

    public String getEc_company() {
        return ec_company;
    }

    public void setEc_company(String ec_company) {
        this.ec_company = ec_company;
    }

    public String getEc_level() {
        return ec_level;
    }

    public void setEc_level(String ec_level) {
        this.ec_level = ec_level;
    }

    public String getEc_email() {
        return ec_email;
    }

    public void setEc_email(String ec_email) {
        this.ec_email = ec_email;
    }

    public String getEc_address() {
        return ec_address;
    }

    public void setEc_address(String ec_address) {
        this.ec_address = ec_address;
    }

    public String getEc_phone() {
        return ec_phone;
    }

    public void setEc_phone(String ec_phone) {
        this.ec_phone = ec_phone;
    }

    public String getEc_fbhomework() {
        return ec_fbhomework;
    }

    public void setEc_fbhomework(String ec_fbhomework) {
        this.ec_fbhomework = ec_fbhomework;
    }
}
