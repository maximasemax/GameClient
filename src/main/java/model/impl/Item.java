package model.impl;

public class Item {
    public String name;
    public int damageSkill;
    public int defenceSkill;

    public Item(String name, int damageSkill, int defenceSkill) {
        this.name = name;
        this.damageSkill = damageSkill;
        this.defenceSkill = defenceSkill;
    }

    public Item() {
    }

    public int getDefenceSkill() {
        return defenceSkill;
    }

    public int getDamageSkill() {
        return damageSkill;
    }

    public String getName() {
        return name;
    }

    public void setDamageSkill(int damageSkill) {
        this.damageSkill = damageSkill;
    }

    public void setDefenceSkill(int defenceSkill) {
        this.defenceSkill = defenceSkill;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name +
                "\n" +
                damageSkill +
                "\n" +
                defenceSkill +
                "\n";
    }
}
