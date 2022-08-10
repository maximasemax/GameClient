package model.service.request;

import model.impl.Person;
import model.service.jsonBuild.JsonBuildForPerson;

import java.io.IOException;
import java.util.List;

public class RequestPerson {

    public List<Person> getPersons() throws IOException, InterruptedException {
        RequestMetods requestMetods = new RequestMetods();
        String response = String.valueOf(requestMetods.sendRequest("http://localhost:8002/getPerson").body());
        char[] rezult = response.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : rezult) stringBuilder.append(c);
        String personsListStr = stringBuilder.toString();
        JsonBuildForPerson jsonBuildForPerson = new JsonBuildForPerson();
        return jsonBuildForPerson.fromJsonPersons(personsListStr);
    }
}
