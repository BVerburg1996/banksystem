package accountManagment;

import interfaces.IAccount;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest implements IAccount {

    @Test
    @Override
    public void CreateBankAccount(BankAccount bankAccount, String accountNumber) {

    }

    @Test
    @Override
    public List<BankAccount> ReadBankAccount(int bankAccountID) {
        return null;
    }

    @Test
    @Override
    public void DeleteBankAccount(BankAccount bankAccount) {

    }


    @Test
    @Override
    public void CreateTransAction(BankAccount bankAccount, double amount, BankAccount bankAccount2, int accountDeposit, int accountWithdraw) {

    }
}