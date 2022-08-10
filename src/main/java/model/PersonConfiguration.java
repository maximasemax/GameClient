package model;

import com.fasterxml.jackson.core.JsonProcessingException;
import model.impl.Person;


public interface PersonConfiguration {
    void addPerson(Person person) throws JsonProcessingException;

}
