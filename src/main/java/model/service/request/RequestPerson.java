package model.service.request;

import model.impl.Person;
import model.service.jsonBuild.JsonBuildForPerson;

import java.io.IOException;
import java.util.List;

public class RequestPerson {

    static final String BASEURL = "http://localhost:8002";
    static final String PERSON = "/getPerson";

    public List<Person> getPersons() throws IOException, InterruptedException {
        RequestMetods requestMetods = new RequestMetods();
        String response = String.valueOf(requestMetods.sendRequest(BASEURL + PERSON).body());
        char[] rezult = response.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : rezult) stringBuilder.append(c);
        String personsListStr = stringBuilder.toString();
        JsonBuildForPerson jsonBuildForPerson = new JsonBuildForPerson();
        return jsonBuildForPerson.fromJsonPersons(personsListStr);
    }
}
