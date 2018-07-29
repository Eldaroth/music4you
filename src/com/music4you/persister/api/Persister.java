package com.music4you.persister.api;

import com.music4you.domain.*;

import java.util.List;

public interface Persister {

    /**
     * Saves a new instrument
     *
     * @param instr
     * @return
     * @throws Exception
     */
    Instrument save(Instrument instr) throws Exception;

    /**
     * Searches for the instrument with given model
     * @param model
     * @return
     * @throws Exception
     */
    Instrument findInstrModel(String model) throws Exception;

    Instrument findInstrType(String type) throws Exception;

    Instrument findInstrManuf(String manuf) throws Exception;

    /**
     * Loads all instruments objects in the file
     *
     * @return
     * @throws Exception
     */
    List<Instrument> loadAllInstr() throws Exception;

}
