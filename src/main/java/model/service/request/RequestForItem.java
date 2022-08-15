package model.service.request;

import model.impl.Item;
import model.service.jsonBuild.JsonBuildForItem;

import java.io.IOException;
import java.util.List;

public class RequestForItem {

    static final String BASEURL = "http://localhost:8002";
    static final String ITEM = "/getItem";

    public List<Item> getItems() throws IOException, InterruptedException {
        RequestMetods requestMetods = new RequestMetods();
        String response = String.valueOf(requestMetods.sendRequest(BASEURL + ITEM).body());
        char[] rezult = response.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : rezult) stringBuilder.append(c);
        String itemsListStr = stringBuilder.toString();
        JsonBuildForItem jsonBuildForItem = new JsonBuildForItem();
        return jsonBuildForItem.fromJsonItem(itemsListStr);
    }
}
