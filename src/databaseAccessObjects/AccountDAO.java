package databaseAccessObjects;

import accountManagment.BankAccount;
import accountManagment.Person;
import interfaces.IAccount;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO implements IAccount {

    //Declarations
    Connection connection;
    Statement statement;
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    @Override
    //Creates an bank account
    public void CreateBankAccount(BankAccount bankAccount, String bankAccountNumber) {

        //Checks if bankaccount is not null and account number is not null
        if (bankAccount != null && bankAccountNumber != null) {

            try {
                //Makes connect with the databaseAccessObjects
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank?serverTimezone=UTC", "root", "");

                //Query to get the ID from the person in the databaseAccessObjects using the bankaccount number
                String getIDFromPersonQuery = "SELECT ID FROM person WHERE AccountNumber = " + bankAccountNumber + ";";
                //Creates statement
                statement = connection.createStatement();
                //Executes query
                resultSet = statement.executeQuery(getIDFromPersonQuery);
                //Gets the ID of the person and puts it in a variable
                resultSet.next();
                int personID = resultSet.getInt(1);

                //Query to get the ID of the account using the personid
                String getIDFROMAccountQuery = "SELECT ID FROM account WHERE person_ID = " + personID + ";";
                //Creates statement
                statement = connection.createStatement();
                //Executes query
                resultSet = statement.executeQuery(getIDFROMAccountQuery);
                //Gets the id of the account and puts it in a variable
                resultSet.next();
                int accountNumber = resultSet.getInt(1);

                //Query to create a bank account
                String createBankAccountQuery = "INSERT INTO bankaccount (Amount, Description, account_ID) VALUES(?, ?, ?)";

                //PreparedStatemen, Sets values to make an bank account
                preparedStatement = connection.prepareStatement(createBankAccountQuery);
                preparedStatement.setDouble(1, bankAccount.getAmount());
                preparedStatement.setString(2, bankAccount.getDescription());
                preparedStatement.setInt(3, accountNumber);

                //Executes update
                preparedStatement.executeUpdate();
                //Closes connection
                connection.close();
            } catch (SQLException sqlex) {
                System.out.println(sqlex);
            }

        }
    }

    @Override
    public List<BankAccount> ReadBankAccount(String bankAccountID) {
        return null;
    }

    //Returns a list of bankaccounts the user has using his accountnumber
    public ArrayList<BankAccount> ReadAll(String accountNumber) {

        //ArrayList of bankaccounts
        ArrayList<BankAccount> bankAccounts = new ArrayList<>();
        try {

            //Makes connection with the databaseAccessObjects
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank?serverTimezone=UTC", "root", "");

            //Query to get ID of person out of the databaseAccessObjects using his accountnumber
            String getIDFromQuery = "SELECT ID FROM person WHERE AccountNumber = '" + accountNumber + "';";
            //Creates statement
            statement = connection.createStatement();
            //Executes statement
            resultSet = statement.executeQuery(getIDFromQuery);
            //Gets the ID of the person and puts in in a variable
            resultSet.next();
            int personID = resultSet.getInt(1);

            //Query to get ID from account using the personid
            String getIDFromAccountQuery = "SELECT ID FROM account WHERE person_ID = " + personID + ";";
            //Creates statement
            statement = connection.createStatement();
            //Executes query
            resultSet = statement.executeQuery(getIDFromAccountQuery);

            //Gets the ID of the account and puts it in a variable
            resultSet.next();
            int accountID = resultSet.getInt("ID");

            //Query to get all the bankaccounts where the account_ID is equal to your account id
            String getAllBankAccountsQuery = "SELECT * FROM bankaccount WHERE account_ID = " + accountID + ";";

            //Creates statement
            statement = connection.createStatement();
            resultSet = statement.executeQuery(getAllBankAccountsQuery);


            if (resultSet != null) {
                while (resultSet.next()) {
                    BankAccount bankAccount = new BankAccount(resultSet.getDouble("Amount"), resultSet.getString("Description"));

                    bankAccounts.add(bankAccount);
                }
            }
            connection.close();
        } catch (SQLException sql) {
            System.out.println(sql);
            bankAccounts = null;
        }


        return bankAccounts;
    }

    @Override
    public void DeleteBankAccount(BankAccount bankAccount) {
        if (bankAccount != null) {

            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank?serverTimezone=UTC", "root", "");

                String deletequery = "DELETE FROM bankaccount WHERE Description = '" + bankAccount.getDescription() + "' AND Amount = " + bankAccount.getAmount() + ";";
                statement = connection.createStatement();
                statement.executeUpdate(deletequery);

            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }

    public void CreateTransAction(Person person1, double amount, Person person2) {

        double depositedAmount;
        double amountToWithdraw;

        if (person1 != null && person2 != null && amount != 0) {
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank?serverTimezone=UTC", "root", "");

                String getDepositAccountIDQuery = "SELECT ID FROM account WHERE person_ID = " + person1.getID() + ";";
                statement = connection.createStatement();
                resultSet = statement.executeQuery(getDepositAccountIDQuery);
                resultSet.next();
                int depositAccountID = resultSet.getInt(1);

                String getDepositBankAccountQuery = "SELECT * FROM bankaccount WHERE account_ID = " + depositAccountID + " AND Description = 'main';";
                statement = connection.createStatement();
                resultSet = statement.executeQuery(getDepositBankAccountQuery);
                resultSet.next();

                BankAccount depositBankAccount = new BankAccount(resultSet.getDouble("Amount"), resultSet.getString("Description"));

                String getWithdrawAccountIDQuery = "SELECT ID FROM Account WHERE person_ID = " + person2.getID() + ";";
                statement = connection.createStatement();
                resultSet = statement.executeQuery(getWithdrawAccountIDQuery);
                resultSet.next();

                int withDrawAccountID = resultSet.getInt(1);

                String getWithDrawBankAccountQuery = "SELECT * FROM bankaccount WHERE account_ID = " + withDrawAccountID + " AND Description = 'main';";
                statement = connection.createStatement();
                resultSet = statement.executeQuery(getWithDrawBankAccountQuery);
                resultSet.next();
                BankAccount withdrawBankAccount = new BankAccount(resultSet.getDouble("Amount"), resultSet.getString("Description"));

                depositedAmount = depositBankAccount.getAmount() - amount;
                String updateDepositBankAccount = "UPDATE bankaccount SET Amount=? WHERE account_ID = " + depositAccountID + " AND Description = 'main';";

                preparedStatement = connection.prepareStatement(updateDepositBankAccount);
                preparedStatement.setDouble(1, depositedAmount);
                preparedStatement.executeUpdate();

                String updateWithdrawBankAccount = "UPDATE bankaccount SET Amount=? WHERE account_ID = " + withDrawAccountID + " AND Description = 'main';";

                if (person1.getLanguage().equals(person2.getLanguage())) {

                    amountToWithdraw = withdrawBankAccount.getAmount() + amount;

                    preparedStatement = connection.prepareStatement(updateWithdrawBankAccount);
                    preparedStatement.setDouble(1, amountToWithdraw);
                    preparedStatement.executeUpdate();
                } else {

                    String getCurrencyValue = "SELECT Value FROM currency WHERE Language = '" + person2.getLanguage() + "';";
                    statement = connection.createStatement();
                    resultSet = statement.executeQuery(getCurrencyValue);
                    resultSet.next();
                    double currencyValue = resultSet.getDouble(1);

                    amount = amount * currencyValue;

                    amountToWithdraw = withdrawBankAccount.getAmount() + amount;

                    preparedStatement = connection.prepareStatement(updateWithdrawBankAccount);
                    preparedStatement.setDouble(1, amountToWithdraw);
                    preparedStatement.executeUpdate();

                    connection.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex);
            }

        }
    }

    public void Deposit(String from, double amount, String to, int accountID) {

        double amounTo;
        double amountWithdraw;
        if (from != null && amount != 0 && to != null) {

            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank?serverTimezone=UTC", "root", "");

                String getBankAccount = "SELECT Amount FROM bankaccount WHERE account_ID = " + accountID + " AND Description = '" + from + "';";
                statement = connection.createStatement();
                resultSet = statement.executeQuery(getBankAccount);
                resultSet.next();
                double depositBankAccountAmount = resultSet.getDouble(1);

                amounTo = depositBankAccountAmount - amount;

                String updateDepositBankAccount = "UPDATE bankaccount SET Amount=? WHERE account_ID = " + accountID + " AND Description = '" + from + "';";
                preparedStatement = connection.prepareStatement(updateDepositBankAccount);
                preparedStatement.setDouble(1, amounTo);
                preparedStatement.executeUpdate();

                String getBankAccountWithdraw = "SELECT Amount FROM bankaccount WHERE account_ID = " + accountID + " AND Description = '" + to + "';";
                statement = connection.createStatement();
                resultSet = statement.executeQuery(getBankAccountWithdraw);
                resultSet.next();
                double withdrawBankAccountAmount = resultSet.getDouble(1);

                amountWithdraw = withdrawBankAccountAmount + amount;

                String updateWithdrawBankAccount = "UPDATE bankaccount SET Amount=? WHERE account_ID = " + accountID + " AND Description = '" + to + "';";
                preparedStatement = connection.prepareStatement(updateWithdrawBankAccount);
                preparedStatement.setDouble(1, amounTo);
                preparedStatement.executeUpdate();

            } catch (SQLException ex) {
                System.out.println(ex);
            }

        }
    }

}
