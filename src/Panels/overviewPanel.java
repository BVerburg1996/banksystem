package Panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class overviewPanel<headers> extends JFrame {

    private Font f = new Font("Arial", Font.BOLD,18);

    public static void main(String[] args) {
        overviewPanel frameTabel = new overviewPanel();
    }
    JLabel welcome = new JLabel("Welkom <naam>");
    JLabel overview = new JLabel("Overzicht bankaccounts: ");
    JButton newAccount = new JButton("Nieuw bankaccount");
    JButton newTransaction = new JButton("Transactie");
    JButton personalData = new JButton("Persoonsgegevens");

    JPanel panel = new JPanel();

    overviewPanel(){
        super("B$nk");
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
        personalData.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                dataPanel dataPanel =new dataPanel();
                dataPanel.setVisible(true);
                dispose();
            }
        });
    }
}