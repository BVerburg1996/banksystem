package accountManagment;

import interfaces.IPerson;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PersonTest implements IPerson {

    Person person = new Person("Iemand", "Iemand", "ab25258", "diederikn", "Nederlands");

    Account account = new Account("UserName", "Password");
    @Test
    @Override
    public void CreateAccount(Person person, Account account) {


    }

    @Test
    @Override
    public Person Read(String string) {
        return null;
    }
}