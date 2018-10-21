package accountManagment;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PersonTest {


    @Test
    public void create() {

        Person person = new Person("Diederik", "Nieuwenhuize", "011010", "wjdnieuw", null, "Dutch");

        person.Create(person);

        assertNotNull(person);
    }

    @Test
    public void read() {
    }

    @Test
    public void delete() {
    }

}