package com.music4you.ui;

import com.music4you.business.api.Administration;
import com.music4you.domain.Instrument;

import java.util.Iterator;
import java.util.Scanner;

/**
 * Creates and shows the submenu for the instruments catalog
 *
 * @author Eldaroth
 * @version 1.0
 */

public class InstrumentCatalogUI {
    private static Administration administration;

    public InstrumentCatalogUI(Administration administration) {
        this.administration = administration;
    }

    /**
     * Responsible for executing the program
     */
    public void execute() {
        InstrumentCatalogUI.showMenu();
    }

    /**
     * Creates the entry menu for the instruments catalog
     */
    public static void showMenu() {
        Scanner showMenu = new Scanner(System.in);

        while (true) {
            try { // catches the exception if the user does not enter an int variable
                MenuSkeleton subMain = new MenuSkeleton("Instrument catalog", "Back to Main Menu");
                subMain.addMenuItem("Add instrument");
                subMain.addMenuItem("Search in catalog");
                subMain.printMenu();

                int chosenOption = showMenu.nextInt();

                switch (chosenOption) {

                    case 1:
                        addInstrument();
                        break;

                    case 2:
                        instrumentSearchFor();
                        break;

                    case 0:
                        //MainMenu back = new MainMenu();
                        MainMenu.showMain();
                        break;

                    default:
                        System.out.println("\n \n \nPlease chose a valid option");
                }

            } catch (Exception e) {
                System.out.println("\n \n \nInvalid Input. Please enter a number.");
                showMenu();
            }
        }
    }

    /*
     * Creates the submenu for searching an instrument in the catalog
     */
    public static void instrumentSearchFor() {
        Scanner searchFor = new Scanner(System.in);

        //while (true) {
            try { // catches the exception if the user does not enter an int variable
                MenuSkeleton subSearch = new MenuSkeleton("Search Instrument catalog", "Back to previous menu");
                subSearch.setOptionalText("I'd like to search for:");
                subSearch.addMenuItem("Model");
                subSearch.addMenuItem("Type of instrument");
                subSearch.addMenuItem("Manufacturer");
                subSearch.addMenuItem("Show all instruments");
                subSearch.printMenu();

                int chosenOption = searchFor.nextInt();

                switch (chosenOption) {

                    case 1:
                        try {
                            Scanner scModel = new Scanner(System.in);
                            System.out.print("Enter model: ");
                            String model = scModel.nextLine();

                            System.out.println("\n \n");
                            Instrument temp = administration.findInstrModel(model);

                            if (temp == null) {
                                System.out.println("No instrument found");
                            } else {
                                System.out.println(temp);
                            }

                            System.out.println("\n \nPlease press enter to continue");
                            System.in.read();
                        } catch (Exception e) {
                            System.out.println("Input not valid");
                            break;
                        }
                        break;

                    case 2:
                        try {
                            Scanner scType = new Scanner(System.in);
                            System.out.print("Enter type: ");
                            String type = scType.nextLine();

                            System.out.println("\n \n");
                            Instrument temp = administration.findInstrType(type);

                            if (temp == null) {
                                System.out.println("No instrument found");
                            } else {
                                System.out.println(temp);
                            }

                            System.out.println("\n \nPlease press enter to continue");
                            System.in.read();
                        } catch (Exception e) {
                            System.out.println("Input not valid");
                            break;
                        }
                        break;

                    case 3:
                        try {
                            Scanner scType = new Scanner(System.in);
                            System.out.print("Enter manufacturer: ");
                            String manuf = scType.nextLine();

                            System.out.println("\n \n");
                            Instrument temp = administration.findInstrManuf(manuf);

                            if (temp == null) {
                                System.out.println("No instrument found");
                            } else {
                                System.out.println(temp);
                            }

                            System.out.println("\n \nPlease press enter to continue");
                            System.in.read();
                        } catch (Exception e) {
                            System.out.println("Input not valid");
                            break;
                        }
                        break;

                    case 4:
                        try {
                            System.out.println("\n \n");
                            Iterator<Instrument> itInstr = administration.showAllInstr().iterator();
                            while (itInstr.hasNext()) {
                                String instr = itInstr.next().toString();
                                System.out.println(instr);
                            }
                            System.out.println("\n \nPlease press enter to continue");
                            System.in.read();

                        } catch (Exception e) {
                            System.out.println("Could not been processed");
                            break;
                        }
                        break;

                    case 0:
                        showMenu();
                        break;

                    default:
                        System.out.println("\n \n \nPlease chose a valid option");
                        break;
                }
            }
            catch (Exception e) {
                System.out.println("\n \n \nInvalid Input. Please enter a number.");
                instrumentSearchFor();
            }
        //}
    }

    /**
     * Adds an instrument to the catalog
     */
    public static void addInstrument() {
        Scanner sc = new Scanner(System.in);
        System.out.println("");

        System.out.print("Model: ");
        String model = sc.nextLine();

        System.out.print("Type: ");
        String type = sc.nextLine();

        System.out.print("Manufacturer: ");
        String manufacturer = sc.nextLine();

        Instrument instr = new Instrument(model, type, manufacturer);

        try {
            Scanner sc1 = new Scanner(System.in);
            administration.addInstrument(instr);

            System.out.println("\nInstrument successfully added to catalog");
            System.out.println("\nPlease confirm with enter");
            sc1.nextLine();


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("\nInstrument could not been added to catalog");
        }
    }
}