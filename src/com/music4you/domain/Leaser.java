package com.music4you.domain;

import java.io.Serializable;

/**
 * Class describes someone who is renting an instrument
 *
 * @author Eldaroth
 * @version 1.0
 */

public class Leaser implements Serializable {
    // Declare attributes
    private static int nextId = 1;

    private int id;
    private String name;
    private String address;
    private String email;
    private int phoneNumber;

    // Constructor
    public Leaser(String inName) {
        this.id = Leaser.nextId;
        this.name = inName;
        this.address = "missing";
        this.email = "missing";
        this.phoneNumber = 0;
        nextId++;
    }

    /**
     * setting & getting clients unique number/id
     */
    public void setId(int id) {
        this.id = id;
    }

    public int id() {
        return id;
    }


    /**
     * Setting & getting clients name
     */
    public void setName (String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    /**
     * setting & getting clients address, e-mail & phone no.
     */
    public void setAddress(String street, String houseNumber, String zipCity) {
        this.address = street + " " + houseNumber + ", " + zipCity;
    }

    public String getAddress() {
        return address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    /**
     *returns short summary of personal details of client
     */
    @Override
    public String toString() {
        return this.id + " / " + this.name;
    }
}
