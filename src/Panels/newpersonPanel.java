package Panels;

import accountManagment.Account;
import accountManagment.Person;

import javax.swing.*;
import java.awt.event.*;

public class newpersonPanel<headers> extends JFrame {

   public static void main(String[] args) {
       newpersonPanel frameTabel = new newpersonPanel();
   }

    //Set all elements to the new person panel
    JLabel title = new JLabel("Nieuw account aanmaken");
    JLabel preUsername = new JLabel("Gebruikersnaam");
    JLabel prePassword = new JLabel("Wachtwoord");
    JLabel preName = new JLabel("Voornaam");
    JLabel preSurname = new JLabel("Achternaam");
    JLabel preAccountnumber = new JLabel("Bankrekening nummer");
    JLabel preEmail = new JLabel("Email");
    JLabel preLanguage = new JLabel("Land");
    JTextField username = new JTextField();
    JPasswordField password = new JPasswordField();
    JTextField name = new JTextField();
    JTextField surname = new JTextField();
    JTextField accountNumber = new JTextField();
    JTextField email = new JTextField();
    JButton submitnewperson = new JButton("Verzenden");
    JButton backToLogin = new JButton("Terug");
    JPanel panel = new JPanel();

    //Dropdown of availible languages
    String[] Languages = {"NL", "USA", "GB", "HUN", "JPN", "MEX", "TSJ", "ZWE", "CHI", "MAR" };
    JComboBox language = new JComboBox(Languages);

    //Set up the new person panel
    newpersonPanel(){
        super("B$nk");
        setSize(500, 500);
        setLocation(700,300);
        panel.setLayout (null);

        //Give all the elements their place
        title.setBounds(125,10,250,60);
        preUsername.setBounds(112,60,250,20);
        username.setBounds(112, 80, 250, 25);
        prePassword.setBounds(112,100, 250, 25);
        password.setBounds(112,120, 250, 25);
        preName.setBounds(112,140, 250, 25);
        name.setBounds(112,160, 250, 25);
        preSurname.setBounds(112,180, 250, 25);
        surname.setBounds(112,200, 250, 25);
        preAccountnumber.setBounds(112,220, 250, 25);
        accountNumber.setBounds(112,240, 250, 25);
        preEmail.setBounds(112,260, 250, 25);
        email.setBounds(112,280, 250, 25);
        preLanguage.setBounds(112,300, 250, 25);
        language.setBounds(112,320, 250, 25);
        submitnewperson.setBounds(50,400, 150, 25);
        backToLogin.setBounds(300,400, 150, 25);

        //.. And add them all to the panel
        panel.add(title);
        panel.add(preUsername);
        panel.add(username);
        panel.add(prePassword);
        panel.add(password);
        panel.add(preName);
        panel.add(name);
        panel.add(preSurname);
        panel.add(surname);
        panel.add(preEmail);
        panel.add(email);
        panel.add(preAccountnumber);
        panel.add(accountNumber);
        panel.add(preLanguage);
        panel.add(language);
        panel.add(submitnewperson);
        panel.add(backToLogin);
        title.setFont(overviewPanel.f);

        //Set the panel visible and initialise all event handlers
        getContentPane().add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setBackToMain();
        setNewPerson();
    }

    //event handler submit button
    public void setNewPerson() {
        submitnewperson.addActionListener(e -> {
            //Set all the values necessary for adding the new person and account
            String uname = username.getText();
            String pwrd = password.getText();
            String firstname = name.getText();
            String lastname = surname.getText();
            String accountnumber = accountNumber.getText();
            String mail = email.getText();
            String chosenLanguage = (String) language.getSelectedItem();

            //Check if all fields are filled and email is valid
            if(!uname.equals("") && !pwrd.equals("") && !firstname.equals("") && !lastname.equals("") && !accountnumber.equals("") && !mail.equals("") && mail.contains("@")) {
                //Create the new person
                Person person = new Person(firstname, lastname, accountnumber, mail, chosenLanguage);

                //Create the new account
                Account account = new Account(uname, pwrd);

                //...And submit them to the databaseAccessObjects
                person.CreateAccount(person, account);

                //Show confirmation and redirect to login panel
                JOptionPane.showMessageDialog(null, "Uw account is aangemaakt!");
                loginPanel loginPanel = new loginPanel();
                loginPanel.setVisible(true);
                dispose();
            }
            //Error and reset panel
            else {
                JOptionPane.showMessageDialog(null, "U heeft niet alle velden correct ingevuld");
                username.setText("");
                password.setText("");
                name.setText("");
                surname.setText("");
                email.setText("");
                accountNumber.setText("");
                username.requestFocus();
            }
        });
    }

    //Event handler backbutton (to login page)
    public void setBackToMain(){
        backToLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                loginPanel loginPanel = new loginPanel();
                loginPanel.setVisible(true);
                dispose();
            }
        });
    }
}
