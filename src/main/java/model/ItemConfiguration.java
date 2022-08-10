package model;

import com.fasterxml.jackson.core.JsonProcessingException;
import model.impl.Item;

public interface ItemConfiguration {
    void addItem(Item item) throws JsonProcessingException;
}
