package model.service;

import model.impl.Person;

import java.io.IOException;
import java.util.List;

public interface PersonService {

    void showAllPerson() throws IOException, InterruptedException;

    List<Person> getPersons() throws IOException, InterruptedException;

    Person chosePerson() throws IOException, InterruptedException;

    Person chosePersonBot() throws IOException, InterruptedException;


}
