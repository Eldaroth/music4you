package com.music4you.domain;

import java.io.Serializable;
import java.util.Date;

public class Club extends Leaser implements Serializable {
    // Declare attributes
    private String contactPerson;
    private static final int LEASE_LENGTH = 1; //minimal lease length in months

    // Constructor
    public Club (String inName, String inContactPerson) {
        super(inName);
        this.contactPerson = inContactPerson;
    }

    /**
     * Setting & getting the contact person
     */
    public void setContactPerson(String contactPerson){
        this.contactPerson = contactPerson;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    @Override
    public String toString(){
        return getId() + " " + getName() + " " + getAddress() +
                " " + getEmail() + " " + getPhoneNumber() + " " + getContactPerson();
    }
}
