package Panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class dataPanel<headers> extends JFrame {
    String getUname = loginPanel.txuser.getText();

    private Font f = new Font("Arial", Font.BOLD,18);

    public static void main(String[] args) {
        dataPanel frameTabel = new dataPanel();
    }


    JLabel title = new JLabel("Persoonlijke gegevens");

    JLabel username = new JLabel("Gebruikersnaam: " + overviewPanel.personID);
    JLabel name = new JLabel("Voornaam: " + overviewPanel.setfirstname);
    JLabel surname = new JLabel("Achternaam: " + overviewPanel.setsurname);
    JLabel email = new JLabel("E-mail adres: " + overviewPanel.setemail);

    JButton backToMain = new JButton("Terug");
    JPanel panel = new JPanel();


    dataPanel(){
        super("B$nk");
        setSize(500,500);
        setLocation(700,300);
        panel.setLayout (null);

        title.setBounds(125,10,250,60);
        username.setBounds(100, 80, 250, 25);
        name.setBounds(100,120, 250, 25);
        surname.setBounds(100,150, 150, 25);
        email.setBounds(100,180, 150, 25);
        backToMain.setBounds(140,240, 150, 25);

        panel.add(title);
        panel.add(username);
        panel.add(name);
        panel.add(surname);
        panel.add(email);
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