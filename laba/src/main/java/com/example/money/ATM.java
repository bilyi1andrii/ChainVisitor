package com.example.money;

public class ATM {
    private Banknote firstTray;
    public ATM() {

        firstTray = new BanknoteHundred();
        firstTray.setNextItem(new BanknoteFifty())
        .setNextItem(new BanknoteFive());

    }

    public void process(int amount) {
        firstTray.process(amount);
    }
}
