package com.music4you.domain;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Class describes a real world object Instrument
 *
 * @author Eldaroth
 * @version 1.0
 */

public class Instrument implements Serializable {
    // Declare attributes
    private String model;
    private String type;
    private String manufacturer;
    private int inventoryId;
    private boolean leased;
    private LocalDate startLease;
    private LocalDate endLease;


    // Constructor
    public Instrument (String inModel, String inType, String inManufacturer) {
        this.model = inModel;
        this.type = inType;
        this.manufacturer = inManufacturer;
        this.leased = false;
        this.inventoryId = 0;
    }

    /**
     * Setter and getter methods
     */
    public void setModel(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }

    public int getInventoryId() {
        return inventoryId;
    }

    public void setLeased(boolean leased) {
        this.leased = leased;
    }

    public boolean isLeased() {
        return leased;
    }

    public LocalDate getStartLease() {
        return startLease;
    }

    public void setStartLease(LocalDate startLease) {
        this.startLease = startLease;
    }

    public LocalDate getEndLease() {
        return endLease;
    }


    public void setEndLease(LocalDate endLease) {
        this.endLease = endLease;
    }

    @Override
    public String toString(){
        String lease;
        if (leased) {
            lease = "yes";
        } else {
            lease = "no";
        }
        return inventoryId + " Model: " + model + " / Type: " + type + " / Manufacturer: " + manufacturer
                + " / Leased: " + lease;
    }

    // Necessary to override in order to determine whether instrument already exists
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Instrument that = (Instrument) o;

        if (!model.equals(that.model)) return false;
        if (!type.equals(that.type)) return false;
        return manufacturer.equals(that.manufacturer);
    }

    @Override
    public int hashCode() {
        int result = model.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + manufacturer.hashCode();
        return result;
    }

}