package com.music4you.ui;

import com.music4you.business.api.Administration;

import java.util.Scanner;

public class RentingUI {
    private static Administration administration;

    public RentingUI(Administration administration) {
        this.administration = administration;
    }

    public void execute() {
        RentingUI.showMenu();
    }

    public static void showMenu() {
        Scanner showMenu = new Scanner(System.in);

        while (true) {
            try {
                MenuSkeleton subMenu = new MenuSkeleton("Renting", "Back to Main Menu");
                subMenu.addMenuItem("Rent out new instrument");
                subMenu.addMenuItem("Return instrument");
                subMenu.addMenuItem("Ongoing rentals");
                subMenu.printMenu();

                int chosenOption = Integer.parseInt(showMenu.nextLine());

                switch (chosenOption) {

                    case 1:
                        System.out.println("In development");
                        break;

                    case 2:
                        System.out.println("In development");
                        break;

                    case 3:
                        System.out.println("In development");
                        break;

                    case 0:
                        MainMenu.showMain();
                        break;

                    default:
                        System.out.println("\n \n \nPlease chose a valid option");

                }

            } catch (NumberFormatException e) {
                System.out.println("\n \n \nInvalid input. Please enter a number");
            }
        }
    }
}
