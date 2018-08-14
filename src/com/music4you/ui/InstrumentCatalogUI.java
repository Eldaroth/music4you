package com.music4you.ui;

import com.music4you.business.api.Administration;
import com.music4you.domain.Instrument;
import com.music4you.domain.Leaser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
                MenuSkeleton subMain = new MenuSkeleton("Instrument catalog",
                        "Back to Main Menu");
                subMain.addMenuItem("Add instrument");
                subMain.addMenuItem("Search in catalog");
                subMain.printMenu();

                int chosenOption = Integer.parseInt(showMenu.nextLine());

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

            } catch (NumberFormatException e) {
                System.out.println("\n \n \nInvalid Input. Please enter a number.");
            }
        }
    }

    /*
     * Creates the submenu for searching an instrument in the catalog
     */
    public static void instrumentSearchFor() {
        Scanner searchFor = new Scanner(System.in);

        while (true) {
            try { // catches the exception if the user does not enter an int variable
                MenuSkeleton subSearch = new MenuSkeleton("Search Instrument catalog",
                        "Back to previous menu");
                subSearch.setOptionalText("I'd like to search for:");
                subSearch.addMenuItem("Model");
                subSearch.addMenuItem("Type of instrument");
                subSearch.addMenuItem("Manufacturer");
                subSearch.addMenuItem("Show all instruments");
                subSearch.printMenu();

                int chosenOption = Integer.parseInt(searchFor.nextLine());
                Scanner sc = new Scanner(System.in);

                switch (chosenOption) {

                    case 1:
                        try {
                            System.out.print("Enter model: ");
                            String model = sc.nextLine().toLowerCase();

                            System.out.println("\n \n");
                            ArrayList<Instrument> temp = administration.findInstrModel(model);

                            if (temp.isEmpty()) {
                                System.out.println("No instrument found");
                            } else {
                                for (Instrument instr : temp) {
                                    System.out.println(instr);
                                }
                            }

                            options(temp);

                        } catch (NullPointerException n) {
                            System.out.println("No instrument found");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            System.out.println("Input not valid");
                        }
                        break;

                    case 2:
                        try {
                            System.out.print("Enter type: ");
                            String type = sc.nextLine().toLowerCase();

                            System.out.println("\n \n");
                            ArrayList<Instrument> temp = administration.findInstrType(type);

                            if (temp.isEmpty()) {
                                System.out.println("No instrument found");
                            } else {
                                for (Instrument instr : temp) {
                                    System.out.println(instr);
                                }
                            }

                            options(temp);

                        } catch (NullPointerException n) {
                            System.out.println("No instrument found");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            System.out.println("Input not valid");
                        }
                        break;

                    case 3:
                        try {
                            System.out.print("Enter manufacturer: ");
                            String manuf = sc.nextLine().toLowerCase();

                            System.out.println("\n \n");
                            ArrayList<Instrument> temp = administration.findInstrManuf(manuf);

                            if (temp.isEmpty()) {
                                System.out.println("No instrument found");
                            } else {
                                for (Instrument instr : temp) {
                                    System.out.println(instr);
                                }
                            }

                            options(temp);

                        } catch (NullPointerException n) {
                            System.out.println("No instrument found");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            System.out.println("Input not valid");
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
            catch (NumberFormatException e) {
                System.out.println("\n \n \nInvalid Input. Please enter a number.");
            }
        }
    }

    public static void options(ArrayList<Instrument> input) {
        Scanner optionsMenu = new Scanner(System.in);

        while (true) {
            try { // catches the exception if the user does not enter an int variable
                MenuSkeleton subMain = new MenuSkeleton("What would you like to do?",
                        "Back to previous menu");
                subMain.addMenuItem("Rent out instrument");
                subMain.addMenuItem("Edit instrument");
                subMain.addMenuItem("Delete instrument");
                subMain.printMenu();

                int chosenOption = Integer.parseInt(optionsMenu.nextLine());
                Scanner sc = new Scanner(System.in);

                switch (chosenOption) {

                    case 1: //Rent out instrument
                        while (true) {
                            System.out.print("\nPlease enter inventory ID: ");
                            int id = Integer.parseInt(sc.nextLine());


                            for (Instrument temp : input) {
                                if (temp.getInventoryId() == id) {

                                    if (temp.isLeased()) {
                                        System.out.println("\nAlready rented out, sorry");
                                        break;
                                    }

                                    Instrument original = new Instrument(temp);

                                    System.out.print("Please enter client ID: ");
                                    String clientId = sc.nextLine();
                                    try {
                                        Leaser client = administration.findLeaserId(clientId);
                                        if (client == null) {
                                            System.out.println("\nNot found");
                                            break;
                                        }
                                    } catch (NullPointerException e) {
                                        System.out.println(e.getMessage());
                                        break;
                                    } catch (Exception e) {
                                        System.out.println("\nNot found");
                                        break;
                                    }

                                    System.out.print("Please enter start date for rent [dd.MM.yyyy]: ");
                                    LocalDate start = LocalDate.now();
                                    boolean isSuccessful = false;
                                    while (!isSuccessful) {
                                        try { // Catching exception if user inputs invalid date
                                            String startDate = sc.nextLine();
                                            start = LocalDate.parse(startDate,
                                                    DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                                            isSuccessful = true;
                                        } catch (Exception e) {
                                            System.out.print("Enter valid date [dd.MM.yyyy]: ");
                                        }
                                    }

                                    System.out.print("Please enter end date for rent [dd.MM.yyyy]: ");
                                    LocalDate end = LocalDate.now();
                                    boolean isSuccessful2 = false;
                                    while (!isSuccessful2) {
                                        try { // Catching exception if user inputs invalid date
                                            String endDate = sc.nextLine();
                                            end = LocalDate.parse(endDate, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                                            isSuccessful2 = true;
                                        } catch (Exception e) {
                                            System.out.print("Enter valid date [dd.MM.yyyy]: ");
                                        }
                                    }

                                    try {
                                        Leaser leaser = administration.findLeaserId(clientId);
                                        administration.rent(leaser, temp, start, end);
                                    } catch (Exception e) {
                                        System.out.println("\n" + e.getMessage());
                                    }

                                    break;
                                } else {
                                    System.out.println("\nNo such ID found, try again");
                                    break;
                                }
                            }
                            break;
                        }
                        instrumentSearchFor();
                        break;

                    case 2: //Edit instrument
                        while (true) {
                            System.out.print("\nPlease enter inventory ID: ");
                            int id = Integer.parseInt(sc.nextLine());
                            int counter = 0;

                            for (Instrument temp : input) {
                                if (temp.getInventoryId() == id) {
                                    counter++;
                                    Instrument original = new Instrument(temp);

                                    System.out.println("What would you like to edit:");
                                    System.out.println("\n[1] Model" + "\n[2] Type" + "\n[3] Manufacturer");

                                    int option = Integer.parseInt(sc.nextLine());
                                    if (option > 3) {
                                        System.out.println("No option, try again");
                                        break;
                                    }

                                    temp = editInstrument(temp, option);
                                    try {
                                        administration.replace(original, temp);
                                        System.out.println("\nNew:" + "\n" + temp);
                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                        System.out.println("Error while editing instrument");
                                    }
                                }
                            }
                            if (counter == 0) {
                                System.out.println("No such ID found");
                                break;
                            }
                            break;
                        }
                        instrumentSearchFor();
                        break;

                    case 3: //delete instrument
                        while (true) {
                            System.out.print("\nPlease enter inventory ID to delete: ");
                            int id = Integer.parseInt(sc.nextLine());
                            int counter = 0;

                            for (Instrument temp : input) {
                                if (temp.getInventoryId() == id) {
                                    try {
                                        administration.delete(temp);
                                        System.out.println(temp.getInventoryId() + " deleted");
                                        counter++;

                                        input.remove(temp);
                                        for (Instrument instr : input) {
                                            System.out.println("\nRemaining: " +
                                                    "\n" + instr);
                                        }
                                        System.out.println("\n \nPlease press enter to continue");
                                        System.in.read();
                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                        System.out.println("Error while deleting instrument");
                                    }
                                }
                            }
                            if (counter == 0) {
                                System.out.println("\nNo such ID found, try again");
                            }
                            break;
                        }
                        instrumentSearchFor();
                        break;

                    case 0:
                        instrumentSearchFor();
                        break;

                    default:
                        System.out.println("\n \n \nPlease chose a valid option");
                }

            } catch (NumberFormatException e) {
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

        try { // Goes through all already existing inventory ID and searches a free one or sets the highest
            int newId = 0;
            for (int i : administration.allInventoryId()) {
                if (newId == i) {
                    newId++;
                }
            }
            instr.setInventoryId(newId);

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

    private static Instrument editInstrument(Instrument instr, int option) {
        Scanner sc = new Scanner(System.in);

        if (option == 1) {
            System.out.print("Please enter new model: ");
            String newModel = sc.nextLine();
            instr.setModel(newModel);
        } else if (option == 2) {
            System.out.print("Please enter new type: ");
            String newType = sc.nextLine();
            instr.setType(newType);
        } else if (option == 3) {
            System.out.print("Please enter new manufacturer: ");
            String newManuf = sc.nextLine();
            instr.setManufacturer(newManuf);
        } else {
            System.out.println("Error");
        }
        return instr;
    }

}