package Panels;

import accountManagment.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class transactionPanel<headers> extends JFrame {

    private Font f = new Font("Arial", Font.BOLD,18);

    public static void main(String[] args) {
        transactionPanel frameTabel = new transactionPanel();
    }

    JLabel title = new JLabel("Nieuwe transactie");
    JLabel preAmount = new JLabel("Hoeveel wilt u overmaken?");
    JLabel preToInput = new JLabel("Naar Rekeningnummer");
    JTextField amountInput = new JTextField();
    JTextField toInput = new JTextField();
    JButton submitTransaction = new JButton("Verzenden");
    JButton backToMain = new JButton("Terug");
    JPanel panel = new JPanel();


    transactionPanel(){
        super("B$nk");
        setSize(500,500);
        setLocation(700,300);
        panel.setLayout (null);

        title.setBounds(125,10,250,60);
        preAmount.setBounds(100, 70, 250, 25);
        amountInput.setBounds(100, 90, 250, 25);
        preToInput.setBounds(100,120, 250, 25);
        toInput.setBounds(100,140, 250, 25);
        submitTransaction.setBounds(140,170, 150, 25);
        backToMain.setBounds(140,220, 150, 25);

        panel.add(title);
        panel.add(preAmount);
        panel.add(amountInput);
        panel.add(preToInput);
        panel.add(toInput);
        panel.add(submitTransaction);
        panel.add(backToMain);
        title.setFont(f);

        getContentPane().add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setTransaction();
        setBackToMain();
    }

    public void setTransaction() {
        submitTransaction.addActionListener(e -> {
            String amount = amountInput.getText();
            String toWho = toInput.getText();

            Account Account = new Account ();

            JOptionPane.showMessageDialog(null,"Uw transactie van " + amount + " naar " + toWho + " is verzonden!");
            overviewPanel overviewPanel =new overviewPanel();
            overviewPanel.setVisible(true);
            dispose();
        });
    }

    public void setBackToMain(){
        backToMain.addActionListener(ae -> {
            overviewPanel overviewPanel =new overviewPanel();
            overviewPanel.setVisible(true);
            dispose();
        });
    }

}