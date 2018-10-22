package database;

import accountManagment.Account;
import accountManagment.BankAccount;
import accountManagment.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {

    DatabaseConnection connection = new DatabaseConnection();

//    public Account read(int personID) {
//
//        Account account = null;
//
//        if (personID != 0) {
//
//            if (connection.openConnection()) {
//
//                ResultSet resultSet = connection.executeSQLStatement("SELECT * FROM account WHERE person_ID = " + personID + ";");
//
//                if (resultSet != null) {
//
//                    try {
//                        account = new Account(resultSet.getString("UserName"), resultSet.getString("PassWord"));
//                    } catch (SQLException sql) {
//                        System.out.println(sql);
//
//                        account = null;
//                    }
//                }
//            }
//            connection.closeConnection();
//        }
//        return account;
//    }


    public void CreateBankAccount(BankAccount bankAccount, String bankAccountNumber) {

        Connection conn;

        if (bankAccount != null) {

            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank?serverTimezone=UTC", "root", "");

                String query1 = "SELECT ID FROM person WHERE AccountNumber = " + bankAccountNumber + ";";
                Statement statement1 = conn.createStatement();
                ResultSet result = statement1.executeQuery(query1);
                result.next();
                int number = result.getInt(1);

                String query2 = "SELECT ID FROM account WHERE person_ID = " + number + ";";
                Statement statement2 = conn.createStatement();
                ResultSet result2 = statement2.executeQuery(query2);
                result2.next();
                int accountNumber = result2.getInt(1);

                String sql = "INSERT INTO bankaccount (Amount, Description, account_ID) VALUES(?, ?, ?)";

                PreparedStatement statement = conn.prepareStatement(sql);

                statement.setDouble(1, bankAccount.getAmount());
                statement.setString(2, bankAccount.getDescription());
                statement.setInt(3, accountNumber);

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
                            BankAccount bankAccount = new BankAccount(resultSet.getDouble("Amount"), resultSet.getString("Description"));

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

            if (connection.openConnection()) {

                try {
                    connection.executeSqlDmlStatement("DELETE FROM bankaccount WHERE Description = '" + bankAccount.getDescription() + "' AND Amount = " + bankAccount.getAmount() + ";");
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
            connection.closeConnection();
        }
    }

    public void Deposit(BankAccount bankAccount, double amountToDeposit, int accountID) {

        Connection conn;

        double amountDeposit;
        double amountWithDraw;
        if (bankAccount != null && amountToDeposit != 0) {

            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank?serverTimezone=UTC", "root", "");

                amountDeposit = bankAccount.getAmount() - amountToDeposit;

                String query1 = "UPDATE bankaccount SET Amount=? WHERE Description = '" + bankAccount.getDescription() + "' AND account_ID = " + accountID + ";";

                PreparedStatement statement1 = conn.prepareStatement(query1);
                statement1.setDouble(1, amountDeposit);

                statement1.executeUpdate();

            } catch (SQLException sqlex) {
                System.out.println(sqlex);
            }
        }
    }


    public void WithDraw(BankAccount bankAccount, double amount, int accountID) {

        Person person = null;
        double amountToWithDraw;
        double currency = 100;
        Connection conn;

        if (bankAccount != null && amount != 0) {

            try {

                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank?serverTimezone=UTC", "root", "");

                String query1 = "SELECT person_ID FROM account WHERE ID = " + accountID + ";";
                Statement statement = conn.createStatement();
                ResultSet result = statement.executeQuery(query1);
                result.next();
                int personid = result.getInt(1);

                String query2 = "SELECT Language FROM person WHERE ID = " + personid + ";";
                Statement statement2 = conn.createStatement();
                ResultSet result2 = statement2.executeQuery(query2);
                result2.next();
                String language = result2.getString(1);

                String query3 = "SELECT Value FROM currency WHERE Language = '" + language + "';";
                Statement statement3 = conn.createStatement();
                ResultSet result3 = statement3.executeQuery(query3);
                result3.next();
                int value = result3.getInt(1);


                if (value != 0) {

                    if (value > 0) {
                        currency += value;
                    } else {
                        currency -= value;
                    }


                }

                amountToWithDraw = amount / 100 * currency;

                String query = "UPDATE bankaccount SET amount=? WHERE Description = " + bankAccount.getDescription() + "AND account_ID = " + bankAccount.getDescription() + ";";

                PreparedStatement statement1 = conn.prepareStatement(query);
                statement1.setDouble(2, amountToWithDraw);

                statement1.executeUpdate();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
    }

}
