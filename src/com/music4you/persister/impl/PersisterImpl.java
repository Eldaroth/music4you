package com.music4you.persister.impl;

import com.music4you.domain.Club;
import com.music4you.domain.Instrument;
import com.music4you.domain.Leaser;
import com.music4you.domain.Person;
import com.music4you.persister.api.Persister;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PersisterImpl implements Persister{

    String userHomeDir = System.getProperty("user.home");
    String fileName = userHomeDir + File.separator + "music4youSerialized.txt";
    File file = new File(fileName);

    /*
     * @see com.music4you.business.api.Administration#save
     */
    public Instrument save(Instrument instr) throws Exception {
        List<Instrument> listInstr = new ArrayList<>();
        /*
         *Checks whether a file already exists and stores all the data in a new list
         */
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
                listInstr = (ArrayList<Instrument>) ois.readObject();
            }
        }
        listInstr.add(instr);
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(listInstr);
        }
        return instr;
    }

    public Club save(Club club) throws Exception {
        List<Club> listClub = new ArrayList<>();

        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
                listClub = (ArrayList<Club>) ois.readObject();
            }
        }
        listClub.add(club);
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(listClub);
        }
        return club;
    }

    public Person save(Person person) throws Exception {
        List<Person> listPerson = new ArrayList<>();

        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
                listPerson = (ArrayList<Person>) ois.readObject();
            }
        }
        listPerson.add(person);
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(listPerson);
        }
        return person;
    }

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
    public List<Instrument> loadAllInstr() throws Exception {
        ArrayList<Instrument> listAll = new ArrayList<>();

        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
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
    public List<Club> loadAllClubs() throws Exception {
        ArrayList<Club> listAll = new ArrayList<>();

        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
                listAll = (ArrayList<Club>) ois.readObject();
            }
        }

        if (listAll != null) {
            return listAll;
        } else {
            return new ArrayList<Club>();
        }
    }

    @Override
    public List<Person> loadAllPerson() throws Exception {
        ArrayList<Person> listAll = new ArrayList<>();

        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
                listAll = (ArrayList<Person>) ois.readObject();
            }
        }

        if (listAll != null) {
            return listAll;
        } else {
            return new ArrayList<Person>();
        }
    }

}
