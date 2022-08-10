package model.service.jsonBuild;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import model.impl.Person;
import model.impl.PersonConfigurationImpl;

import java.util.List;

public class JsonBuildForPerson {

    public List<Person> FromJsonPersons(String jsonString) {
        return new Gson().fromJson(jsonString, PersonConfigurationImpl.class).getPersons();
    }

    public String parserPerson(List<Person> personList){
        return new Gson().toJson(personList);
    }
}
