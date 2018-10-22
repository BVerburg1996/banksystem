package accountManagment;

import java.util.ArrayList;

public class BankAccount {

    //private int id;
    private int accountID;
    private double amount;
    private String description;

    public BankAccount(){

    }

    public BankAccount(double amount, String description, int accountID){
        this.accountID = accountID;
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

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }
}
