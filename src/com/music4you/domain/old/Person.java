package com.music4you.domain.old;

import com.music4you.domain.Leaser;

import java.io.Serializable;
import java.time.LocalDate;

public class Person extends Leaser implements Serializable {
    // Declare attributes
    private LocalDate dateOfBirth;
    private String firstName;
    private static final int LEASE_LENGTH = 6; //minimal lease length in months

    // Constructor
    public Person (String inName, String inFirstName, LocalDate inDateOfBirth) {
        super(inName);
        this.dateOfBirth = inDateOfBirth;
        this.firstName = inFirstName;
    }

    /**
     * Setting & getting the date of birth of the client
     */
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getDateOfBirth() {
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

    @Override
    public String toString(){
        return getId() + " " + getName() + ", " + firstName + " " + getAddress() +
                " " + getContact() + " "  + getDateOfBirth();
    }

}
