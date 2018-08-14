package com.music4you.persister.api;

import com.music4you.domain.*;

import java.util.ArrayList;

/**
 * Interface responsible for interacting with the data files
 *
 * @author Eldaroth
 * @version 1.0
 */

public interface Persister {

    /**
     * Saves a new instrument or leaser
     *
     * @param instr
     * @throws Exception
     */
    void save(Instrument instr) throws Exception;

    void save(Leaser leaser) throws Exception;

//    void rent(Leaser leaser, Instrument instr) throws Exception;

    /**
     * Deletes the client or instrument from the data file
     * @param leaser, instrument
     * @throws Exception
     */
    void delete(Leaser leaser) throws Exception;

    void delete(Instrument instrument) throws Exception;

    void replace(Instrument original, Instrument edited) throws Exception;

    void replace(Leaser original, Leaser edited) throws Exception;

    /**
     * Searches for an instrument with given parameter
     * @param model, type, manuf
     * @return Instrument
     * @throws Exception
     */
    ArrayList<Instrument> findInstrModel(String model) throws Exception;

    ArrayList<Instrument> findInstrType(String type) throws Exception;

    ArrayList<Instrument> findInstrManuf(String manuf) throws Exception;

    Instrument findInstrId(int id) throws Exception;

    /**
     * Searches for a leaser with given parameter
     * @param name, email, city
     * @return
     * @throws Exception
     */
    ArrayList<Leaser> findLeaserName(String name) throws Exception;

    ArrayList<Leaser> findLeaserEmail(String email) throws Exception;

    ArrayList<Leaser> findLeaserCity(String city) throws Exception;

    Leaser findLeaserId(String id) throws Exception;

    /**
     * Loads all Instrument objects in data file
     * @return
     * @throws Exception
     */
    ArrayList<Instrument> loadAllInstr() throws Exception;

    /**
     * Loads all Leaser objects in data file
     * @return
     * @throws Exception
     */
    ArrayList<Leaser> loadAllLeaser() throws Exception;

    /**
     * Method for getting all used inventory ID for instruments
     * @return
     * @throws Exception
     */
    ArrayList<Integer> allInventoryId() throws Exception;
}
