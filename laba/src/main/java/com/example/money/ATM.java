package com.example.money;

public class ATM {
    private Banknote firstTray;
    public ATM() {

        firstTray = new Banknote100();
        firstTray.setNextItem(new Banknote50()).setNextItem(new Banknote5());

    }

    public void process(int amount) {
        firstTray.process(amount);
    }
}
