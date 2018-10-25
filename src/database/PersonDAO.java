package database;

import accountManagment.Account;
import accountManagment.Person;

import java.sql.*;

public class PersonDAO {

    DatabaseConnection connection = new DatabaseConnection();

    public void CreateAccount(Person person, Account account) {

        Connection conn;

        if (person != null && account != null) {
            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank?serverTimezone=UTC", "root", "");

                String query = "INSERT INTO person (FirstName, LastName, AccountNumber, Email, Language) VALUES(?, ?, ?, ?, ?)";

                PreparedStatement statement = conn.prepareStatement(query);

                statement.setString(1, person.getFirstName());
                statement.setString(2, person.getLastName());
                statement.setString(3, person.getAccountNumber());
                statement.setString(4, person.getEmail());
                statement.setString(5, person.getLanguage());

                statement.executeUpdate();


                String sql2 = "SELECT ID FROM person WHERE AccountNumber = '" + person.getAccountNumber() + "';";
                Statement statement4 = conn.createStatement();
                ResultSet result = statement4.executeQuery(sql2);
                result.next();
                int number = result.getInt(1);

                String query2 = "INSERT INTO account (UserName, Password, person_ID) VALUES(?, ?, ?)";

                PreparedStatement statement2 = conn.prepareStatement(query2);

                statement2.setString(1, account.getUserName());
                statement2.setString(2, account.getPassword());
                statement2.setInt(3, number);

                statement2.executeUpdate();

                String query3 = "SELECT ID FROM account WHERE person_ID = " + number + ";";
                Statement statement1 = conn.createStatement();
                ResultSet res = statement1.executeQuery(query3);
                res.next();
                int accountNumber = res.getInt(1);

                String query4 = "INSERT INTO bankaccount (Amount, Description, account_ID) VALUES(?, ?, ?)";
                PreparedStatement pr = conn.prepareStatement(query4);
                pr.setDouble(1, 100);
                pr.setString(2, "main");
                pr.setInt(3, accountNumber);

                pr.executeUpdate();

                conn.close();
            } catch (SQLException sqlex) {
                System.out.println(sqlex);
            }

        }

    }

    public Person read(String accountNumber) {

        Person person = null;

        try {
            Connection mycon = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank?serverTimezone=UTC", "root", "");

            String sql = "SELECT * FROM Person WHERE AccountNumber = '" + accountNumber + "';";

            Statement statement = mycon.createStatement();
            ResultSet result = statement.executeQuery(sql);

            result.next();
            person = new Person(result.getInt("ID"), result.getString("FirstName"), result.getString("LastName"), result.getString("AccountNumber"), result.getString("Email"), result.getString("Language"));
            mycon.close();
        } catch (SQLException sql) {
            System.out.println(sql);
        }

        return person;
    }

    public void Delete(Person person) {

        Statement statement, statement2, statement3;
        ResultSet result;

        try {
            Connection mycon = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank?serverTimezone=UTC", "root", "");

            String sql = "SELECT ID FROM person WHERE AccountNumber = '" + person.getAccountNumber() + "';";
            statement = mycon.createStatement();
            result = statement.executeQuery(sql);
            result.next();

            int personID = result.getInt("ID");

            String sql2 = "DELETE FROM account WHERE person_ID = " + personID + ";";
            statement2 = mycon.createStatement();
            statement2.executeUpdate(sql2);

            statement3= mycon.createStatement();
            String sql3 = "DELETE FROM person WHERE AccountNumber = '" + person.getAccountNumber() + "';";
            statement3.executeUpdate(sql3);
        }

        catch (SQLException sqlex) {
            System.out.println(sqlex);
        }
    }


}
