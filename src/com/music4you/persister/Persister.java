package com.music4you.persister;

import com.music4you.domain.*;

public interface Persister {

    Person save(Person person) throws Exception;

}
