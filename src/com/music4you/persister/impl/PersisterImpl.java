package com.music4you.persister.impl;

import com.music4you.domain.Instrument;
import com.music4you.domain.Leaser;
import com.music4you.persister.api.Persister;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Implementation of the Persister API
 *
 * @author Eldaroth
 * @version 1.0
 */

public class PersisterImpl implements Persister{

    String userHomeDir = System.getProperty("user.home");
    String instrFileName = userHomeDir + File.separator + "music4youInstrSerialized.txt";
    String leaserFileName = userHomeDir + File.separator + "music4youLeaserSerialized.txt";
    File instrFile = new File(instrFileName);
    File leaserFile = new File(leaserFileName);

    /*
     * @see com.music4you.business.api.Administration#save
     */
    public void save(Instrument instr) throws Exception {
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
    public Instrument findInstrModel(String model) throws Exception {
        for (Instrument instr : loadAllInstr()) {
            if (instr.getModel().toLowerCase().contains(model)) {
                return instr;
            }
        }
        return null;
    }

    @Override
    public Instrument findInstrType(String type) throws Exception {
        for (Instrument instr : loadAllInstr()) {
            if (instr.getType().toLowerCase().contains(type)) {
                return instr;
            }
        }
        return null;
    }

    @Override
    public Instrument findInstrManuf(String manuf) throws Exception {
        for (Instrument instr : loadAllInstr()) {
            if (instr.getManufacturer().toLowerCase().contains(manuf)) {
                return instr;
            }
        }
        return null;
    }

    @Override
    public Leaser findLeaserName(String name) throws Exception {
        for (Leaser temp : loadAllLeaser()) {
            if (temp.getName().toLowerCase().contains(name)) {
                return temp;
            }
        }
        return null;
    }

    @Override
    public Leaser findLeaserEmail(String email) throws Exception {
        for (Leaser temp : loadAllLeaser()) {
            if (temp.getContact().getEmail().toLowerCase().contains(email)) {
                return temp;
            }
        }
        return null;
    }

    @Override
    public Leaser findLeaserCity(String city) throws Exception {
        for (Leaser temp : loadAllLeaser()) {
            if (temp.getAddress().getCity().toLowerCase().contains(city)) {
                return temp;
            }
        }
        return null;
    }

    @Override
    public Instrument deleteInstrument(Instrument instr) throws Exception {

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
