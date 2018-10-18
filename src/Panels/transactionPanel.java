package Panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class transactionPanel<headers> extends JFrame {

    private Font f = new Font("Arial", Font.BOLD,18);

    public static void main(String[] args) {
        transactionPanel frameTabel = new transactionPanel();
    }

    JLabel title = new JLabel("Nieuwe transactie");
    JTextField amountInput = new JTextField("Hoeveelheid");
    JTextField toInput = new JTextField("Naar account");
    JButton submitTransaction = new JButton("Verzenden");
    JButton backToMain = new JButton("Terug");
    JPanel panel = new JPanel();


    transactionPanel(){
        super("B$nk");
        setSize(500,500);
        setLocation(0,0);
        panel.setLayout (null);

        title.setBounds(125,10,250,60);
        amountInput.setBounds(100, 80, 250, 25);
        toInput.setBounds(100,130, 250, 25);
        submitTransaction.setBounds(140,170, 150, 25);
        backToMain.setBounds(140,220, 150, 25);

        panel.add(title);
        panel.add(amountInput);
        panel.add(toInput);
        panel.add(submitTransaction);
        panel.add(backToMain);
        title.setFont(f);

        getContentPane().add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setBackToMain();
    }

    public void setBackToMain(){
        backToMain.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                overviewPanel overviewPanel =new overviewPanel();
                overviewPanel.setVisible(true);
                dispose();
            }
        });
    }

}