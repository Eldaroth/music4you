package com.music4you.business.api;

import com.music4you.domain.Instrument;
import com.music4you.domain.Leaser;

import java.lang.reflect.Array;
import java.time.LocalDate;
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
    ArrayList<Instrument> findInstrModel(String model) throws Exception;

    ArrayList<Instrument> findInstrType(String type) throws Exception;

    ArrayList<Instrument> findInstrManuf(String manuf) throws Exception;

    Instrument findInstrId(int id) throws Exception;

    /**
     * Searches for a leaser(s) in the leaser data file with given attribute
     * @param name, email, city
     * @throws Exception
     */
    ArrayList<Leaser> findLeaserName(String name) throws Exception;

    ArrayList<Leaser> findLeaserEmail(String email) throws Exception;

    ArrayList<Leaser> findLeaserCity(String city) throws Exception;

    Leaser findLeaserId(String id) throws Exception;

    /**
     * Deletes a leaser/instrument from the respective data file
     * @param leaser, instrument
     * @throws Exception
     */

    Leaser delete(Leaser leaser) throws Exception;

    Instrument delete(Instrument instrument) throws Exception;

    /**
     * Methods for replacing an original instrument/leaser with the edited one
     * @param original
     * @param edited
     * @throws Exception
     */

    void replace(Instrument original, Instrument edited) throws Exception;

    void replace(Leaser original, Leaser edited) throws Exception;

    /**
     * Shows all the entries in the instrument data file
     * @throws Exception
     */
    ArrayList<Instrument> showAllInstr() throws Exception;

    /**
     * Shows all the entries in the leaser data file
     * @throws Exception
     */
    ArrayList<Leaser> showAllLeaser() throws Exception;

    /**
     * Generates a list with all already used InventoryIDs
     * @throws Exception
     */
    ArrayList<Integer> allInventoryId() throws Exception;

    /**
     * Method for renting out a instrument to a leaser
     * @param leaser
     * @param instr
     * @param start
     * @param end
     * @throws Exception
     */

    void rent(Leaser leaser, Instrument instr, LocalDate start, LocalDate end) throws Exception;

}
