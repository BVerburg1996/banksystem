package interfaces;

import accountManagment.Account;
import accountManagment.Person;

public interface IPerson {

     void CreateAccount(Person person, Account account);

     Person Read(String string);

}
