package model.impl;

public class Fighter {
    public int tipUser;
    public Person person;
    public Item item;

    public Fighter(int tipUser, Person person, Item item) {
        this.tipUser = tipUser;
        this.person = person;
        this.item = item;
    }

    public int getTipUser() {
        return tipUser;
    }

    public void setTipUser(int tipUser) {
        this.tipUser = tipUser;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
