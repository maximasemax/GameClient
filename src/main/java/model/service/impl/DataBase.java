package model.service.impl;

import model.impl.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DataBase {
    static final String DbUrl = "jdbc:postgresql://localhost:5432/Item";
    static final String USER = "postgres";
    static final String PASS = "postgres";

    private void tryStartDriver() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
            e.printStackTrace();
            return;
        }
        System.out.println("PostgreSQL JDBC Driver successfully connected");
    }

    private Optional<Connection> connectToDataBase() {
        try {
            Optional<Connection> connectionOptional = Optional.ofNullable(DriverManager
                    .getConnection(DbUrl, USER, PASS));
            System.out.println("You successfully connected to database now");
            return connectionOptional;
        } catch (SQLException e) {
            System.out.println("Failed to make connection to database");
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public List<Item> getItemsFromDataBase() {
        tryStartDriver();
        Optional<Connection> connection = connectToDataBase();
        return connection.map(this::doGetItems).orElseGet(ArrayList::new);
    }

    private List<Item> doGetItems(Connection connection) {
        List<Item> itemList = new ArrayList<>();
        try {
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM items");
            while (resultSet.next()) {
                Item item = new Item();
                System.out.println("ID: " + resultSet.getInt("id"));
                item.setName(resultSet.getString("name"));
                item.setDamageSkill(resultSet.getInt("damage_skill"));
                item.setDefenceSkill(resultSet.getInt("defence_skill"));
                itemList.add(item);
            }
        } catch (SQLException e) {
            System.out.println("Holly shit");
        }
        return itemList;
    }
}
