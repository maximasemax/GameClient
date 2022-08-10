package model.impl;

import java.util.ArrayList;

public class FighterConfiguration {
    public String name;
    public ArrayList<Fighter> fighters;

    public FighterConfiguration(String name, ArrayList<Fighter> fighters) {
        this.name = name;
        this.fighters = fighters;
    }

    public FighterConfiguration() {
    }

    public ArrayList<Fighter> getFighters() {
        return fighters;
    }

    public void setFighters(ArrayList<Fighter> fighters) {
        this.fighters = fighters;
    }

}
