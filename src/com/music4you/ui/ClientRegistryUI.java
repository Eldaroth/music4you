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
                showMenu();
            }
        }
    }

        /*
     * Creates the submenu for searching an instrument in the catalog
     */
    public static void clientSearchFor() {
        Scanner searchFor = new Scanner(System.in);

        //while (true) {
            try { // catches the exception if the user does not enter an int variable
                MenuSkeleton subSearch = new MenuSkeleton("Search client registry", "Back to previous menu");
                subSearch.setOptionalText("I'd like to search for:");
                subSearch.addMenuItem("Name");
                subSearch.addMenuItem("Address");
                subSearch.addMenuItem("E-Mail");
                subSearch.addMenuItem("Show all clients");
                subSearch.printMenu();

                int chosenOption = searchFor.nextInt();

                switch (chosenOption) {

                    case 1:
                        try {
                            Scanner scModel = new Scanner(System.in);
                            System.out.print("Enter name: ");
                            String name = scModel.nextLine();

                            System.out.println("\n \n");
                            //System.out.println(administration.findClientName(name));

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
                            System.out.print("Enter address: ");
                            String address = scType.nextLine();

                            System.out.println("\n \n");
                            //System.out.println(administration.findClientAddress(address));

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
                            System.out.print("Enter e-mail: ");
                            String email = scType.nextLine();

                            System.out.println("\n \n");
                            //System.out.println(administration.findClientEmail(email));

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
                            isEmpty = allLeaser.isEmpty();
                            if (isEmpty) {
                                System.out.println("No entries");
                            }

                            ArrayList<Leaser> allClubs = new ArrayList<Leaser>();
                            for (Leaser temp : allLeaser) {
                                if (temp.getContactPerson() != null) {
                                    allClubs.add(temp);
                                }
                            }

                            Iterator<Leaser> itClub = allClubs.iterator();
                            while (itClub.hasNext()) {
                                String club = itClub.next().printClub();
                                System.out.println(club);
                            }
                        } catch (Exception e) {
                            System.out.println("Could not been processed");
                        }
                        System.out.println("\nPlease press enter to show Persons");
                        System.in.read();

                        System.out.println("\n");
                        boolean isEmpty2 = false;
                        System.out.println("Person:");
                        System.out.println("--------------------");
                        try {
                            ArrayList<Leaser> allLeaser = administration.showAllLeaser();
                            isEmpty2 = allLeaser.isEmpty();
                            if (isEmpty2) {
                                System.out.println("No entries");
                            }

                            ArrayList<Leaser> allPerson = new ArrayList<Leaser>();
                            for (Leaser temp : allLeaser) {
                                if (temp.getDateOfBirth() != null) {
                                    allPerson.add(temp);
                                }
                            }

                            Iterator<Leaser> itPerson = allPerson.iterator();
                            while (itPerson.hasNext()) {
                                String person = itPerson.next().printPerson();
                                System.out.println(person);
                            }
                        } catch (Exception e) {
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
                clientSearchFor();
            }
        //}
    }

    /**
     * Add client to the registry
     */
    public static void addClient() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Is the client a Club?");
        System.out.print("[Y]/[N]: ");
        String club = sc.nextLine();

        if (club.equals("Y")) {
            addClub();
        } else {
            addPerson();
        }
    }

    public static void addClub() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Name: ");
        String name = sc.nextLine();

        System.out.print("Street & Nr.: ");
        String street = sc.nextLine();

        System.out.print("ZIP Code: ");
        int zip = Integer.parseInt(sc.nextLine());

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

            System.out.println("Club successfully added to registry");
            System.out.println("Please confirm with enter");
            sc1.nextLine();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Club could not been added to catalog");
        }
    }

    public static void addPerson() {
        Scanner sc = new Scanner(System.in);

        System.out.print("First name: ");
        String firstName = sc.nextLine();

        System.out.print("Last name: ");
        String lastName = sc.nextLine();

        System.out.print("Date of Birth [dd.MM.yyyy]: ");
        String dob = sc.nextLine();
        LocalDate dateOfBirth = LocalDate.parse(dob, DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        System.out.print("Street & Nr.: ");
        String street = sc.nextLine();

        System.out.print("ZIP Code: ");
        int zip = Integer.parseInt(sc.nextLine());

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

            System.out.println("Client successfully added to registry");
            System.out.println("Please confirm with enter");
            sc1.nextLine();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Client could not been added to catalog");
        }
    }
}
