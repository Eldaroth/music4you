package com.music4you.ui;

import com.music4you.business.api.Administration;

import java.util.Scanner;

/**
 * Creates and shows the Main Menu of the program
 *
 * @author Eldaroth
 * @version 1.0
 */

public class MainMenu {
    private static Administration administration;

    public MainMenu(Administration administration) {
        this.administration = administration;
    }

    /**
     * Responsible for executing the program
     */
    public void execute() {
        MainMenu.showMain();
    }

    /**
     * Creates the entry menu for the user
     */
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

            try { // catches the exception if the user does not enter an int variable
                MenuSkeleton main = new MenuSkeleton("Main Menu", "Exit program");
                main.addMenuItem("Instrument catalog");
                main.addMenuItem("Client registry");
                main.addMenuItem("Renting");
                main.printMenu();

                int chosenOption = Integer.parseInt(sc.nextLine());

                switch (chosenOption) {

                    case 1:
                        InstrumentCatalogUI submenu1 = new InstrumentCatalogUI(administration);
                        submenu1.showMenu();
                        break;

                    case 2:
                        ClientRegistryUI submenu2 = new ClientRegistryUI(administration);
                        submenu2.showMenu();
                        break;

                    case 3:
                        RentingUI submenu3 = new RentingUI(administration);
                        submenu3.showMenu();
                        break;

                    case 0:
                        MainMenu.finish();
                        break;

                    default:
                        System.out.println("\n \n \nPlease chose a valid option");
                }
            }
            catch (NumberFormatException e) {
                System.out.println("\n \n \nInvalid Input. Please enter a number.");
            }

        }
    }

    /**
     * finishes & stops the application
     */
    public static void finish() {
        System.out.println("\nProgram stopped.");
        System.exit(0);
    }

}