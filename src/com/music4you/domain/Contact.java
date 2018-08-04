package com.music4you.domain;

import java.io.Serializable;

/**
 * Helper class for creating contact details
 *
 * @author Eldaroth
 * @version 1.0
 */

public class Contact implements Serializable{
    // Declare attributes
    private String phoneNumber;
    private String email;

    // Constructor
    public Contact(String phoneNumber, String email) {
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    @Override
//    public String toString() {
//        return "Contact: " + this.phoneNumber + " / " + this.email;
//    }
}
