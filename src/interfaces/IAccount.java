package interfaces;

import accountManagment.Account;
import accountManagment.BankAccount;
import accountManagment.Person;

import java.util.List;

public interface IAccount {

    void CreateBankAccount(BankAccount bankAccount, String accountNumber);

    List<BankAccount> ReadBankAccount(String bankAccountID);

    void DeleteBankAccount(BankAccount bankAccount);

    void CreateTransAction(Person person1, double amount, Person person2);

    void Deposit(String from, double amount, String to, int accountID);
}
