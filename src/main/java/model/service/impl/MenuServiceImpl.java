package model.service.impl;

import model.impl.Fighter;
import model.impl.FighterConfiguration;
import model.impl.Item;
import model.impl.Person;
import model.service.jsonBuild.JsonBuildForFight;
import model.service.ItemService;
import model.service.MenuService;
import model.service.MessageService;
import model.service.request.RequestMetods;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class MenuServiceImpl implements MenuService {

    private final MessageService messageService = new MessageServiceImpl();
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void startMenuService() throws Exception {
        messageService.showStartMessage();
        start();
    }
//TODO  МЕТОД ОГРОМНЫЙ , РАЗДЕЛИТЬ И НАЗВАТЬ НОРМАЛЬНО !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    @Override
    public void startGame() throws IOException, InterruptedException, SQLException {
        PersonServiceImpl personService = new PersonServiceImpl();
        Person personUser = personService.chosePerson();
        ItemService itemServiceImpl = new ItemServiceImpl();
        List<Item> itemsUser = new ArrayList<>(itemServiceImpl.chooseItem());
        Person personBot = personService.chosePersonBot();
        System.out.println(personBot.getName() + "\n");
        List<Item> itemsBot = itemServiceImpl.chooseItemBot();
        for (Item item : itemsBot) {
            System.out.println(item.getName());
        }
        JsonBuildForFight jsonBuild = new JsonBuildForFight();
        RequestMetods requestMetods = new RequestMetods();
        while (personUser.getHp() > 0 && personBot.getHp() > 0) {
            Item itemUser = itemChoseUser(itemsUser);
            Item itemBot = itemChoseBot(itemsBot);
            Fighter fighterUser = new Fighter(0, personUser, itemUser);
            Fighter fighterBot = new Fighter(1, personBot, itemBot);
            ArrayList<Fighter> fighters = new ArrayList<>();
            fighters.add(fighterUser);
            fighters.add(fighterBot);
            FighterConfiguration fighterConfiguration = new FighterConfiguration("asd", fighters);
            String response = new String(requestMetods.PostRequestToServer("http://localhost:8002/fight",
                    jsonBuild.parserFighter(fighterConfiguration)));
            fighterConfiguration = jsonBuild.fromJsonFighter(response);
            personUser.setHp(fighterConfiguration.getFighters()
                    .get(0)
                    .getPerson()
                    .getHp());
            personBot.setHp(fighterConfiguration.getFighters()
                    .get(1)
                    .getPerson()
                    .getHp());
            if (personUser.getHp() < 0) {
                System.out.println("0 hp user\n");
            } else {
                System.out.println(personUser.getHp() + " hp " + personUser.getName() + " (you)\n");
            }
            if (personBot.getHp() < 0) {
                System.out.println("0 hp bot\n");
            } else {
                System.out.println(personBot.getHp() + " hp " + personBot.getName() + " (bot)\n");
            }
        }
        if (personUser.getHp() > 0) {
            System.out.println("You win!!!!!");
        } else {
            System.out.println("You lose((((");
        }
        exitGame();
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
