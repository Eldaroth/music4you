package com.music4you.ui;

import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MainMenu {

    public void execute() {
        MainMenu.showMain();
    }

    public static void showMain() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            /*System.out.println("\n \n \n");
            System.out.println("Main Menu");
            System.out.println("******************************");
            System.out.println("[1] Instrument catalog");
            System.out.println("[2] Client registry");
            System.out.println("[3] Renting");
            System.out.println("");
            System.out.println("[0] Exit program");
            System.out.println("******************************");
            System.out.println("");
            System.out.print("Please chose your option: ");*/

            MenuSkeleton main = new MenuSkeleton("Main Menu", "Exit program");
            main.addMenuItem("Instrument catalog");
            main.addMenuItem("Client registry");
            main.addMenuItem("Renting");
            main.printMenu();

            int chosenOption = sc.nextInt();

            switch (chosenOption) {

                case 1:
                    InstrumentCatalogUI submenu1 = new InstrumentCatalogUI();
                    submenu1.showMenu();
                    break;

                case 2:
                    System.out.println("\nIn development");
                    break;

                case 3:
                    System.out.println("\nIn development");
                    break;

                case 0:
                    MainMenu.finish();

                default:
                    System.out.println("\n \n \nPlease chose a valid option");
            }

        }
    }

    /*
     * finishes & stops the application
     */
    public static void finish() {
        System.out.println("\nProgram stopped.");
        System.exit(1);
    }

}