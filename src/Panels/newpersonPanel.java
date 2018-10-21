package Panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class newpersonPanel<headers> extends JFrame {

    private Font f = new Font("Arial", Font.BOLD,18);

    public static void main(String[] args) {
        newpersonPanel frameTabel = new newpersonPanel();
    }

    JLabel title = new JLabel("Nieuw account aanmaken");
    JTextField username = new JTextField("Gebruikersnaam");
    JPasswordField password = new JPasswordField("Wachtwoord");
    JTextField name = new JTextField("Voornaam");
    JTextField surname = new JTextField("Achternaam");
    JTextField email = new JTextField("E-mail");
    JTextField bday = new JTextField("Geboortedatum");
    JButton submitnewperson = new JButton("Verzenden");
    JButton backToLogin = new JButton("Terug");
    JPanel panel = new JPanel();


    newpersonPanel(){
        super("B$nk");
        setSize(500, 500);
        setLocation(700,300);
        panel.setLayout (null);

        title.setBounds(125,10,250,60);
        username.setBounds(112, 70, 250, 25);
        password.setBounds(112,110, 250, 25);
        name.setBounds(112,150, 250, 25);
        surname.setBounds(112,190, 250, 25);
        email.setBounds(112,230, 250, 25);
        bday.setBounds(112,270, 250, 25);
        submitnewperson.setBounds(50,400, 150, 25);
        backToLogin.setBounds(300,400, 150, 25);

        panel.add(title);
        panel.add(username);
        panel.add(password);
        panel.add(name);
        panel.add(surname);
        panel.add(email);
        panel.add(bday);
        panel.add(submitnewperson);
        panel.add(backToLogin);
        title.setFont(f);

        getContentPane().add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setBackToMain();
    }
    public void setBackToMain(){
        backToLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                loginPanel loginPanel =new loginPanel();
                loginPanel.setVisible(true);
                dispose();
            }
        });
    }
}