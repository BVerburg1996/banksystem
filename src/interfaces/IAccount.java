package interfaces;

import accountManagment.Account;
import accountManagment.BankAccount;

import java.util.List;

public interface IAccount {

    void CreateBankAccount(BankAccount bankAccount, String accountNumber);

    List<BankAccount> ReadBankAccount(int bankAccountID);

    void DeleteBankAccount(BankAccount bankAccount);

    void CreateTransAction(BankAccount bankAccount, double amount, BankAccount bankAccount2, int accountDeposit, int accountWithdraw);


}
