package model.service.impl;

import model.impl.Item;
import model.service.ItemService;
import model.service.request.RequestForItem;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ItemServiceImpl implements ItemService {

    Scanner scanner = new Scanner(System.in);

    @Override
    public void showAllItem() throws IOException, InterruptedException {
        List<Item> items = new ArrayList<>(getItem());

        StringBuilder stringBuilder = new StringBuilder();
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
    }

    @Override
    public List<Item> getItem() throws IOException, InterruptedException {
        RequestForItem requestForItem = new RequestForItem();
        return requestForItem.getItems();
    }

    @Override
    public List<Item> chooseItem() throws IOException, SQLException, InterruptedException {
        showAllItem();
        System.out.println("Please chose item and write item number.");
        List<Item> itemList = new ArrayList<>(getItem());
        List<Item> itemsChose = new ArrayList<>();
        int amount = 3;
        while (amount != 0) {
            amount -= 1;
            int nameOfItem = scanner.nextInt();
            itemsChose.add(itemList.get(nameOfItem - 1));
        }
        System.out.println("Items selected!");
        return itemsChose;
    }

    @Override
    public List<Item> chooseItemBot() throws IOException, InterruptedException {
        Random random = new Random();
        System.out.println("Bots chose!");
        ArrayList<Item> items = new ArrayList<>(getItem());
        ArrayList<Item> itemsBot = new ArrayList<>();
        int amount = 3;
        while (amount != 0) {
            amount = amount - 1;
            Item itemBot = items.get(random.nextInt(getItem().size()));
            while (itemsBot.contains(itemBot)) {
                itemBot = items.get(random.nextInt(getItem().size()));
            }
            itemsBot.add(itemBot);
        }
        return itemsBot;
    }
}
