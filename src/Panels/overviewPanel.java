package Panels;

import com.mysql.cj.protocol.Resultset;

import javax.swing.*;
import javax.xml.transform.Result;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import static Panels.loginPanel.txuser;

public class overviewPanel<headers> extends JFrame {

    private Font f = new Font("Arial", Font.BOLD,18);
    static String setfirstname;
    static String setsurname;
    static String setemail;
    static String setacccountNumber;
    static int personID;

    public static void main(String[] args) {
        overviewPanel frameTabel = new overviewPanel();
    }

    Connection mycon;

    {
        try {
            mycon = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank?serverTimezone=UTC", "root", "");
            String sql = ("SELECT * FROM person WHERE UserName=`"+txuser.getText() + "`");
            Statement statement = mycon.createStatement();
            ResultSet login = statement.executeQuery(sql);
            login.next();

        } catch (SQLException e) {
        }
    }

    JLabel welcome = new JLabel("Welkom " + txuser.getText());
    JLabel overview = new JLabel("Overzicht bankaccounts: ");
    JButton newAccount = new JButton("Nieuw bankaccount");
    JButton newTransaction = new JButton("Transactie");
    JButton personalData = new JButton("Persoonsgegevens");

    JPanel panel = new JPanel();

    overviewPanel(){
        super("Welcome");
        setSize(500,500);
        setLocation(700, 300);
        panel.setLayout (null);

        welcome.setBounds(170,10,160,60);
        overview.setBounds(125, 30, 250, 60);
        personalData.setBounds(5, 400, 150, 25);
        newAccount.setBounds(165,400, 150, 25);
        newTransaction.setBounds(325,400, 150, 25);


        //@TODO
        //foreach (account : accounts) > maak een regel

        panel.add(welcome);
        panel.add(overview);
        panel.add(newAccount);
        panel.add(newTransaction);
        panel.add(personalData);
        welcome.setFont(f);
        overview.setFont(f);

        getContentPane().add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setNewTransaction();
        setNewAccount();
        setPersonalData();
    }

    public void setNewTransaction(){
        newTransaction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                transactionPanel transaction =new transactionPanel();
                transaction.setVisible(true);
                dispose();
            }
        });
    }

    public void setNewAccount(){
        newAccount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                newbankaccountPanel newaccount =new newbankaccountPanel();
                newaccount.setVisible(true);
                dispose();
            }
        });
    }

    public void setPersonalData(){
        personalData.addActionListener(ae -> {

                try {
                    Connection mycon = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank?serverTimezone=UTC", "root", "");
                    String sql = ("SELECT * FROM account WHERE UserName='" + txuser.getText() + "'");
                    Statement statement = mycon.createStatement();
                    ResultSet data = statement.executeQuery(sql);
                    data.next();

                    personID = data.getInt("person_ID");

                    dataPanel dataPanel = new dataPanel();
                    dataPanel.setVisible(true);

                    String sql2 = ("SELECT * FROM person WHERE ID='" + personID + "'");
                    Statement statement2 = mycon.createStatement();
                    ResultSet personinfo = statement2.executeQuery(sql2);
                    personinfo.next();
                    setfirstname = personinfo.getString("FirstName");
                    setsurname = personinfo.getString("LastName");
                    setacccountNumber = personinfo.getString("AccountNumber");
                    setemail = personinfo.getString("Email");
                    dispose();
                }
                catch (SQLException e) {

                }
        });
    }
}