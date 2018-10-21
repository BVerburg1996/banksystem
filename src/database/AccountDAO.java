package database;

import accountManagment.Account;
import accountManagment.BankAccount;
import accountManagment.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {

    DatabaseConnection connection = new DatabaseConnection();

    public void CreateAccount(Account account) {

        Connection conn;

        if (account != null) {

            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank?serverTimezone=UTC", "root", "");

                String sql = "INSERT INTO person (UserName, Password) VALUES(?, ?)";

                PreparedStatement statement = conn.prepareStatement(sql);

                statement.setString(1, account.getUserName());
                statement.setString(2, account.getPassword());

                statement.executeUpdate();

                conn.close();
            } catch (SQLException sqlex) {

                System.out.println(sqlex);
            }

        }
    }

    public Account read(int personID) {

        Account account = null;

        if (personID != 0) {

            if (connection.openConnection()) {

                ResultSet resultSet = connection.executeSQLStatement("SELECT * FROM account WHERE person_ID = " + personID + ";");

                if (resultSet != null) {

                    try {
                        account = new Account(resultSet.getInt("person_ID"), resultSet.getString("UserName"), resultSet.getString("PassWord"));
                    } catch (SQLException sql) {
                        System.out.println(sql);

                        account = null;
                    }
                }
            }
            connection.closeConnection();
        }
        return account;
    }

    public void CreateBankAccount(BankAccount bankAccount) {

        Connection conn;

        if (bankAccount != null) {

            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank?serverTimezone=UTC", "root", "");

                String sql = "INSERT INTO bankaccount (Amount, Description, account_ID) VALUES(?, ?, ?)";

                PreparedStatement statement = conn.prepareStatement(sql);

                statement.setDouble(1, bankAccount.getAmount());
                statement.setString(2, bankAccount.getDescription());
                statement.setInt(3, bankAccount.getAccountID());

                statement.executeUpdate();

                conn.close();
            } catch (SQLException sqlex) {
                System.out.println(sqlex);
            }

        }
    }

    public List<BankAccount> readAll(int accountID) {

        ArrayList<BankAccount> bankAccounts = new ArrayList<>();

        if (accountID != 0) {

            if (connection.openConnection()) {

                ResultSet resultSet = connection.executeSQLStatement("SELECT * FROM bankaccount WHERE account_ID = " + accountID + ";");

                if (resultSet != null) {

                    try {
                        while (resultSet.next()) {
                            BankAccount bankAccount = new BankAccount(resultSet.getDouble("Amount"), resultSet.getString("Description"), resultSet.getInt("account_ID"));

                            bankAccounts.add(bankAccount);
                        }
                    } catch (SQLException sql) {
                        System.out.println(sql);
                        bankAccounts = null;
                    }
                }
            }
            connection.closeConnection();
        }
        return bankAccounts;
    }

    public void Delete(BankAccount bankAccount) {

        if (bankAccount != null) {

            if(connection.openConnection()){

                try{
                    connection.executeSqlDmlStatement("DELETE FROM bankaccount WHERE Description = " + bankAccount.getDescription() + "AND account_ID = " + bankAccount.getAccountID() + ";");
                }
                catch (Exception ex){
                    System.out.println(ex);
                }
            }
        connection.closeConnection();
        }
    }

    public void Deposit(BankAccount bankAccount, double amountToDeposit){

        Connection conn;

        double amountDeposit;
        double amountWithDraw;
        if(bankAccount != null && amountToDeposit != 0){

            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank?serverTimezone=UTC", "root", "");

                amountDeposit = bankAccount.getAmount() - amountToDeposit;

                String query1 = "UPDATE bankaccount SET Amount=? WHERE Description = " + bankAccount.getDescription() + "AND account_ID = " + bankAccount.getDescription() + ";" ;

                PreparedStatement statement1 = conn.prepareStatement(query1);
                statement1.setDouble(2, amountDeposit);

                statement1.executeUpdate();

            }
            catch (SQLException sqlex) {
                System.out.println(sqlex);
            }
        }
    }


    public void WithDraw(BankAccount bankAccount, double amount) {

        Account account = null;
        Person person = null;
        double amountToWithDraw;
        double currency = 100;
        Connection conn;

        if(bankAccount != null && amount != 0){

            ResultSet resultSet = connection.executeSQLStatement("SELECT * FROM account WHERE ID = " + bankAccount.getAccountID() + ";");

            if (resultSet != null) {

                try {
                    account = new Account(resultSet.getInt("person_ID"), resultSet.getString("UserName"), resultSet.getString("PassWord"));
                } catch (SQLException sql) {
                    System.out.println(sql);

                }

            }

            ResultSet resultSet1 = connection.executeSQLStatement("SELECT * FROM person WHERE ID = " + account.getpersonID() + ";");

            if (resultSet != null) {

                try {
                    person = new Person(resultSet.getString("FirstName"), resultSet.getString("LastName"), resultSet.getString("AccountNumber"), resultSet.getString("Email"), resultSet.getDate(""), resultSet.getString("Language"));
                } catch (SQLException sql) {
                    System.out.println(sql);

                }
            }

            ResultSet resultSet2 = connection.executeSQLStatement("SELECT Value FROM currency WHERE Language = " + person.getLanguage() + ";");

            if(resultSet2 != null){
                try{
                    if(resultSet2.getInt(2) > 0){
                        currency += resultSet2.getInt(2);
                    }
                    else{
                        currency -= resultSet2.getInt(2);
                    }
                }
                catch (SQLException sqlex){
                    System.out.println(sqlex);
                }
            }

            try{
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank?serverTimezone=UTC", "root", "");

                amountToWithDraw = amount / 100 * currency;

                String query = "UPDATE bankaccount SET amount=? WHERE Description = " + bankAccount.getDescription() + "AND account_ID = " + bankAccount.getDescription() + ";" ;

                PreparedStatement statement1 = conn.prepareStatement(query);
                statement1.setDouble(2, amountToWithDraw);

                statement1.executeUpdate();
            }
            catch (SQLException ex){
                System.out.println(ex);
            }
        }
    }

}
