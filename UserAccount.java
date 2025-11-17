package ua.lab.parallel;

public class UserAccount {

    private final int number;
    private int funds;

    public UserAccount(int number, int initialFunds) {
        this.number = number;
        this.funds = initialFunds;
    }

    public int getNumber() {
        return number;
    }

    public synchronized int currentFunds() {
        return funds;
    }

    public synchronized void credit(int amount) {
        funds += amount;
    }

    public synchronized void debit(int amount) {
        funds -= amount;
    }
}

