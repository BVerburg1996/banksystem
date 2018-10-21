package paymentCurrency;


import java.util.ArrayList;

public class Currency {
    //private int ID;
    private double amount;
    private String personsLanguage;
    private String description;

    public Currency(){

    }

    public Currency(double amount, String personsLanguage, String description){
        this.amount = amount;
        this.personsLanguage = personsLanguage;
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPersonsLanguage() {
        return personsLanguage;
    }

    public void setPersonsLanguage(String personsLanguage) {
        this.personsLanguage = personsLanguage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
