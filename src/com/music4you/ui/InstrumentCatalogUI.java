package com.music4you.ui;

import java.awt.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InstrumentCatalogUI {

    public void execute() {
        InstrumentCatalogUI.showMenu();
    }

    public static void showMenu() {
        Scanner showMenu = new Scanner(System.in);

        while (true) {
            MenuSkeleton subMain = new MenuSkeleton("Instrument catalog", "Back to Main Menu");
            subMain.addMenuItem("Add instrument");
            subMain.addMenuItem("Search in catalog");
            subMain.printMenu();

            int chosenOption = showMenu.nextInt();

            switch (chosenOption) {

                case 1:
                    System.out.println("\nIn development");
                    break;

                case 2:
                    instrumentSearchFor();
                    break;

                case 0:
                    MainMenu back = new MainMenu();
                    back.showMain();
                    break;

                default:
                    System.out.println("\n \n \nPlease chose a valid option");
            }
        }
    }

    public static void instrumentSearchFor() {
        Scanner searchFor = new Scanner(System.in);

        while (true) {
            MenuSkeleton subSearch = new MenuSkeleton("Search Instrument catalog", "Back to previous menu");
            subSearch.setOptionalText("I'd like to search for:");
            subSearch.addMenuItem("Inventory ID");
            subSearch.addMenuItem("Model number");
            subSearch.addMenuItem("Type of instrument");
            subSearch.addMenuItem("Manufacturer");
            subSearch.addMenuItem("Show all instruments");
            subSearch.printMenu();

            //int chosenOption = searchFor.nextInt();

            switch (subSearch.userSelection(searchFor)) {

                case 1:
                    System.out.println("\nIn development");
                    break;

                case 2:
                    System.out.println("\nIn development");
                    break;

                case 3:
                    System.out.println("\nIn development");
                    break;

                case 4:
                    System.out.println("\nIn development");
                    break;

                case 5:
                    System.out.println("\nIn development");
                    break;

                case 0:
                    showMenu();
                    break;

                default:
                    System.out.println("\n \n \nPlease chose a valid option");
                    break;
            }
        }
    }
}