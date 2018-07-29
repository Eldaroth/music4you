package com.music4you.domain;

public class Instrument {
    // Declare attributes
    private static int nextInventoryId = 1;

    private String model;
    private String type;
    private String manufacturer;
    private int inventoryId;

    // Constructor
    public Instrument (String inModel, String inType, String inManufacturer) {
        this.model = inModel;
        this.type = inType;
        this.manufacturer = inManufacturer;
        this.inventoryId = Instrument.nextInventoryId;
        nextInventoryId++;
    }

    /*
     * setting & getting the model name of the instrument
     */
    public void setModel(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    /*
     * setting & getting the type of the instrument
     */
    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    /*
     * setting & getting the manufacturer of the instrument
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    /*
     * setting & getting the unique inventory id of the instrument
     */
    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }

    public int getInventoryId() {
        return inventoryId;
    }
}