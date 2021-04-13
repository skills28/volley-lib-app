package org.example.volleyforjavacrud.entity;

public class Country {

    private Integer id;
    private String countryName;
    private String capitalName;

    public Country() {
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public void setCapitalName(String capitalName) {
        this.capitalName = capitalName;
    }

    public Integer getId() {
        return id;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getCapitalName() {
        return capitalName;
    }

}
