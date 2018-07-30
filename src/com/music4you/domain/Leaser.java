package com.music4you.domain;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Class describes someone who is renting an instrument
 *
 * @author Eldaroth
 * @version 1.0
 */

public class Leaser implements Serializable {
    // Declare attributes
    private static final int LEASE_LENGTH_CLUB = 1; //minimal lease length in months for a club
    private static final int LEASE_LENGTH_PERSON = 6; //minimal lease length in months for a person
    //private static int nextId = 1;

    private int id;
    private String name;
    private String firstName;
    private LocalDate dateOfBirth;
    private Address address;
    private Contact contact;
    private String contactPerson;
    private int leaseLength;

    // Constructor
    public Leaser(String inName) {
        this.id = 0;
        this.name = inName;
        this.firstName = "";
        this.dateOfBirth = null;
        this.address = new Address("",0,"");
        this.contact = new Contact("","");
        //nextId++;
    }

    // Constructor for a person as Leaser
    public Leaser(String inName, String inFirstName, LocalDate inDateOfBirth) {
        this(inName);
        this.firstName = inFirstName;
        this.dateOfBirth = inDateOfBirth;
        this.leaseLength = LEASE_LENGTH_PERSON;
    }

    // Constructor for a club as Leaser
    public Leaser(String inName, String inContactPerson) {
        this(inName);
        this.contactPerson = inContactPerson;
        this.leaseLength = LEASE_LENGTH_CLUB;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName (String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String printClub() {
        return this.id + " / " + this.name + ", Contact person: " + this.contactPerson + "\n"
                + "Contact details: " + contact.getPhoneNumber() + " / " + contact.getEmail() + "\n"
                + "Address: " + address.getStreet() + ", " + address.getZip() + " " + address.getCity();
    }

    public String printPerson() {
        return this.id + " / " + this.name + ", " + this.firstName + " [" + this.dateOfBirth + "]" + "\n"
                + "Contact details: " + contact.getPhoneNumber() + " / " + contact.getEmail() + "\n"
                + "Address: " + address.getStreet() + ", " + address.getZip() + " " + address.getCity();
    }

    /**
     *returns short summary of personal details of client
     */
    @Override
    public String toString() {
        return this.id + " / " + this.name + ", " + this.firstName + " [" + this.dateOfBirth + "]" + "\n"
                + this.contactPerson + ", " + contact.getPhoneNumber() + " / " + contact.getEmail() + "\n"
                + address.getStreet() + ", " + address.getZip() + " " + address.getCity();
    }
}
