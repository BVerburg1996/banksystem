package transaction;

import accountManagment.BankAccount;

public class Transaction {
    //private int id;
    private BankAccount bankAccount1;
    private BankAccount bankAccount2;
    private double amount;
    private String transactionReason;
    private String languageForCurrency;
    //private double withdraw?

    public Transaction(){

    }

    public Transaction(BankAccount bankAccount1, BankAccount bankAccount2, double amount, String transactionReason, String languageForCurrency){
        this.bankAccount1 = bankAccount1;
        this.bankAccount2 = bankAccount1;
        this.amount = amount;
        this.transactionReason = transactionReason;
        this.languageForCurrency = languageForCurrency;
    }

    public BankAccount getBankAccount1() {
        return bankAccount1;
    }

    public void setBankAccount1(BankAccount bankAccount1) {
        this.bankAccount1 = bankAccount1;
    }

    public BankAccount getBankAccount2() {
        return bankAccount2;
    }

    public void setBankAccount2(BankAccount bankAccount2) {
        this.bankAccount2 = bankAccount2;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTransactionReason() {
        return transactionReason;
    }

    public void setTransactionReason(String transactionReason) {
        this.transactionReason = transactionReason;
    }

    public String getLanguageForCurrency() {
        return languageForCurrency;
    }

    public void setLanguageForCurrency(String languageForCurrency) {
        this.languageForCurrency = languageForCurrency;
    }
}
