package com.music4you.business.api;

import com.music4you.domain.Instrument;
import com.music4you.domain.Leaser;

import java.util.ArrayList;

/**
 * Interface for administering the data file entries
 *
 * @author Eldaroth
 * @version 1.0
 */

public interface Administration {

    /**
     * Adds an instrument to the instrument data file
     * @param instr
     * @throws Exception
     */
    Instrument addInstrument(Instrument instr) throws Exception;

    /**
     * Adds a leaser to the leaser date file
     * @param leaser
     * @throws Exception
     */
    Leaser addLeaser(Leaser leaser) throws Exception;

    /**
     * Searches for an instrument(s) in the instrument data file with given attribute
     * @param model, type, manuf
     * @throws Exception
     */
    Instrument findInstrModel(String model) throws Exception;

    Instrument findInstrType(String type) throws Exception;

    Instrument findInstrManuf(String manuf) throws Exception;

    /**
     * Searches for a leaser(s) in the leaser data file with given attribute
     * @param name, email, city
     * @throws Exception
     */
    Leaser findLeaserName(String name) throws Exception;

    Leaser findLeaserEmail(String email) throws Exception;

    Leaser findLeaserCity(String city) throws Exception;

    /**
     * Shows all the entries in the instrument data file
     * @throws Exception
     */
    ArrayList<Instrument> showAllInstr() throws Exception;

    /**
     * Shows all the entries in the leaser data file
     * @return
     * @throws Exception
     */
    ArrayList<Leaser> showAllLeaser() throws Exception;

    int generateId() throws Exception;

}
