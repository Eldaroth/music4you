package com.music4you.persister.impl;

import com.music4you.domain.Instrument;
import com.music4you.domain.Leaser;
import com.music4you.persister.api.Persister;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;

/**
 * Implementation of the Persister API
 *
 * @author Eldaroth
 * @version 1.0
 */

public class PersisterImpl implements Persister{

    String userHomeDir = System.getProperty("user.home");
    //String instrFileName = userHomeDir + File.separator + "music4youInstrSerialized.txt";
    //String leaserFileName = userHomeDir + File.separator + "music4youLeaserSerialized.txt";
    String instrFileName = "music4youInstrSerialized.txt";
    String leaserFileName = "music4youLeaserSerialized.txt";
    File instrFile = new File(instrFileName);
    File leaserFile = new File(leaserFileName);

    @Override
    public void save(Instrument instr) throws Exception {
        ArrayList<Instrument> listInstr = new ArrayList<Instrument>();

        //Checks whether a file already exists and stores all the data in a new list
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

    @Override
    public void save(Leaser leaser) throws Exception {
        ArrayList<Leaser> listLeaser = new ArrayList<Leaser>();

        if (leaserFile.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(leaserFileName))) {
                listLeaser = (ArrayList<Leaser>) ois.readObject();
            }
        }
        listLeaser.add(leaser);
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(leaserFileName))) {
            oos.writeObject(listLeaser);
        }
    }

    @Override
    public void delete(Leaser leaser) throws Exception {
        ArrayList<Leaser> listLeaser = loadAllLeaser();
        if (listLeaser.contains(leaser)) {
            listLeaser.remove(leaser);
        } else {
            throw new Exception("Could not been deleted");
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(leaserFileName))) {
            oos.writeObject(listLeaser);
        }
    }

    @Override
    public void delete(Instrument instrument) throws Exception {
        ArrayList<Instrument> listInstr = loadAllInstr();
        if (listInstr.contains(instrument)) {
            listInstr.remove(instrument);
        } else {
            throw new Exception("Could not been deleted");
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(instrFileName))) {
            oos.writeObject(listInstr);
        }
    }

    @Override
    public void replace(Instrument original, Instrument edited) throws Exception {
        ArrayList<Instrument> listInstr = loadAllInstr();
        ListIterator<Instrument> iterator = listInstr.listIterator();

        while (iterator.hasNext()) {
            Instrument next = iterator.next();
            if (next.equals(original)) {
                iterator.set(edited);
            }
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(instrFileName))) {
            oos.writeObject(listInstr);
        }
    }

    @Override
    public void replace(Leaser original, Leaser edited) throws Exception {
        ArrayList<Leaser> listLeaser = loadAllLeaser();
        ListIterator<Leaser> iterator = listLeaser.listIterator();

        while (iterator.hasNext()) {
            Leaser next = iterator.next();
            if (next.equals(original)) {
                iterator.set(edited);
            }
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(leaserFileName))) {
            oos.writeObject(listLeaser);
        }
    }

    @Override
    public ArrayList<Instrument> findInstrModel(String model) throws Exception {
        ArrayList<Instrument> allFound = new ArrayList<Instrument>();
        for (Instrument instr : loadAllInstr()) {
            if (instr.getModel().toLowerCase().contains(model)) {
                allFound.add(instr);
            }
        }
        if (allFound.isEmpty()) {
            return null;
        }
        return allFound;
    }

    @Override
    public ArrayList<Instrument> findInstrType(String type) throws Exception {
        ArrayList<Instrument> allFound = new ArrayList<Instrument>();
        for (Instrument instr : loadAllInstr()) {
            if (instr.getType().toLowerCase().contains(type)) {
                allFound.add(instr);
            }
        }
        if (allFound.isEmpty()) {
            return null;
        }
        return allFound;
    }

    @Override
    public ArrayList<Instrument> findInstrManuf(String manuf) throws Exception {
        ArrayList<Instrument> allFound = new ArrayList<Instrument>();
        for (Instrument instr : loadAllInstr()) {
            if (instr.getManufacturer().toLowerCase().contains(manuf)) {
                allFound.add(instr);
            }
        }
        if (allFound.isEmpty()) {
            return null;
        }
        return allFound;
    }

    @Override
    public Instrument findInstrId(int id) throws Exception {
        for (Instrument instr : loadAllInstr()) {
            if (instr.getInventoryId() == id) {
                return instr;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Leaser> findLeaserName(String name) throws Exception {
        ArrayList<Leaser> foundAll = new ArrayList<Leaser>();
        for (Leaser temp : loadAllLeaser()) {
            if (temp.getName().toLowerCase().contains(name)) {
                foundAll.add(temp);
            }
        }
        if (foundAll.isEmpty()) {
            return null;
        }
        return foundAll;
    }

    @Override
    public ArrayList<Leaser> findLeaserEmail(String email) throws Exception {
        ArrayList<Leaser> foundAll = new ArrayList<Leaser>();
        for (Leaser temp : loadAllLeaser()) {
            if (temp.getContact().getEmail().toLowerCase().contains(email)) {
                foundAll.add(temp);
            }
        }
        if (foundAll.isEmpty()) {
            return null;
        }
        return foundAll;
    }

    @Override
    public ArrayList<Leaser> findLeaserCity(String city) throws Exception {
        ArrayList<Leaser> foundAll = new ArrayList<Leaser>();
        for (Leaser temp : loadAllLeaser()) {
            if (temp.getAddress().getCity().toLowerCase().contains(city)) {
                foundAll.add(temp);
            }
        }
        if (foundAll.isEmpty()) {
            return null;
        }
        return foundAll;
    }

    @Override
    public Leaser findLeaserId(String id) throws Exception {
        for (Leaser temp : loadAllLeaser()) {
            if (temp.getId().toLowerCase().contains(id)) {
                return temp;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Instrument> loadAllInstr() throws Exception {
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
    public ArrayList<Leaser> loadAllLeaser() throws Exception {
        ArrayList<Leaser> listAll = new ArrayList<Leaser>();

        if (leaserFile.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(leaserFileName))) {
                listAll = (ArrayList<Leaser>) ois.readObject();
            }
        }

        if (listAll != null) {
            return listAll;
        } else {
            return new ArrayList<Leaser>();
        }
    }

    @Override
    public ArrayList<Integer> allInventoryId() throws Exception {
        ArrayList<Integer> allId = new ArrayList<Integer>();

        if (allId.isEmpty()) {
            for (Instrument temp : loadAllInstr()) {
                allId.add(temp.getInventoryId());
            }
            Collections.sort(allId, Collections.reverseOrder());
            return allId;
        }
        return new ArrayList<Integer>();
    }

}
