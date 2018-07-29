package com.music4you.ui;

import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuSkeleton {
    private String title;
    private ArrayList<String> menuItems;
    private String finiteOption;
    private String optionalText;
    private int selection;

    public MenuSkeleton(String title, String finiteOption) {
        this.title = title;
        this.menuItems = new ArrayList<String>();
        this.finiteOption = finiteOption;
        this.optionalText = "";
        this.selection = 0;
    }

    public MenuSkeleton() {
        this("","");
    }

    public void printMenu(){
        int i = 1;
        System.out.println("\n \n \n");
        System.out.println(this.getTitle());
        System.out.println("******************************");
        if (!this.optionalText.isEmpty()) {
            System.out.println(this.optionalText);
        }
        for (String temp : this.menuItems) {
            System.out.println("[" + Integer.toString(i) + "] " + temp);
            i++;
        }
        System.out.println("");
        System.out.println("[0] " + this.finiteOption);
        System.out.println("******************************");
        System.out.println("");
        System.out.print("Please chose your option: ");
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void addMenuItem(String item) {
        this.menuItems.add(item);
    }

    public void setFiniteOption(String finiteOption) {
        this.finiteOption = finiteOption;
    }

    public String getFiniteOption() {
        return finiteOption;
    }

    public void setOptionalText(String optionalText) {
        this.optionalText = optionalText;
    }

    public String getOptionalText() {
        return optionalText;
    }

    public int userSelection(Scanner sc) {
        try {
            this.selection = sc.nextInt();
            return this.selection;
        } catch (Exception e) {
            return 9;
        }
    }

}
