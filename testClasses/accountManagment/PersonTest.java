package accountManagment;

import database.PersonDAO;
import interfaces.IPerson;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    Person person = new Person("Iemand", "Iemand", "ab25258", "diederikn", "Nederlands");

    Account account = new Account("UserName", "Password");

    PersonDAO personDAO = new PersonDAO();

    @Test
    public void testCreateRead() {

        Person p = new Person();

        person.CreateAccount(person, account);

        p = person.Read(person.getAccountNumber());

        assertNotNull(p);

        personDAO.Delete(person);

        p = person.Read(person.getAccountNumber());

        assertNull(p);
    }
}