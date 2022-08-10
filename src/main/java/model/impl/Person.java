package model.impl;

import java.util.Objects;

public class Person {
    public String name;
    public float hp;
    public float attackSkill;
    public float defenceSkill;

    public Person(String name, float hp, float attackSkill, float defenceSkill) {
        this.name = name;
        this.attackSkill = attackSkill;
        this.defenceSkill = defenceSkill;
        this.hp = hp;
    }

    public Person() {
    }

    public float getAttackSkill() {
        return attackSkill;
    }

    public float getDefenceSkill() {
        return defenceSkill;
    }

    public float getHp() {
        return hp;
    }

    public void setHp(float hp) {
        this.hp = hp;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return name +
                "\n" +
                attackSkill +
                "\n" +
                defenceSkill +
                "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Float.compare(person.hp, hp) == 0 && Float.compare(person.attackSkill, attackSkill) == 0 &&
                Float.compare(person.defenceSkill, defenceSkill) == 0 && Objects.equals(name, person.name);
    }
//TODO рассказать про различные способы сравнения(компеар, equals, и просто равно равно или больше меньше между двумя переменными)
    @Override
    public int hashCode() {
        return Objects.hash(name, hp, attackSkill, defenceSkill);
    }
}
