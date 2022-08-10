package model.impl;

import java.util.List;

public class FighterConfiguration {
    public String name;
    public List<Fighter> fighters;

    public FighterConfiguration(String name, List<Fighter> fighters) {
        this.name = name;
        this.fighters = fighters;
    }

    public FighterConfiguration() {
    }

    public List<Fighter> getFighters() {
        return fighters;
    }

    public void setFighters(List<Fighter> fighters) {
        this.fighters = fighters;
    }
}
