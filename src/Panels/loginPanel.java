package Panels;

import javax.swing.*;
import java.awt.event.*;

public class loginPanel extends JFrame {

    public static void main(String[] args) {
        loginPanel frameTabel = new loginPanel();
    }

    JButton login = new JButton("Login");
    JPanel panel = new JPanel();
    JLabel usrname = new JLabel("Gebruikersnaam: ");
    JLabel pswrd = new JLabel("Wachtwoord: ");
    JTextField txuser = new JTextField(15);
    JPasswordField pass = new JPasswordField(15);
    JButton newPerson = new JButton("Nieuw account");

    loginPanel(){
        super("Login B$nk");
        setSize(300,300);
        setLocation(500,280);
        panel.setLayout (null);

        usrname.setBounds(70, 20, 150, 20);
        txuser.setBounds(70,40,150,20);
        pswrd.setBounds(70, 65, 150, 20);
        pass.setBounds(70,85,150,20);
        login.setBounds(110,110,80,20);
        newPerson.setBounds(70,140,150,20);

        panel.add(login);
        panel.add(usrname);
        panel.add(txuser);
        panel.add(pswrd);
        panel.add(pass);
        panel.add(newPerson);

        getContentPane().add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        actionlogin();
        setNewPerson();
    }

    public void actionlogin(){
        login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String puname = txuser.getText();
                String ppaswd = pass.getText();
                if(puname.equals("test") && ppaswd.equals("12345")) {
                    overviewPanel regFace =new overviewPanel();
                    regFace.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null,"Verkeerd wachtwoord/gebruikersnaam");
                    txuser.setText("");
                    pass.setText("");
                    txuser.requestFocus();
                }

            }
        });
    }

    public void setNewPerson(){
        newPerson.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                newpersonPanel newpersonPanel =new newpersonPanel();
                newpersonPanel.setVisible(true);
                dispose();
            }
        });
    }
}

