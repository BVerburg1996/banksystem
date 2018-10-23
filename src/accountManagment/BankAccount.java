package accountManagment;

import java.util.ArrayList;

public class BankAccount {

    private double amount;
    private String description;

    public BankAccount(){

    }

    public BankAccount(double amount, String description) {
        this.amount = amount;
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "amount=" + amount +
                ", description='" + description + '\'' +
                '}';
    }
}
