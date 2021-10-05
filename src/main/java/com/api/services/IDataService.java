package com.api.services;

import java.util.List;

import com.api.entity.Person;
import com.api.utils.RecordAlreadyExistsException;

public interface IDataService {
    public List<String> getPeople();
    public List<Person> getPeople2();
    public void addPerson(Person person) throws RecordAlreadyExistsException;
}
