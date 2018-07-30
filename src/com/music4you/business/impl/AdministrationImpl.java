package com.music4you.business.impl;

import com.music4you.business.api.Administration;
import com.music4you.domain.Club;
import com.music4you.domain.Instrument;
import com.music4you.domain.Leaser;
import com.music4you.domain.Person;
import com.music4you.persister.api.Persister;

import java.util.ArrayList;

public class AdministrationImpl implements Administration {

    private Persister persister;

    public AdministrationImpl(Persister persister) {
        this.persister = persister;
    }

    @Override
    public Instrument addInstrument(Instrument instr) throws Exception {
        if (persister.loadAllInstr().contains(instr)) {
            throw new Exception("Entry already existing");
        } else {
            persister.save(instr);
        }
        return instr;
    }

    @Override
    public Leaser addLeaser(Leaser leaser) throws Exception {
        if (persister.loadAllLeaser().contains(leaser)) {
            throw new Exception("Entry already existing");
        } else {
            persister.save(leaser);
        }
        return leaser;
    }

//    @Override
//    public Person addPerson(Person person) throws Exception {
//        if (persister.loadAllPerson().contains(person)) {
//            throw new Exception("Entry already existing");
//        } else {
//            persister.save(person);
//        }
//        return person;
//    }

    @Override
    public Instrument findInstrModel(String model) throws Exception {
        try {
            return persister.findInstrModel(model);
        } catch (Exception e) {
            throw new Exception("Model not found");
        }
    }

    @Override
    public Instrument findInstrType(String type) throws Exception {
        try {
            return persister.findInstrType(type);
        } catch (Exception e) {
            throw new Exception("No such type");
        }
    }

    @Override
    public Instrument findInstrManuf(String manuf) throws Exception {
        try {
            return persister.findInstrManuf(manuf);
        } catch (Exception e) {
            throw new Exception("Manufacturer not in catalog");
        }
    }

    @Override
    public ArrayList<Instrument> showAllInstr() throws Exception {
        ArrayList<Instrument> listAll = new ArrayList<Instrument>(persister.loadAllInstr());
        return listAll;
    }

    @Override
    public ArrayList<Leaser> showAllLeaser() throws Exception {
        ArrayList<Leaser> listAll = new ArrayList<Leaser>(persister.loadAllLeaser());
        return listAll;
    }

//    @Override
//    public ArrayList<Person> showAllPerson() throws Exception {
//        ArrayList<Person> listAll = new ArrayList<Person>(persister.loadAllPerson());
//        return listAll;
//    }

}
