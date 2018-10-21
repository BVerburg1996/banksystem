package Panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class newbankaccountPanel<headers> extends JFrame {

    private Font f = new Font("Arial", Font.BOLD,18);

    public static void main(String[] args) {
        newbankaccountPanel frameTabel = new newbankaccountPanel();
    }

    JLabel title = new JLabel("Nieuw bankaccount");
    JTextField startAmountInput = new JTextField("Hoeveelheid start");
    JTextField descriptionInput = new JTextField("Beschrijving");
    JButton submitnewaccount = new JButton("Verzenden");
    JButton backToMain = new JButton("Terug");
    JPanel panel = new JPanel();


    newbankaccountPanel(){
        super("B$nk");
        setSize(500,500);
        setLocation(700,300);
        panel.setLayout (null);

        title.setBounds(125,10,250,60);
        startAmountInput.setBounds(100, 80, 250, 25);
        descriptionInput.setBounds(100,130, 250, 25);
        submitnewaccount.setBounds(140,170, 150, 25);
        backToMain.setBounds(140,220, 150, 25);

        panel.add(title);
        panel.add(startAmountInput);
        panel.add(descriptionInput);
        panel.add(submitnewaccount);
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