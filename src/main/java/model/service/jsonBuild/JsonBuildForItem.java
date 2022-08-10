package model.service.jsonBuild;

import com.google.gson.Gson;
import model.impl.Item;
import model.impl.ItemConfigurationImpl;

import java.util.List;

public class JsonBuildForItem {

    public List<Item> FromJsonItem(String jsonString){
        return new Gson().fromJson(jsonString, ItemConfigurationImpl.class).getItems();
    }

    public String parserItems(List<Item> itemList) {
        return new Gson().toJson(itemList);
    }
}
