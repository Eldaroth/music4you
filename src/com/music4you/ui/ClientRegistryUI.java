package com.music4you.ui;

import com.music4you.business.api.Administration;
import com.music4you.domain.Address;
import com.music4you.domain.Contact;
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

public class ClientRegistryUI {
    private static Administration administration;

    public ClientRegistryUI(Administration administration) {
        this.administration = administration;
    }

    /**
     * Responsible for executing the program
     */
    public void execute() {
        ClientRegistryUI.showMenu();
    }

    /**
     * Creates the entry menu for the instruments catalog
     */
    public static void showMenu() {
        Scanner showMenu = new Scanner(System.in);

        while (true) {
            try { // catches the exception if the user does not enter an int variable
                MenuSkeleton subMain = new MenuSkeleton("Client registry", "Back to Main Menu");
                subMain.addMenuItem("Add client");
                subMain.addMenuItem("Search in registry");
                subMain.printMenu();

                int chosenOption = showMenu.nextInt();

                switch (chosenOption) {

                    case 1:
                        addClient();
                        break;

                    case 2:
                        clientSearchFor();
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
            }
        }
    }

    /*
     * Creates the submenu for searching an instrument in the catalog
     */
    public static void clientSearchFor() {
        Scanner searchFor = new Scanner(System.in);

        while (true) {
            try { // catches the exception if the user does not enter an int variable
                MenuSkeleton subSearch = new MenuSkeleton("Search client registry", "Back to previous menu");
                subSearch.setOptionalText("I'd like to search for:");
                subSearch.addMenuItem("Name");
                subSearch.addMenuItem("E-Mail");
                subSearch.addMenuItem("City");
                subSearch.addMenuItem("Show all clients");
                subSearch.printMenu();

                int chosenOption = searchFor.nextInt();

                switch (chosenOption) {

                    case 1:
                        try {
                            Scanner scModel = new Scanner(System.in);
                            System.out.print("Enter name: ");
                            String name = scModel.nextLine().toLowerCase();

                            System.out.println("\n \n");
                            Leaser temp = administration.findLeaserName(name);

                            if (temp == null) {
                                System.out.println("No client found");
                            } else {
                                System.out.println(temp.printPerson());
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
                            System.out.print("Enter e-mail: ");
                            String email = scType.nextLine().toLowerCase();

                            System.out.println("\n \n");
                            Leaser temp = administration.findLeaserEmail(email);

                            if (temp == null) {
                                System.out.println("No client found");
                            } else {
                                System.out.println(temp.printPerson());
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
                            System.out.print("Enter city: ");
                            String city = scType.nextLine().toLowerCase();

                            System.out.println("\n \n");
                            Leaser temp = administration.findLeaserCity(city);

                            if (temp == null) {
                                System.out.println("No client found");
                            } else {
                                System.out.println(temp.printPerson());
                            }

                            System.out.println("\n \nPlease press enter to continue");
                            System.in.read();
                        } catch (Exception e) {
                            System.out.println("Input not valid");
                            break;
                        }
                        break;

                    case 4:
                        System.out.println("\n \n");
                        boolean isEmpty = false;
                        System.out.println("Clubs:");
                        System.out.println("--------------------");
                        try {
                            ArrayList<Leaser> allLeaser = administration.showAllLeaser();
                            ArrayList<Leaser> allClubs = new ArrayList<Leaser>();
                            for (Leaser temp : allLeaser) {
                                if (temp.isClubTag()) {
                                    allClubs.add(temp);
                                }
                            }
                            isEmpty = allClubs.isEmpty();
                            if (isEmpty) {
                                System.out.println("No entries");
                            }

                            Iterator<Leaser> itClub = allClubs.iterator();
                            while (itClub.hasNext()) {
                                String club = itClub.next().printClub();
                                System.out.println(club);
                                System.out.println("");
                            }
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            System.out.println("Could not been processed");
                        }
                        System.out.println("\nPlease press enter to show persons");
                        System.in.read();

                        System.out.println("\n");
                        boolean isEmpty2 = false;
                        System.out.println("Persons:");
                        System.out.println("--------------------");
                        try {
                            ArrayList<Leaser> allPerson = new ArrayList<Leaser>();
                            ArrayList<Leaser> allLeaser = administration.showAllLeaser();
                            for (Leaser temp : allLeaser) {
                                if (!temp.isClubTag()) {
                                    allPerson.add(temp);
                                }
                            }

                            isEmpty2 = allPerson.isEmpty();
                            if (isEmpty2) {
                                System.out.println("No entries");
                            }

                            Iterator<Leaser> itPerson = allPerson.iterator();
                            while (itPerson.hasNext()) {
                                String person = itPerson.next().printPerson();
                                System.out.println(person);
                                System.out.println("");
                            }
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            System.out.println("Could not been processed");
                        }

                        System.out.println("\n \nPlease press enter to continue");
                        System.in.read();
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
            }
        }
    }

    /**
     * Initiate to adding a client to the registry with differentiator btw Club & Person
     */
    public static void addClient() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Is the client a Club?");
        System.out.print("[Y]/[N]: ");
        String club = sc.nextLine().toLowerCase();

        if (club.equals("y")) {
            addClub();
        } else {
            addPerson();
        }
    }

    /**
     * Method for adding a Club Object to the registry
     */
    public static void addClub() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Name: ");
        String name = sc.nextLine();

        System.out.print("Street & Nr.: ");
        String street = sc.nextLine();

        System.out.print("ZIP Code: ");
        String zip = sc.nextLine();

        System.out.print("City: ");
        String city = sc.nextLine();

        System.out.print("E-Mail: ");
        String email = sc.nextLine();

        System.out.print("Phone: ");
        String phoneNumber = sc.nextLine();

        System.out.print("Contact person: ");
        String contact = sc.nextLine();

        Leaser club = new Leaser(name,contact);
        club.setAddress(new Address(street, zip, city));
        club.setContact(new Contact(phoneNumber, email));

        try {
            Scanner sc1 = new Scanner(System.in);
            administration.addLeaser(club);

            System.out.println("\nClub successfully added to registry");
            System.out.println("\nPlease confirm with enter");
            sc1.nextLine();

        } catch (Exception e) {
            System.out.println("\n" + e.getMessage());
            System.out.println("Club could not been added to catalog");
        }
    }

    /**
     * Method for adding a Person Object to the registry
     */
    public static void addPerson() {
        Scanner sc = new Scanner(System.in);

        System.out.print("First name: ");
        String firstName = sc.nextLine();

        System.out.print("Last name: ");
        String lastName = sc.nextLine();

        System.out.print("Date of Birth [dd.MM.yyyy]: ");
        LocalDate dateOfBirth = LocalDate.now();
        boolean isSuccessful = false;
        while (!isSuccessful) {
            try { // Catching exception if user inputs invalid date
                String dob = sc.nextLine();
                dateOfBirth = LocalDate.parse(dob, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                isSuccessful = true;
            } catch (Exception e) {
                System.out.print("Enter valid date [dd.MM.yyyy]: ");
            }
        }

        System.out.print("Street & Nr.: ");
        String street = sc.nextLine();

        System.out.print("ZIP Code: ");
        String zip = sc.nextLine();

        System.out.print("City: ");
        String city = sc.nextLine();

        System.out.print("E-Mail: ");
        String email = sc.nextLine();

        System.out.print("Phone: ");
        String phoneNumber = sc.nextLine();

        Leaser person = new Leaser(lastName, firstName, dateOfBirth);
        person.setAddress(new Address(street, zip, city));
        person.setContact(new Contact(phoneNumber, email));

        try {
            Scanner sc1 = new Scanner(System.in);
            administration.addLeaser(person);

            System.out.println("\nClient successfully added to registry");
            System.out.println("\nPlease confirm with enter");
            sc1.nextLine();

        } catch (Exception e) {
            System.out.println("\n" + e.getMessage());
            System.out.println("Client could not been added to catalog");
        }
    }
}
