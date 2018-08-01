package com.music4you.business.api;

import com.music4you.domain.Instrument;
import com.music4you.domain.Leaser;

import java.util.ArrayList;

public interface Administration {

    /**
     * Adds an instrument to the database file
     * @param instr
     * @return
     * @throws Exception
     */
    Object addInstrument(Instrument instr) throws Exception;

    Leaser addLeaser(Leaser leaser) throws Exception;

    //Person addPerson(Person person) throws Exception;

    /**
     * Searches for an instrument in the database file with given model
     * @param model
     * @return
     * @throws Exception
     */
    Instrument findInstrModel(String model) throws Exception;

    Instrument findInstrType(String type) throws Exception;

    Instrument findInstrManuf(String manuf) throws Exception;

    Leaser findLeaserName(String name) throws Exception;

    Leaser findLeaserEmail(String email) throws Exception;

    Leaser findLeaserCity(String city) throws Exception;

    /**
     * Shows all the Entries in the database file for a certain type of Object
     * @return
     * @throws Exception
     */
    ArrayList<Instrument> showAllInstr() throws Exception;

    ArrayList<Leaser> showAllLeaser() throws Exception;

    //ArrayList<Person> showAllPerson() throws Exception;

}
