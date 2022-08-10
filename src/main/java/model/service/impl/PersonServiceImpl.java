package model.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import model.impl.Person;
import model.impl.PersonConfigurationImpl;
import model.service.PersonService;
import model.service.request.RequestPerson;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class PersonServiceImpl implements PersonService {

    Scanner scanner = new Scanner(System.in);

    @Override
    public void showAllPerson() throws IOException, InterruptedException {
        List<Person> personList = new ArrayList<>(getPersons());
        StringBuilder stringBuilder = new StringBuilder();
        int count = 0;
        for (Person person : personList) {
            count += 1;
            stringBuilder.append(count).append(". Name: ").append(person.getName());
            stringBuilder.append("\n");
            stringBuilder.append("Attack skill: ").append(person.getAttackSkill());
            stringBuilder.append("\n");
            stringBuilder.append("Defence skill: ").append(person.getDefenceSkill());
            stringBuilder.append("\n");
            stringBuilder.append("\n");
        }
        System.out.println(stringBuilder);
    }

    @Override
    public List<Person> getPersons() throws IOException, InterruptedException {
        RequestPerson requestPerson = new RequestPerson();
        return requestPerson.getPersons();
    }

    @Override
    public Person chosePerson() throws IOException, InterruptedException {
        showAllPerson();
        System.out.println("Please chose person and write name.");
        int numberOfPerson = scanner.nextInt();
        List<Person> personList = new ArrayList<>(getPersons());
        System.out.println("Person selected!\n");
        return personList.get(numberOfPerson - 1);

    }

    @Override
    public Person chosePersonBot() throws IOException, InterruptedException {
        Random random = new Random();
        System.out.println("Bots chose!");
        return getPersons().get(random.nextInt(getPersons().size()));
    }

    public List<Person> getPersonsFromFile() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        PersonConfigurationImpl personConfiguration = objectMapper.readValue(new File("src\\main\\resources\\persons.yml"), PersonConfigurationImpl.class);
        return personConfiguration.getPersons();
    }
}
