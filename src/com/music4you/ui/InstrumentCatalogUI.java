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
                        MainMenu.showMain();
                        break;

                    default:
                        System.out.println("\n \n \nPlease chose a valid option");
                }

            } catch (Exception e) {
                System.out.println("\n \n \nInvalid Input. Please enter a number.");
            }
        }
    }

    /*
     * Creates the submenu for searching an instrument in the catalog
     */
    public static void instrumentSearchFor() {
        Scanner searchFor = new Scanner(System.in);
        //TODO Implement a check if the data file is empty; if yes, return message and stop menu

        while (true) {
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
                            String model = scModel.nextLine().toLowerCase();

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
                            String type = scType.nextLine().toLowerCase();

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
                            String manuf = scType.nextLine().toLowerCase();

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

                        } catch (NullPointerException n) {
                            System.out.println("No entries");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
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
            catch (NullPointerException e) {
                System.out.println("\n \n \nInvalid Input. Please enter a number.");
            }
        }
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
            // Searches the highest InventoryID in the data file and increments it by one
            if (!administration.allInventoryId().isEmpty()) {
                int newId = administration.allInventoryId().get(0) + 1;
                instr.setInventoryId(newId);
            }

            Scanner sc1 = new Scanner(System.in);
            administration.addInstrument(instr);

            System.out.println("\nInstrument successfully added to catalog");
            System.out.println("\nPlease confirm with enter");
            sc1.nextLine();
        } catch (Exception e) {
            System.out.println("\n" + e.getMessage());
            System.out.println("Instrument could not been added to catalog");
        }
    }

}