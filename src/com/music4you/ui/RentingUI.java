package com.music4you.ui;

import com.music4you.business.api.Administration;
import com.music4you.domain.Instrument;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
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
                subMenu.addMenuItem("Return instrument");
                subMenu.addMenuItem("Ongoing rentals");
                subMenu.printMenu();

                int chosenOption = Integer.parseInt(showMenu.nextLine());

                switch (chosenOption) {

                    case 1: //Return instrument
                        Scanner sc = new Scanner(System.in);

                        System.out.println("Please enter client ID: ");
                        String clientId = sc.nextLine();


                        System.out.println("In development");
                        break;

                    case 2: //Ongoing rentals
                        ArrayList<Instrument> listAll = new ArrayList<Instrument>();
                        try {
                            listAll = administration.showAllInstr();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        for (Instrument temp : listAll) {
                            if (temp.isLeased()) {
                                System.out.println("\n \n******************************");
                                System.out.println(temp);
                                System.out.println("\nLeased to: ");
                                if (temp.getLeaser().isClubTag()) {
                                    System.out.println(temp.getLeaser().printClub());
                                } else {
                                    System.out.println(temp.getLeaser().printPerson());
                                }
                                System.out.println("\nLeased from " + temp.getStartLease() + " until "
                                        + temp.getEndLease());
                                System.out.println("");
                                System.out.println("\nPress enter to show next");
                                try {
                                    System.in.read();
                                } catch (IOException io) {
                                    io.printStackTrace();
                                }
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
}
