package com.music4you.persister.impl;

import com.music4you.domain.Club;
import com.music4you.domain.Instrument;
import com.music4you.domain.Leaser;
import com.music4you.domain.Person;
import com.music4you.persister.api.Persister;

import java.io.*;
import java.util.ArrayList;

public class PersisterImpl implements Persister{

    String userHomeDir = System.getProperty("user.home");
    String instrFileName = userHomeDir + File.separator + "music4youInstrSerialized.txt";
    String clubFileName = userHomeDir + File.separator + "music4youClubSerialized.txt";
    String persFileName = userHomeDir + File.separator + "music4youPersonSerialized.txt";
    File instrFile = new File(instrFileName);
    File clubFile = new File(clubFileName);
    File persFile = new File(persFileName);

    /*
     * @see com.music4you.business.api.Administration#save
     */
    public void save(Instrument instr) throws IOException, ClassNotFoundException {
        ArrayList<Instrument> listInstr = new ArrayList<Instrument>();
        /*
         *Checks whether a file already exists and stores all the data in a new list
         */
        if (instrFile.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(instrFileName))) {
                listInstr = (ArrayList<Instrument>) ois.readObject();
            }
        }
        listInstr.add(instr);
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(instrFileName))) {
            oos.writeObject(listInstr);
        }
    }

    public void save(Leaser leaser) throws IOException, ClassNotFoundException {
        ArrayList<Leaser> listLeaser = new ArrayList<Leaser>();

        if (clubFile.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(clubFileName))) {
                listLeaser = (ArrayList<Leaser>) ois.readObject();
            }
        }
        listLeaser.add(leaser);
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(clubFileName))) {
            oos.writeObject(listLeaser);
        }
    }

//    public void save(Person person) throws IOException, ClassNotFoundException {
//        ArrayList<Person> listPerson = new ArrayList<>();
//
//        if (persFile.exists()) {
//            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(persFileName))) {
//                listPerson = (ArrayList<Person>) ois.readObject();
//            }
//        }
//        listPerson.add(person);
//        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(persFileName))) {
//            oos.writeObject(listPerson);
//        }
//    }

    @Override
    public Instrument findInstrModel(String model) throws Exception {
        for (Instrument instr : loadAllInstr()) {
            if (instr.getModel().contains(model)) {
                return instr;
            }
        }
        return null;
    }

    @Override
    public Instrument findInstrType(String type) throws Exception {
        for (Instrument instr : loadAllInstr()) {
            if (instr.getType().contains(type)) {
                return instr;
            }
        }
        return null;
    }

    @Override
    public Instrument findInstrManuf(String manuf) throws Exception {
        for (Instrument instr : loadAllInstr()) {
            if (instr.getManufacturer().contains(manuf)) {
                return instr;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Instrument> loadAllInstr() throws IOException, ClassNotFoundException {
        ArrayList<Instrument> listAll = new ArrayList<>();

        if (instrFile.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(instrFileName))) {
                listAll = (ArrayList<Instrument>) ois.readObject();
            }
        }

        if (listAll != null) {
            return listAll;
        } else {
            return new ArrayList<Instrument>();
        }

    }

    @Override
    public ArrayList<Leaser> loadAllLeaser() throws IOException, ClassNotFoundException {
        ArrayList<Leaser> listAll = new ArrayList<Leaser>();

        if (clubFile.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(clubFileName))) {
                listAll = (ArrayList<Leaser>) ois.readObject();
            }
        }

        if (listAll != null) {
            return listAll;
        } else {
            return new ArrayList<Leaser>();
        }
    }

//    @Override
//    public ArrayList<Person> loadAllPerson() throws IOException, ClassNotFoundException {
//        ArrayList<Person> listAll = new ArrayList<>();
//
//        if (persFile.exists()) {
//            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(persFileName))) {
//                listAll = (ArrayList<Person>) ois.readObject();
//            }
//        }
//
//        if (listAll != null) {
//            return listAll;
//        } else {
//            return new ArrayList<Person>();
//        }
//    }

}
