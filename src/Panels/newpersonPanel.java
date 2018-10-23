package Panels;

import accountManagment.Account;
import accountManagment.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class newpersonPanel<headers> extends JFrame {

    private Font f = new Font("Arial", Font.BOLD,18);

    public static void main(String[] args) {
        newpersonPanel frameTabel = new newpersonPanel();
    }

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

    String[] Languages = {"NL", "USA", "GB", "HUN", "JPN", "MEX", "TSJ", "ZWE", "CHI", "MAR" };

    JComboBox language = new JComboBox(Languages);

    newpersonPanel(){
        super("B$nk");
        setSize(500, 500);
        setLocation(700,300);
        panel.setLayout (null);

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
        title.setFont(f);

        getContentPane().add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setBackToMain();
        setNewPerson();
    }
    public void setNewPerson() {
        submitnewperson.addActionListener(e -> {
            String uname = username.getText();
            String pwrd = password.getText();
            String firstname = name.getText();
            String lastname = surname.getText();
            String accountnumber = accountNumber.getText();
            String mail = email.getText();
            String chosenLanguage = (String) language.getSelectedItem();

            Person person = new Person(firstname, lastname, accountnumber, mail, chosenLanguage);
            Account account = new Account(uname, pwrd);

            person.CreateAccount(person, account);

            JOptionPane.showMessageDialog(null,"Uw account is aangemaakt!");

            loginPanel loginPanel =new loginPanel();
            loginPanel.setVisible(true);
            dispose();
        });
    }

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
