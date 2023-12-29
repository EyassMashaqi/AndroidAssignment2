package com.example.assigmnent2;

public class League {
    private String name;
    private String countryid;

    public League(String name, String countryid) {
        this.name = name;
        this.countryid = countryid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryid() {
        return countryid;
    }

    public void setCountryid(String countryid) {
        this.countryid = countryid;
    }
}
