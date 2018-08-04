package com.music4you.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

/**
 * Class describes a real world Person or Club who is renting an instrument
 *
 * @author Eldaroth
 * @version 1.0
 */

public class Leaser implements Serializable {
    // Declare attributes
    private static final int LEASE_LENGTH_CLUB = 1; //minimal lease length in months for a club
    private static final int LEASE_LENGTH_PERSON = 6; //minimal lease length in months for a person

    private String id;
    private String name;
    private String firstName;
    private LocalDate dateOfBirth;
    private Address address;
    private Contact contact;
    private String contactPerson;
    private int leaseLength;
    private boolean clubTag;

    // Constructor
    public Leaser(String inName) {
        this.name = inName;
        this.firstName = "";
        this.dateOfBirth = LocalDate.now();
        this.address = new Address("","","");
        this.contact = new Contact("","");
        this.id = generateId();
    }

    // Constructor for a person as Leaser
    public Leaser(String inName, String inFirstName, LocalDate inDateOfBirth) {
        this(inName);
        this.firstName = inFirstName;
        this.dateOfBirth = inDateOfBirth;
        this.leaseLength = LEASE_LENGTH_PERSON;
        this.clubTag = false;
    }

    // Constructor for a club as Leaser
    public Leaser(String inName, String inContactPerson) {
        this(inName);
        this.contactPerson = inContactPerson;
        this.leaseLength = LEASE_LENGTH_CLUB;
        this.clubTag = true;
    }

    /**
     * Getter and setter methods
     */
    public String  getId() {
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

    public int getLeaseLength() {
        return leaseLength;
    }

    public void setLeaseLength(int leaseLength) {
        this.leaseLength = leaseLength;
    }

    public boolean isClubTag() {
        return clubTag;
    }

    public void setClubTag(boolean clubTag) {
        this.clubTag = clubTag;
    }


    /**
     * Helper class to print the leasers
     */
    public String printClub() {
        return "ID: " + this.id + " / " + this.name + ", Contact person: " + this.contactPerson + "\n"
                + "Contact details: " + contact.getPhoneNumber() + " / " + contact.getEmail() + "\n"
                + "Address: " + address.getStreet() + ", " + address.getZip() + " " + address.getCity();
    }

    public String printPerson() {
        return "ID: " + this.id + " / " + this.name + ", " + this.firstName + " [" + this.dateOfBirth + "]" + "\n"
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Leaser leaser = (Leaser) o;

        if (!name.equals(leaser.name)) return false;
        if (!firstName.equals(leaser.firstName)) return false;
        if (!dateOfBirth.equals(leaser.dateOfBirth)) return false;
        return address.getCity().equals(leaser.address.getCity());
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + dateOfBirth.hashCode();
        result = 31 * result + address.hashCode();
        return result;
    }

    /**
     * generates a unique ID based on UUID and the leaser's name
     */
    private String generateId() {
        UUID uuid = UUID.randomUUID();
        String uuidString = uuid.toString();

        String newUuid = "";
        for (int i = 0; i < 5; i++) {
            newUuid += uuidString.charAt(i);
        }

        String nameId = "";
        for (int i = 0; i < 3; i++) {
            nameId += this.name.charAt(i);
        }
        return nameId.toUpperCase() + newUuid.toUpperCase();
    }
}
