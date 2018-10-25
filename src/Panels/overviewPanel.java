package Panels;

import accountManagment.*;
import database.PersonDAO;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

import static Panels.loginPanel.txuser;

public class overviewPanel<headers, accounts> extends JFrame {

    //Set global font of titles and subtitles
    static Font f = new Font("Arial", Font.BOLD, 18);

    public static void main(String[] args) {
        overviewPanel frameTabel = new overviewPanel();
    }

    Account account = new Account();

    //Creates all the text and buttons on the page
    JLabel welcome = new JLabel("Welkom " + txuser.getText());
    JLabel overview = new JLabel("Overzicht bankaccounts");
    JButton newAccount = new JButton("Nieuw bankaccount");
    JButton newTransaction = new JButton("Transactie");
    JButton newDeposit = new JButton("Storten");

    //Gives a list of all the bankaccounts of logged in user
    JList<Object> accList = new JList<>(account.ReadBankAccount(loginPanel.accountNumber).toArray());
    JPanel panel = new JPanel();

    //Initialise the overview panel
    overviewPanel() {
        super("Welcome");
        setSize(500, 500);
        setLocation(700, 300);
        panel.setLayout(null);

        //Set all the locations of the elements
        welcome.setBounds(170, 10, 160, 60);
        overview.setBounds(125, 30, 250, 60);
        accList.setBounds(50, 80, 400, 200);
        newAccount.setBounds(168, 400, 150, 25);
        newTransaction.setBounds(325, 400, 150, 25);
        newDeposit.setBounds(10, 400, 150, 25);

        //... And add them to the panel
        panel.add(welcome);
        panel.add(overview);
        panel.add(accList);
        panel.add(newAccount);
        panel.add(newTransaction);
        panel.add(newDeposit);

        //Set the font of title and subtitle
        welcome.setFont(f);
        overview.setFont(f);

        //Make it visible and initialise the event handlers
        getContentPane().add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setNewTransaction();
        setNewAccount();
        setNewDeposit();
    }

    //Goes to new transaction panel
    public void setNewTransaction() {
        newTransaction.addActionListener(ae -> {
            transactionPanel transaction = new transactionPanel();
            transaction.setVisible(true);
            dispose();
        });
    }

    //Goes to new deposit panel
    public void setNewDeposit() {
        newDeposit.addActionListener(ae -> {
            depositPanel deposit = new depositPanel();
            deposit.setVisible(true);
            dispose();
        });
    }

    //Goes to new bankaccount panel
    public void setNewAccount() {
        newAccount.addActionListener(ae -> {
            newbankaccountPanel newaccount = new newbankaccountPanel();
            newaccount.setVisible(true);
            dispose();
        });
    }

}