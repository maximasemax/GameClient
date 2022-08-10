package model.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import model.PersonConfiguration;

import java.util.ArrayList;
import java.util.List;

public class PersonConfigurationImpl implements PersonConfiguration {

    private String name;
    private List<Person> persons;

    public PersonConfigurationImpl(String name) {
        this.name = name;
        this.persons = new ArrayList<>();
    }

    public PersonConfigurationImpl() {
    }

    public List<Person> getPersons() {
        return persons;
    }

    @Override
    public String toString() {
        return "PersoneConfiguration{"
                + "name='"
                + name
                + '\''
                + ", persons="
                + persons
                + '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void addPerson(Person person){
        persons.add(person);
    }
}
