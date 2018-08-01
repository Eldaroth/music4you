package com.music4you.persister.api;

import com.music4you.domain.*;

import java.util.ArrayList;

public interface Persister {

    /**
     * Saves a new instrument
     *
     * @param instr
     * @throws Exception
     */
    void save(Instrument instr) throws Exception;

    void save(Leaser leaser) throws Exception;

    //void save(Person person) throws Exception;

    /**
     * Searches for the instrument with given model
     * @param model
     * @return Instrument
     * @throws Exception
     */
    Instrument findInstrModel(String model) throws Exception;

    /**
     * Searches for the instrument with given type
     * @param type
     * @return Instrument
     * @throws Exception
     */
    Instrument findInstrType(String type) throws Exception;

    /**
     * Searches for the instrument with given manufacturer
     * @param manuf
     * @return Instrument
     * @throws Exception
     */
    Instrument findInstrManuf(String manuf) throws Exception;

    Leaser findLeaserName(String name) throws Exception;

    Leaser findLeaserEmail(String email) throws Exception;

    Leaser findLeaserCity(String city) throws Exception;

    /**
     * Loads all instruments objects in file
     *
     * @return
     * @throws Exception
     */
    ArrayList<Instrument> loadAllInstr() throws Exception;

    /**
     * Loads all club objects in file
     * @return
     * @throws Exception
     */
    ArrayList<Leaser> loadAllLeaser() throws Exception;

    /**
     * Loads all person objects in file
     * @return
     * @throws Exception
     */
    //List<Person> loadAllPerson() throws Exception;

}
