package com.music4you.business.api;

import com.music4you.domain.Club;
import com.music4you.domain.Instrument;
import com.music4you.domain.Leaser;
import com.music4you.domain.Person;

import java.util.List;

public interface Administration {

    /**
     * Adds an instrument to the database file
     *
     * @param instr
     * @return
     * @throws Exception
     */
    Object addInstrument(Instrument instr) throws Exception;

    Club addClub(Club club) throws Exception;

    Person addPerson(Person person) throws Exception;

    /**
     * Searches for an instrument in the database file with given model
     * @param model
     * @return
     * @throws Exception
     */
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

    List<Club> showAllClubs() throws Exception;

    List<Person> showAllPerson() throws Exception;

}
