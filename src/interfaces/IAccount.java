package interfaces;

import accountManagment.Account;
import accountManagment.BankAccount;

import java.util.List;

public interface IAccount {

    void Create(Account account);

    Account Read(int id);

    void CreateBankAccount(BankAccount bankAccount);

    List<BankAccount> ReadBankAccount(int bankAccountID);

    void DeleteBankAccount(BankAccount bankAccount);

    void CreateTransAction(BankAccount bankAccount, double amount, BankAccount bankAccount2);


}
