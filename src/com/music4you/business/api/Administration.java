package com.music4you.business.api;

import com.music4you.domain.Instrument;

import java.util.List;

public interface Administration {

    /**
     * Adds an Object (Instrument, Leaser, etc.) to the database file
     *
     * @param instr
     * @return
     * @throws Exception
     */
    Object addInstrument(Instrument instr) throws Exception;

    Instrument findInstrModel(String model) throws Exception;

    Instrument findInstrType(String type) throws Exception;

    Instrument findInstrManuf(String manuf) throws Exception;

    /**
     * Shows all the Entries in the database file for a certain type of Object
     *
     * @return
     * @throws Exception
     */
    List<Instrument> showAllInstr() throws Exception;

}
