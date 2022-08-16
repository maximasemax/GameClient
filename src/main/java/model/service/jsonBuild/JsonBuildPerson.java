package model.service.jsonBuild;

import com.google.gson.Gson;
import model.impl.Person;
import model.impl.PersonConfigurationImpl;

import java.util.List;

public class JsonBuildPerson {

    public List<Person> fromJsonPersons(String jsonString) {
        return new Gson().fromJson(jsonString, PersonConfigurationImpl.class).getPersons();
    }

    public String parserPerson(List<Person> personList){
        return new Gson().toJson(personList);
    }
}
