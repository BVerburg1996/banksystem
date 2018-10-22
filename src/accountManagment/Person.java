package accountManagment;

import database.PersonDAO;
import interfaces.IPerson;

import java.util.ArrayList;
import java.util.Date;
import java.util.SortedMap;

public class Person implements IPerson {

    private int ID;
    private String firstName;
    private String lastName;
    private String accountNumber;
    private String email;
    private final int rend = 1;
    private Date dateOfBirth;
    private String language;
    PersonDAO personDAO = new PersonDAO();
    public ArrayList<Person> persons;

    public Person(String iemand, String s, String ab25258, String diederikn, String s1){

    }

    public Person(String firstName, String lastName, String accountNumber, String email, Date dateOfBirth, String language){
        persons = new ArrayList<Person>();
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountNumber = accountNumber;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.language = language;
    }

    public Person(int ID, String firstName, String lastName, String accountNumber, String email, Date dateOfBirth, String language){
        persons = new ArrayList<Person>();
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountNumber = accountNumber;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.language = language;
    }



    @Override
    public void CreateAccount(Person person, Account account) {

        personDAO.CreateAccount(person, account);
    }

    @Override
    public Person Read(String string) {

        return personDAO.read(string);
    }

    @Override
    public String toString(){
        return "FirstName: " + firstName + "\n" +
                "LastName: " + lastName + "\n" +
                "AccountNumber: " + accountNumber + "\n" +
                "Email: " + email + "\n" +
                "Date: " + dateOfBirth + "\n" +
                "language: " + language;


    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRend() {
        return rend;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getID() {
        return ID;
    }
}
