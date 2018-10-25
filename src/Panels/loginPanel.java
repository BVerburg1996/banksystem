package Panels;

import java.sql.*;
import javax.swing.*;

public class loginPanel extends JFrame {

    public static void main(String[] args) {
        loginPanel frameTabel = new loginPanel();
    }

    //Create data that can be used throughout the whole application for logged in user
    static int currentAccountID;
    static int currentPersonID;
    static String accountNumber;

    //Create all the login panel labels, buttons and textfields
    JButton login = new JButton("Login");
    JPanel panel = new JPanel();
    JLabel usrname = new JLabel("Gebruikersnaam: ");
    JLabel pswrd = new JLabel("Wachtwoord: ");
    static JTextField txuser = new JTextField(15);
    JPasswordField pass = new JPasswordField(15);
    JButton newPerson = new JButton("Nieuw account");

    //Construct the login panel
    loginPanel(){
        super("Login B$nk");
        setSize(300,220);
        setLocation(800,400);
        panel.setLayout (null);

        //Give all labels, buttons and textfields the location on the panel
        usrname.setBounds(70, 20, 150, 20);
        txuser.setBounds(70,40,150,20);
        pswrd.setBounds(70, 65, 150, 20);
        pass.setBounds(70,85,150,20);
        login.setBounds(110,110,80,20);
        newPerson.setBounds(70,140,150,20);

        //Add all to the login panel
        panel.add(login);
        panel.add(usrname);
        panel.add(txuser);
        panel.add(pswrd);
        panel.add(pass);
        panel.add(newPerson);

        //Set visible to true and set the even handlers
        getContentPane().add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        actionlogin();
        setNewPerson();
    }

    //event handler login button
    public void actionlogin(){
        login.addActionListener(ae -> {

            try {
                //Create connection
                Connection mycon = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank?serverTimezone=UTC", "root", "");

                //Select all from the account of the user who tries to log in
                String sql = ("SELECT * FROM account WHERE UserName='" + txuser.getText() + "'");
                Statement statement = mycon.createStatement();
                ResultSet login = statement.executeQuery(sql);
                login.next();

                //Set the variables initialised on line 14 and 15
                currentAccountID = login.getInt("ID");
                currentPersonID = login.getInt("person_ID");

                //Select all from the person who tries to log in
                String sql2 = ("SELECT * FROM person WHERE ID = " + currentPersonID);
                Statement statement2 = mycon.createStatement();
                ResultSet overview2 = statement2.executeQuery(sql2);
                overview2.next();

                accountNumber = overview2.getString("AccountNumber");

                //This is the password filled in in the textfield "password"
                String filledPassword = pass.getText();

                    //If the password matches, go to the overview panel and dispose the login panel
                    if (login.getString("Password").equals(filledPassword)) {
                        overviewPanel regFace = new overviewPanel();
                        regFace.setVisible(true);
                        dispose();
                    }

                    //Else the password doesn't match, display error, reset the values of the textfields and set focus
                    // on the username so the user can start typing again without re-clicking the textfield
                    else {
                        JOptionPane.showMessageDialog(null, "Uw wachtwoord is incorrect");
                        txuser.setText("");
                        pass.setText("");
                        txuser.requestFocus();
                    }
            }
            //If there is an SQL Exception, it means that the username given by the user is not in the databaseAccessObjects
            catch (SQLException e) {
                JOptionPane.showMessageDialog(null,"Uw gebruikersnaam is niet bij ons bekend");
                txuser.setText("");
                pass.setText("");
                txuser.requestFocus();
            }
        });
    }

    //Event handler of the "new person" button, opens the new person panel
    public void setNewPerson(){
        newPerson.addActionListener(ae -> {
            newpersonPanel newpersonPanel =new newpersonPanel();
            newpersonPanel.setVisible(true);
            dispose();
        });
    }
}

