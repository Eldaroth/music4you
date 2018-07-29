package com.music4you.persister.impl;

import com.music4you.domain.Instrument;
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

}
