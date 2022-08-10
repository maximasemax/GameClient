package model.service;

import model.impl.Item;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface ItemService {
    void showAllItem() throws IOException, SQLException, InterruptedException;

    List<Item> getItem() throws IOException, SQLException, InterruptedException;

    List<Item> chooseItem() throws IOException, SQLException, InterruptedException;


    List<Item> chooseItemBot() throws IOException, SQLException, InterruptedException;
}
