package com.music4you.business.impl;

import com.music4you.business.api.Administration;
import com.music4you.domain.Club;
import com.music4you.domain.Instrument;
import com.music4you.domain.Leaser;
import com.music4you.domain.Person;
import com.music4you.persister.api.Persister;

import java.util.ArrayList;
import java.util.List;

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
    public Club addClub(Club club) throws Exception {
        if (persister.loadAllClubs().contains(club)) {
            throw new Exception("Entry already existing");
        } else {
            persister.save(club);
        }
        return club;
    }

    @Override
    public Person addPerson(Person person) throws Exception {
        if (persister.loadAllPerson().contains(person)) {
            throw new Exception("Entry already existing");
        } else {
            persister.save(person);
        }
        return person;
    }

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
    public List<Instrument> showAllInstr() throws Exception {
        ArrayList<Instrument> listAll = new ArrayList<>(persister.loadAllInstr());
        return listAll;
    }

    @Override
    public List<Club> showAllClubs() throws Exception {
        ArrayList<Club> listAll = new ArrayList<>(persister.loadAllClubs());
        return listAll;
    }

    @Override
    public List<Person> showAllPerson() throws Exception {
        ArrayList<Person> listAll = new ArrayList<>(persister.loadAllPerson());
        return listAll;
    }

}
