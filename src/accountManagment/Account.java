package accountManagment;

import database.AccountDAO;
import interfaces.IAccount;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

public class Account implements IAccount {
    //private int id;
    private int personID;
    private String userName;
    private String password;
    public ArrayList<Account> accounts;
    public ArrayList<BankAccount> bankAccounts;
    private ArrayList<Currency> currencies;
    AccountDAO accountDAO = new AccountDAO();

    public Account() {

    }

    public Account(int personID, String userName, String password) {
        this.accounts = new ArrayList<Account>();
        this.bankAccounts = new ArrayList<BankAccount>();
        this.personID = personID;
        this.userName = userName;
        this.password = password;
    }

    @Override
    public void Create(Account account) {

       accountDAO.CreateAccount(account);
    }

    @Override
    public Account Read(int id) {
     return accountDAO.read(id);
    }

    @Override
    public void CreateBankAccount(BankAccount bankAccount) {
        accountDAO.CreateBankAccount(bankAccount);
    }

    @Override
    public List<BankAccount> ReadBankAccount(int bankAccountID) {

        return accountDAO.readAll(bankAccountID);
    }

    @Override
    public void DeleteBankAccount(BankAccount bankAccount) {
        accountDAO.Delete(bankAccount);
    }

    @Override
    public void CreateTransAction(BankAccount bankAccount, double amount, BankAccount bankAccount2){
        accountDAO.Deposit(bankAccount, amount);

        accountDAO.WithDraw(bankAccount2, amount);
    }

    @Override
    public String toString() {
        return "Account{" +
                "person=" + personID +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public int getpersonID() {
        return personID;
    }

    public void setPersonID(int personID) {
        this.personID = personID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    

}
