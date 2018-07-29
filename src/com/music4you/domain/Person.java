package com.music4you.domain;

import java.io.Serializable;
import java.util.Date;

public class Person extends Leaser implements Serializable {
    // Declare attributes
    private Date dateOfBirth;
    private String firstName;
    private static final int LEASE_LENGTH = 6; //minimal lease length in months

    // Constructor
    public Person (String inName, String inFirstName, Date inDateOfBirth) {
        super(inName);
        this.dateOfBirth = inDateOfBirth;
        this.firstName = inFirstName;
    }

    /**
     * Setting & getting the date of birth of the client
     */
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }


    /**
     * Setting & getting the first name of the client
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

}
