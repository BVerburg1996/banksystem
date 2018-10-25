package accountManagment;

import database.AccountDAO;
import interfaces.IAccount;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    BankAccount bankAccount2 = new BankAccount();
    Person person = new Person ("FirstName", "LastName", "AccountNumber", "Email", "Language");
    Account account = new Account("username", "wachtwoord");
    AccountDAO accountDAO = new AccountDAO();


    @Test
    public void testCreateRead() {

        List<BankAccount> bankAccounts = new ArrayList<>();
        List<BankAccount> bankAccounts2 = new ArrayList<>();

        BankAccount bankAccount = new BankAccount(200, "Beschrijving");

        account.CreateBankAccount(bankAccount, "AccountNumber");

        bankAccounts = account.ReadBankAccount("AccountNumber");

        assertNotNull(bankAccount);

        accountDAO.DeleteBankAccount(bankAccount);

        bankAccounts2 = accountDAO.ReadBankAccount("AccountNumber");

        assertNull(bankAccounts2);
    }
}