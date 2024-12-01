package com.example.money;

public abstract class Banknote {

    public Banknote(int banknoteNominale) {
        this.banknoteNominale = banknoteNominale;
    }


    public Banknote nextItem;


    public Banknote setNextItem(Banknote nextItem) {
        this.nextItem = nextItem;
        return nextItem;

    }
    public int banknoteNominale;

    public void process(int amount) {
        int quantity = amount / banknoteNominale;
        int rest = amount % banknoteNominale;

        if (nextItem != null) {
            nextItem.process(rest);
        } else if (rest > 0) {
            throw new IllegalArgumentException();
        }

        System.out.format("Provided %d x %d\n", banknoteNominale, quantity);

    }
}
