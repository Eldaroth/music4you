package com.music4you.domain;

import java.io.Serializable;

/**
 * Helper class to display addresses
 *
 * @author Eldaroth
 * @version 1.0
 */

public class Address implements Serializable{
    // Declare attributes
    private String street;
    private String zip;
    private String city;

    //Constructor
    public Address(String inStreet, String inZip, String inCity) {
        this.street = inStreet;
        this.zip = inZip;
        this.city = inCity;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

//    @Override
//    public String toString() {
//        return "Address: " + this.street + ", " + this.zip + " " + this.city;
//    }

}
