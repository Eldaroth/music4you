package com.music4you.ui;

import com.music4you.business.api.Administration;
import com.music4you.domain.Instrument;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class used for interacting with rental menu
 *
 * @author Eldaroth
 * @version 1.0
 */

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
                subMenu.addMenuItem("Return instrument");
                subMenu.addMenuItem("All ongoing rentals");
                subMenu.addMenuItem("Search by client ID");
                subMenu.addMenuItem("Search by instrument");
                subMenu.printMenu();

                int chosenOption = Integer.parseInt(showMenu.nextLine());
                Scanner sc = new Scanner(System.in);

                ArrayList<Instrument> listAll = new ArrayList<Instrument>();
                try {
                    listAll = administration.showAllInstr();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    System.out.println("Error");
                }

                switch (chosenOption) {

                    case 1: //Return instrument
                        System.out.println("Please enter client ID: ");
                        String client = sc.nextLine().toLowerCase();

                        for (Instrument temp : listAll) {
                            if (temp.isLeased() && temp.getLeaser().getId().toLowerCase().equals(client)) {
                                showRentals(temp);
                            }
                        }

                        System.out.println("\nWhich instrument would you like to return?" +
                                " Please enter inventory ID: ");
                        int returnId = Integer.parseInt(sc.nextLine());

                        for (Instrument temp : listAll) {
                            if (temp.getInventoryId() == returnId) {
                                Instrument original = new Instrument(temp);
                                temp.setLeased(false);
                                temp.setStartLease(null);
                                temp.setEndLease(null);
                                try {
                                    administration.replace(original, temp);
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                            }
                        }


                        System.out.println("Instrument returned");
                        break;

                    case 2: //Ongoing rentals
                        for (Instrument temp : listAll) {
                            if (temp.isLeased()) {
                                showRentals(temp);
                            }
                        }
                        break;

                    case 3:
                        System.out.println("Please enter client ID: ");
                        String clientId = sc.nextLine().toLowerCase();

                        for (Instrument temp : listAll) {
                            if (temp.isLeased() && temp.getLeaser().getId().toLowerCase().equals(clientId)) {
                                showRentals(temp);
                            }
                        }
                        break;

                    case 4:
                        System.out.println("Please enter inventory ID: ");
                        int instrId = Integer.parseInt(sc.nextLine());

                        for (Instrument temp : listAll) {
                            if (temp.isLeased() && temp.getInventoryId() == instrId) {
                                showRentals(temp);
                            }
                        }
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


    /**
     * Method for showing all the found rentals
     */
    private static void showRentals(Instrument instr) {
        System.out.println("\n \n******************************");
        System.out.println(instr);
        System.out.println("\nLeased to: ");
        if (instr.getLeaser().isClubTag()) {
            System.out.println(instr.getLeaser().printClub());
        } else {
            System.out.println(instr.getLeaser().printPerson());
        }
        System.out.println("\nLeased from " + instr.getStartLease() + " until "
                + instr.getEndLease());
        System.out.println("");
        System.out.println("\nPress enter to show next");
        try {
            System.in.read();
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

}
