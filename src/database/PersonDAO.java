package database;

import accountManagment.Person;

import java.sql.*;

public class PersonDAO {

    DatabaseConnection connection = new DatabaseConnection();

    public void CreatePerson(Person person) {

        Connection conn;

        if (person != null) {
            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank?serverTimezone=UTC", "root", "");

                String sql = "INSERT INTO person (FirstName, LastName, AccountNumber, Email, BirthDate, Language) VALUES(?, ?, ?, ?, ?, ?)";

                PreparedStatement statement = conn.prepareStatement(sql);

                statement.setString(1, person.getFirstName());
                statement.setString(2, person.getLastName());
                statement.setString(3, person.getAccountNumber());
                statement.setString(4, person.getEmail());
                statement.setDate(5, null);
                statement.setString(6, person.getLanguage());

                statement.executeUpdate();

            } catch (SQLException sqlex) {
                System.out.println(sqlex);
            }
        }

    }

    public Person read(String accountNumber) {

        Person person = null;

        if (accountNumber != null) {

            if (connection.openConnection()) {

                ResultSet resultSet = connection.executeSQLStatement("SELECT * FROM person WHERE AccountNumber = " + accountNumber + ";");

                if (resultSet != null) {

                    try {
                        person = new Person(resultSet.getString("FirstName"), resultSet.getString("LastName"), resultSet.getString("AccountNumber"), resultSet.getString("Email"), resultSet.getDate(""), resultSet.getString("Language"));
                    } catch (SQLException sql) {
                        System.out.println(sql);
                        person = null;
                    }
                }
            }

        }

        return person;
    }


}
