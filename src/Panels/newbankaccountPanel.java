package Panels;

import accountManagment.Account;
import accountManagment.BankAccount;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class newbankaccountPanel<headers> extends JFrame {

    private Font f = new Font("Arial", Font.BOLD,18);

    public static void main(String[] args) {
        newbankaccountPanel frameTabel = new newbankaccountPanel();
    }

    JLabel title = new JLabel("Nieuw bankaccount");
    JLabel preStartAmount = new JLabel("Saldo als start");
    JLabel preDescription = new JLabel("Beschrijving");
    JLabel confirmation = new JLabel("Uw bankrekening ter bevestiging");
    JTextField startAmountInput = new JTextField();
    JTextField descriptionInput = new JTextField();
    JTextField bankAccountNumber = new JTextField();
    JButton submitnewbankaccount = new JButton("Verzenden");
    JButton backToMain = new JButton("Terug");
    JPanel panel = new JPanel();


    newbankaccountPanel(){
        super("B$nk");
        setSize(500,500);
        setLocation(700,300);
        panel.setLayout (null);

        title.setBounds(125,10,250,60);
        preStartAmount.setBounds(100, 70, 250, 25);
        startAmountInput.setBounds(100, 90, 250, 25);
        preDescription.setBounds(100,120, 250, 25);
        descriptionInput.setBounds(100,140, 250, 25);
        bankAccountNumber.setBounds(100,190, 250, 25);
        confirmation.setBounds(100,170, 250, 25);
        submitnewbankaccount.setBounds(140,230, 150, 25);
        backToMain.setBounds(140,270, 150, 25);

        panel.add(title);
        panel.add(preStartAmount);
        panel.add(startAmountInput);
        panel.add(preDescription);
        panel.add(descriptionInput);
        panel.add(submitnewbankaccount);
        panel.add(bankAccountNumber);
        panel.add(confirmation);
        panel.add(backToMain);
        title.setFont(f);

        getContentPane().add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setNewBankAccount();
        setBackToMain();
    }

    public void setNewBankAccount() {
        submitnewbankaccount.addActionListener(e -> {
            double amount = Double.parseDouble(startAmountInput.getText());
            String description = descriptionInput.getText();
            BankAccount bankAccount = new BankAccount(amount, description);
            Account account = new Account();
            account.CreateBankAccount(bankAccount, bankAccountNumber.getText());
            JOptionPane.showMessageDialog(null,"Uw nieuwe bankaccount " + description + " is aangemaakt met " + amount + " als startbedrag");
            overviewPanel overviewPanel = new overviewPanel();
            overviewPanel.setVisible(true);
            dispose();
        });
    }

    public void setBackToMain(){
        backToMain.addActionListener(ae -> {
            overviewPanel overviewPanel = new overviewPanel();
            overviewPanel.setVisible(true);
            dispose();
        });
    }
}