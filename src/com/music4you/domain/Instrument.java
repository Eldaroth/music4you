package com.music4you.domain;

import java.io.Serializable;

/**
 * Class describes an instrument
 *
 * @author Eldaroth
 * @version 1.0
 */

public class Instrument implements Serializable {
    // Declare attributes
    //private static int nextInventoryId = 1;

    private String model;
    private String type;
    private String manufacturer;
    private int inventoryId;
    private boolean leased;

    //TODO find a way to continue inventory ID even after closing the program
    // Constructor
    public Instrument (String inModel, String inType, String inManufacturer) {
        this.model = inModel;
        this.type = inType;
        this.manufacturer = inManufacturer;
        //this.inventoryId = Instrument.nextInventoryId;
        //nextInventoryId++;
        this.leased = false;
    }

    /**
     * setting & getting the model name of the instrument
     */
    public void setModel(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    /**
     * setting & getting the type of the instrument
     */
    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    /**
     * setting & getting the manufacturer of the instrument
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * setting & getting the unique inventory id of the instrument
     */
    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }

    public int getInventoryId() {
        return inventoryId;
    }

    /**
     * checking whether instrument is leased or not
     */
    public void setLeased(boolean leased) {
        this.leased = leased;
    }

    public boolean isLeased() {
        return leased;
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
}