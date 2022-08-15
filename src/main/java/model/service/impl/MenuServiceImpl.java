package model.service.impl;

import model.impl.Fighter;
import model.impl.FighterConfiguration;
import model.impl.Item;
import model.impl.Person;
import model.service.ItemService;
import model.service.MenuService;
import model.service.MessageService;
import model.service.PersonService;
import model.service.jsonBuild.JsonBuildForFight;
import model.service.request.RequestMetods;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class MenuServiceImpl implements MenuService {

    static final String BASEURL = "http://localhost:8002";
    static final String FIGHT = "/fight";
    private final MessageService messageService = new MessageServiceImpl();
    private final Scanner scanner = new Scanner(System.in);
    private final JsonBuildForFight jsonBuild = new JsonBuildForFight();
    private final RequestMetods requestMetods = new RequestMetods();

    @Override
    public void startMenuService() throws Exception {
        messageService.showStartMessage();
        start();
    }

    @Override
    public void startGame() throws IOException, InterruptedException, SQLException {
        PersonService personService = new PersonServiceImpl();
        Person personUser = personService.chosePerson();
        ItemService itemService = new ItemServiceImpl();
        List<Item> itemsUser = new ArrayList<>(itemService.chooseItem());
        Person personBot = personService.chosePersonBot();
        System.out.println(personBot.getName() + "\n");
        List<Item> itemsBot = itemService.chooseItemBot();
        itemsBot.forEach(item -> System.out.println(item.getName()));
        while (personUser.getHp() > 0 && personBot.getHp() > 0) {
            List<Person> personList = goToServerForFight(
                    chooseItemForFight(personUser, personBot, itemsUser, itemsBot));
            personUser.setHp(personList.get(0).getHp());
            personBot.setHp(personList.get(1).getHp());
            personList.forEach(this::showFightResult);
        }
        showCommonResult(personUser);
        exitGame();
    }

    private List<Person> goToServerForFight(List<Fighter> fighters) {
        FighterConfiguration fighterConfiguration = new FighterConfiguration("asd", fighters);
        String response = new String(requestMetods.postRequestToServer(BASEURL + FIGHT,
                jsonBuild.parserFighter(fighterConfiguration)));
        fighterConfiguration = jsonBuild.fromJsonFighter(response);
        List<Person> personList = new ArrayList<>();
        personList.add(fighterConfiguration.getFighters().get(0).getPerson());
        personList.add(fighterConfiguration.getFighters().get(1).getPerson());
        return personList;
    }

    private ArrayList<Fighter> chooseItemForFight(Person personUser, Person personBot
            , List<Item> itemsUser, List<Item> itemsBot) {
        Item itemUser = itemChoseUser(itemsUser);
        Item itemBot = itemChoseBot(itemsBot);
        Fighter fighterUser = new Fighter(0, personUser, itemUser);
        Fighter fighterBot = new Fighter(1, personBot, itemBot);
        ArrayList<Fighter> fighters = new ArrayList<>();
        fighters.add(fighterUser);
        fighters.add(fighterBot);
        return fighters;
    }

    private void showFightResult(Person person) {
        if (person.getHp() < 0) {
            System.out.println("0 hp " + person.getName() + "\n");
        } else {
            System.out.println(person.getHp() + " hp " + person.getName() + " \n");
        }
    }

    private void showCommonResult(Person personUser) {
        if (personUser.getHp() > 0) {
            System.out.println("You win!!!!!");
        } else {
            System.out.println("You lose((((");
        }
    }

    private void fight() {

    }

    @Override
    public void endGame() throws Exception {
        messageService.showEndMessage();
        exitGame();
    }

    @Override
    public void ruleMenu() throws Exception {
        messageService.showRulesMenu();
        exitFromRuleMenu();
    }

    @Override
    public void exitGame() {
        System.exit(0);
    }


    private void start() throws Exception {
        messageService.showCommandsMessage();
        int command = scanner.nextInt();
        switch (command) {
            case 1 -> ruleMenu();
            case 2 -> startGame();
            case 3 -> endGame();
            default -> System.out.println("You write wrong number(");
        }
    }

    private void exitFromRuleMenu() throws Exception {
        messageService.showCommandsInRuleMenuMessage();
        int command = scanner.nextInt();
        switch (command) {
            case 1 -> start();
            case 2 -> exitGame();
            default -> System.out.println("You write wrong number(");
        }
    }

    private Item itemChoseUser(List<Item> items) {
        StringBuilder stringBuilder = new StringBuilder();
        System.out.println("chose item at round! from:\n");
        int count = 0;
        for (Item item : items) {
            count += 1;
            stringBuilder.append(count).append(". Name: ").append(item.getName());
            stringBuilder.append("\n");
            stringBuilder.append("Attack skill: ").append(item.getDamageSkill());
            stringBuilder.append("\n");
            stringBuilder.append("Defence skill: ").append(item.getDefenceSkill());
            stringBuilder.append("\n");
            stringBuilder.append("\n");
        }
        System.out.println(stringBuilder);
        scanner.nextInt();
        return items.get(count - 1);
    }

    private Item itemChoseBot(List<Item> items) {
        System.out.println("bot chose!\n");
        Random random = new Random();
        Item item = items.get(random.nextInt(items.size()));
        System.out.println(item);
        return item;
    }
}
