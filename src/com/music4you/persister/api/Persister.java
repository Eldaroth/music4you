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
    //TODO check which Exceptions are really needed and clean up your code

    /**
     * Saves a new instrument or leaser
     *
     * @param instr
     * @throws Exception
     */
    void save(Instrument instr) throws Exception;

    void save(Leaser leaser) throws Exception;


    /**
     * Searches for an instrument with given parameter
     * @param model, type, manuf
     * @return Instrument
     * @throws Exception
     */
    Instrument findInstrModel(String model) throws Exception;

    Instrument findInstrType(String type) throws Exception;

    Instrument findInstrManuf(String manuf) throws Exception;

    /**
     * Searches for a leaser with given parameter
     * @param name, email, city
     * @return
     * @throws Exception
     */
    Leaser findLeaserName(String name) throws Exception;

    Leaser findLeaserEmail(String email) throws Exception;

    Leaser findLeaserCity(String city) throws Exception;

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

}
