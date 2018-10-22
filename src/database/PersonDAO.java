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

                String query = "INSERT INTO person (FirstName, LastName, AccountNumber, Email, BirthDate, Language) VALUES(?, ?, ?, ?, ?, ?)";

                PreparedStatement statement = conn.prepareStatement(query);

                statement.setString(1, person.getFirstName());
                statement.setString(2, person.getLastName());
                statement.setString(3, person.getAccountNumber());
                statement.setString(4, person.getEmail());
                statement.setTimestamp(5, null);
                statement.setString(6, person.getLanguage());

                statement.executeUpdate();


                String sql2 = "SELECT ID FROM person WHERE AccountNumber = " + person.getAccountNumber() + ";";
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

                conn.close();
            } catch (SQLException sqlex) {
                System.out.println(sqlex);
            }

        }

    }

    public Person read(String accountNumber) {

        Person person = null;

        try{ ;
            Connection mycon = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank?serverTimezone=UTC", "root", "");

            String sql = "SELECT * FROM Person WHERE AccountNumber = " + accountNumber + ";";

            Statement statement = mycon.createStatement();
            ResultSet result = statement.executeQuery(sql);

            result.next();
            person = new Person(result.getString("FirstName"), result.getString("LastName"), result.getString("AccountNumber"), result.getString("Email"), result.getDate("BirthDate"), result.getString("Language"));
            mycon.close();
        }
        catch (SQLException sql){
            System.out.println(sql);
        }

        return person;
    }


}
