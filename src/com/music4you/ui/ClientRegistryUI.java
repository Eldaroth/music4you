package com.music4you.ui;

import com.music4you.business.api.Administration;
import com.music4you.domain.Address;
import com.music4you.domain.Contact;
import com.music4you.domain.Leaser;

import java.io.IOException;
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

                int chosenOption = Integer.parseInt(showMenu.nextLine());

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

            } catch (NumberFormatException e) {
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

                int chosenOption = Integer.parseInt(searchFor.nextLine());

                switch (chosenOption) {

                    case 1:
                        try {
                            Scanner scModel = new Scanner(System.in);
                            System.out.print("Enter name: ");
                            String name = scModel.nextLine().toLowerCase();

                            System.out.println("\n \n");
                            ArrayList<Leaser> temp = administration.findLeaserName(name);

                            if (temp.isEmpty()) {
                                System.out.println("No client found");
                            } else {
                                for (Leaser leaser : temp) {
                                    if (leaser.isClubTag()) {
                                        System.out.println(leaser.printClub() + "\n");
                                    } else {
                                        System.out.println(leaser.printPerson() + "\n");
                                    }
                                }
                            }

                            options(temp);

//                            System.out.println("\n \nPlease press enter to continue");
//                            System.in.read();
                        } catch (NullPointerException n) {
                            System.out.println("No entry found");
                            break;
                        } catch (Exception e) {
                            //e.printStackTrace();
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
                            ArrayList<Leaser> temp = administration.findLeaserEmail(email);

                            if (temp.isEmpty()) {
                                System.out.println("No client found");
                            } else {
                                for (Leaser leaser : temp) {
                                    if (leaser.isClubTag()) {
                                        System.out.println(leaser.printClub() + "\n");
                                    } else {
                                        System.out.println(leaser.printPerson() + "\n");
                                    }
                                }
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
                            ArrayList<Leaser> temp = administration.findLeaserCity(city);

                            if (temp.isEmpty()) {
                                System.out.println("No client found");
                            } else {
                                for (Leaser leaser : temp) {
                                    if (leaser.isClubTag()) {
                                        System.out.println(leaser.printClub() + "\n");
                                    } else {
                                        System.out.println(leaser.printPerson() + "\n");
                                    }
                                }
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

                            Iterator<Leaser> itClub = allClubs.iterator();
                            while (itClub.hasNext()) {
                                String club = itClub.next().printClub();
                                System.out.println(club);
                                System.out.println("");
                            }
                        } catch (NullPointerException n) {
                            System.out.println("No entries");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            System.out.println("Could not been processed");
                        }
                        System.out.println("\nPlease press enter to show persons");
                        System.in.read();

                        System.out.println("\n");
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

                            Iterator<Leaser> itPerson = allPerson.iterator();
                            while (itPerson.hasNext()) {
                                String person = itPerson.next().printPerson();
                                System.out.println(person);
                                System.out.println("");
                            }
                        } catch (NullPointerException n) {
                            System.out.println("No entries");
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
            } catch (NumberFormatException e) {
                System.out.println("\n \n \nInvalid Input. Please enter a number.");
            } catch (IOException e) {
                System.out.println("\n \n \nInvalid Input");
            }
        }
    }

    public static void options(ArrayList<Leaser> input) {
        Scanner optionsMenu = new Scanner(System.in);

        while (true) {
            try { // catches the exception if the user does not enter an int variable
                MenuSkeleton subMain = new MenuSkeleton("What would you like to do?",
                        "Back to previous menu");
                subMain.addMenuItem("Edit client");
                subMain.addMenuItem("Delete client");
                subMain.printMenu();

                int chosenOption = Integer.parseInt(optionsMenu.nextLine());

                switch (chosenOption) {

                    case 1: //Edit client
                        Scanner sc1 = new Scanner(System.in);

                        while (true) {
                            System.out.println("\nPlease enter client ID: ");
                            String id = sc1.nextLine().toLowerCase();
                            Leaser leaser = new Leaser("TEST");
                            int counter = 0;

                            for (Leaser temp : input) {
                                if (temp.getId().toLowerCase().equals(id)) {
                                    try {
                                        leaser = temp;
                                        administration.delete(temp);
                                        counter++;
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                            if (counter == 0) {
                                System.out.println("No such ID found, try again");
                                break;
                            }

                            System.out.println("What would you like to edit:");
                            boolean isClub = false;
                            if (leaser.isClubTag()) {
                                System.out.println("\n[1] Name" + "\n[2] Contact person" + "\n[3] E-Mail"
                                        + "\n[4] Phone number" + "\n[5] Address");
                                isClub = true;
                            } else {
                                System.out.println("\n[1] First name" + "\n[2] Last name" + "\n[3] E-Mail"
                                        + "\n[4] Phone number" + "\n[5] Address");
                            }
                            int option = Integer.parseInt(sc1.nextLine());
                            if (option > 5) {
                                System.out.println("No option, try again");
                                break;
                            }

                            leaser = editLeaser(leaser, isClub, option);

                            try {
                                administration.addLeaser(leaser);
                                break;
                            } catch (Exception e) {
                                e.printStackTrace();
                                System.out.println("Error while editing client");
                            }
                        }
                        break;

                    case 2: //Delete client
                        Scanner sc2 = new Scanner(System.in);

                        while (true) {
                            System.out.println("\nPlease enter client ID to delete: ");
                            String id = sc2.nextLine().toLowerCase();
                            int counter = 0;

                            for (Leaser temp : input) {
                                if (temp.getId().toLowerCase().equals(id)) {
                                    try {
                                        System.out.println(temp.getId() + " deleted");
                                        administration.delete(temp);
                                        counter++;
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                            if (counter == 0) {
                                System.out.println("No such ID found, try again");
                            }
                            break;
                        }
                        break;

                    case 0:
                        clientSearchFor();
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
     * Initiator for adding a client to the registry with differentiation btw Club & Person
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
     * Method for adding a Club Leaser to the registry
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
     * Method for adding a Person Leaser to the registry
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

    /**
     * Method for getting new parameter to edit a specific leaser
     */
    public static Leaser editLeaser(Leaser leaser, boolean isClub, int option) {
        Scanner sc = new Scanner(System.in);

        if (option == 1 && isClub) {
            System.out.print("Please enter new name: ");
            String newName = sc.nextLine();
            leaser.setName(newName);
        } else if (option == 1 && !isClub) {
            System.out.print("Please enter new first name: ");
            String newFirstName = sc.nextLine();
            leaser.setFirstName(newFirstName);
        } else if (option == 2 && isClub) {
            System.out.print("Please enter new contact person: ");
            String newContactPerson = sc.nextLine();
            leaser.setContactPerson(newContactPerson);
        } else if (option == 2 && !isClub) {
            System.out.print("Please enter new last name: ");
            String newLastName = sc.nextLine();
            leaser.setName(newLastName);
        } else if (option == 3) {
            System.out.print("Please enter new email: ");
            String newEmail = sc.nextLine();
            Contact newContact = leaser.getContact();
            newContact.setEmail(newEmail);
            leaser.setContact(newContact);
        } else if (option == 4) {
            System.out.print("Please enter new phone number: ");
            String newPhone = sc.nextLine();
            Contact newContact = leaser.getContact();
            newContact.setPhoneNumber(newPhone);
            leaser.setContact(newContact);
        } else if (option == 5) {
            System.out.print("Please enter new street: ");
            String newStreet = sc.nextLine();
            System.out.print("Please enter new ZIP: ");
            String newZip = sc.nextLine();
            System.out.print("Please enter new city: ");
            String newCity = sc.nextLine();
            Address newAddress = new Address(newStreet, newZip, newCity);
            leaser.setAddress(newAddress);
        } else {
            System.out.println("Error");
        }
        return leaser;
    }
}
