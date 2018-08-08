package com.music4you.business.impl;

import com.music4you.business.api.Administration;
import com.music4you.domain.Instrument;
import com.music4you.domain.Leaser;
import com.music4you.persister.api.Persister;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Implementation of the Administration API
 *
 * @author Eldaroth
 * @version 1.0
 */

public class AdministrationImpl implements Administration {

    private Persister persister;

    public AdministrationImpl(Persister persister) {
        this.persister = persister;
    }

    @Override
    public Instrument addInstrument(Instrument instr) throws Exception {
        if (!persister.loadAllInstr().isEmpty()) {
            for (Instrument temp : persister.loadAllInstr()) {
                if (temp.equals(instr)) {
                    throw new Exception("Entry already existing");
                }
            }
        }
        persister.save(instr);
        return instr;
    }

    @Override
    public Leaser addLeaser(Leaser leaser) throws Exception {
        if (!persister.loadAllLeaser().isEmpty()) {
            for (Leaser temp : persister.loadAllLeaser()) {
                if (temp.equals(leaser)) {
                    throw new Exception("Entry already existing");
                }
            }
        }
        persister.save(leaser);
        return leaser;
    }

    @Override
    public ArrayList<Instrument> findInstrModel(String model) throws Exception {
        try {
            return persister.findInstrModel(model);
        } catch (Exception e) {
            throw new Exception("Model not found");
        }
    }

    @Override
    public ArrayList<Instrument> findInstrType(String type) throws Exception {
        try {
            return persister.findInstrType(type);
        } catch (Exception e) {
            throw new Exception("No such type");
        }
    }

    @Override
    public ArrayList<Instrument> findInstrManuf(String manuf) throws Exception {
        try {
            return persister.findInstrManuf(manuf);
        } catch (Exception e) {
            throw new Exception("Manufacturer not in catalog");
        }
    }

    @Override
    public Instrument findInstrId(int id) throws Exception {
        try {
            return persister.findInstrId(id);
        } catch (Exception e) {
            throw new Exception("No instrument found");
        }
    }

    @Override
    public ArrayList<Leaser> findLeaserName(String name) throws Exception {
        try {
            return persister.findLeaserName(name);
        } catch (Exception e) {
            throw new Exception("No client found");
        }
    }

    @Override
    public ArrayList<Leaser> findLeaserEmail(String email) throws Exception {
        try {
            return persister.findLeaserEmail(email);
        } catch (Exception e) {
            throw new Exception("No client found");
        }
    }

    @Override
    public ArrayList<Leaser> findLeaserCity(String city) throws Exception {
        try {
            return persister.findLeaserCity(city);
        } catch (Exception e) {
            throw new Exception("No client found");
        }
    }

    @Override
    public Leaser findLeaserId(String id) throws Exception {
        try {
            return persister.findLeaserId(id);
        } catch (Exception e) {
            throw new Exception("No client found");
        }
    }

    @Override
    public Leaser delete(Leaser leaser) throws Exception {
        try {
            persister.delete(leaser);
        } catch (Exception e) {
            throw new Exception("No client deleted");
        }
        return leaser;
    }

    @Override
    public Instrument delete(Instrument instrument) throws Exception {
        try {
            persister.delete(instrument);
        } catch (Exception e) {
            throw new Exception("No instrument deleted");
        }
        return instrument;
    }

    @Override
    public ArrayList<Instrument> showAllInstr() throws Exception {
        ArrayList<Instrument> listAll = new ArrayList<Instrument>(persister.loadAllInstr());
        if (!listAll.isEmpty()) {
            Collections.sort(listAll, new Comparator<Instrument>() {
                @Override
                public int compare(Instrument o1, Instrument o2) {
                    return o1.getInventoryId() - o2.getInventoryId();
                }
            });
            return listAll;
        }
        return null;
    }

    @Override
    public ArrayList<Leaser> showAllLeaser() throws Exception {
        ArrayList<Leaser> listAll = new ArrayList<Leaser>(persister.loadAllLeaser());
        if (!listAll.isEmpty()) {
            return listAll;
        }
        return null;
    }

    @Override
    public ArrayList<Integer> allInventoryId() throws Exception {
        ArrayList<Integer> allId = new ArrayList<Integer>();

        if (allId.isEmpty()) {
            for (Instrument temp : persister.loadAllInstr()) {
                allId.add(temp.getInventoryId());
            }
            Collections.sort(allId);
            return allId;
        }
        return null;
    }

    @Override
    public void rent(Leaser leaser, Instrument instr, LocalDate start, LocalDate end) throws Exception {
        if (instr.isLeased()) {
            throw new Exception("Already rented out");
        }

        instr.setStartLease(start);
        if (leaser.isClubTag()) {
            instr.setEndLease(end.plusMonths(leaser.getLeaseLengthClub()));
        } else {
            instr.setEndLease(end.plusMonths(leaser.getLeaseLengthPerson()));
        }
        persister.rent(leaser, instr);
    }

}
