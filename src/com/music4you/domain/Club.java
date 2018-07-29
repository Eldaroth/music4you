package com.music4you.domain;

import java.util.Date;

public class Club extends Leaser {
    // Declare attributes
    private String contactPerson;
    private Date foundation;
    private static final int LEASE_LENGTH = 1; //minimal lease length in months

    // Constructor
    public Club (String inName, String inContactPerson, Date inFoundation) {
        super(inName);
        this.contactPerson = inContactPerson;
        this.foundation = inFoundation;
    }

    /*
     * Setting & getting the contact person
     */
    public void setContactPerson(String contactPerson){
        this.contactPerson = contactPerson;
    }

    public String getContactPerson() {
        return contactPerson;
    }


    /*
     * Setting & getting the date of the foundation of the club
     */
    public void setFoundation(Date foundation){
        this.foundation = foundation;
    }

    public Date getFoundation(){
        return foundation;
    }

}
