package accountManagment;

import database.AccountDAO;
import interfaces.IAccount;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

public class Account implements IAccount {



    private int personID;
    private String userName;
    private String password;
    public ArrayList<Account> accounts;
    public ArrayList<BankAccount> bankAccounts;
    private ArrayList<Currency> currencies;
    AccountDAO accountDAO = new AccountDAO();

    public Account() {

    }

    public Account(String userName, String password) {
        this.accounts = new ArrayList<Account>();
        this.bankAccounts = new ArrayList<BankAccount>();
        this.userName = userName;
        this.password = password;
    }

    @Override
    public void CreateBankAccount(BankAccount bankAccount, String accountNumber) {
        accountDAO.CreateBankAccount(bankAccount, accountNumber);
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
    public void CreateTransAction(BankAccount bankAccount, double amount, BankAccount bankAccount2, int accountDeposit, int accountWithdraw){
        accountDAO.Deposit(bankAccount, amount, accountDeposit);

        accountDAO.WithDraw(bankAccount2, amount, accountWithdraw);
    }

    @Override
    public String toString() {
        return "Account{" +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
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

    public int getPersonID() {
        return personID;
    }
}
