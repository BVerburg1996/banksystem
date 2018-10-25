package Panels;

import accountManagment.*;
import database.AccountDAO;
import database.PersonDAO;

import javax.swing.*;

public class transactionPanel<headers> extends JFrame {

    //Set the toWho sting, filled on line 61
    String toWho;

    public static void main(String[] args) {
        transactionPanel frameTabel = new transactionPanel();
    }

    //Set all the elements on the panel
    JLabel title = new JLabel("Nieuwe transactie");
    JLabel preAmount = new JLabel("Hoeveel wilt u overmaken?");
    JLabel preToInput = new JLabel("Naar Rekeningnummer");
    JTextField amountInput = new JTextField();
    JTextField toInput = new JTextField();
    JButton submitTransaction = new JButton("Verzenden");
    JButton backToMain = new JButton("Terug");
    JPanel panel = new JPanel();

    //Initialise the transaction panel
    transactionPanel(){
        super("B$nk");
        setSize(500,500);
        setLocation(700,300);
        panel.setLayout (null);

        //Give all the elements their own place
        title.setBounds(125,10,250,60);
        preAmount.setBounds(100, 70, 250, 25);
        amountInput.setBounds(100, 90, 250, 25);
        preToInput.setBounds(100,120, 250, 25);
        toInput.setBounds(100,140, 250, 25);
        submitTransaction.setBounds(140,170, 150, 25);
        backToMain.setBounds(140,220, 150, 25);

        //... And add them to the panel
        panel.add(title);
        panel.add(preAmount);
        panel.add(amountInput);
        panel.add(preToInput);
        panel.add(toInput);
        panel.add(submitTransaction);
        panel.add(backToMain);
        title.setFont(overviewPanel.f);

        //Set panel to visible and initialise the event handlers
        getContentPane().add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setTransaction();
        setBackToMain();
    }

    //Event handler submit button
    public void setTransaction() {
        submitTransaction.addActionListener(e -> {

            //Set all the necessary values
            Double amount = Double.valueOf(amountInput.getText());
            toWho = toInput.getText();

            if(amount > 0.0 && amount != 0 && !toWho.equals("")) {
                //Create new personDAO
                PersonDAO personDAO = new PersonDAO();

                //Create new person, and set it to the person who will receive the money
                Person personTo = new Person();
                personTo = personDAO.read(toWho);

                //Create new person, and set it to the person who will submit the money
                Person personFrom = new Person();
                personFrom = personDAO.read(loginPanel.accountNumber);

                //Create new AccountDAO, and fire the createtransaction method
                AccountDAO accountDAO = new AccountDAO();
                accountDAO.CreateTransAction(personFrom, amount, personTo);

                //Show confirmation, and redirects back to overview panel
                JOptionPane.showMessageDialog(null, "Uw transactie van " + amount + " naar " + toWho + " is verzonden!");
                overviewPanel overviewPanel = new overviewPanel();
                overviewPanel.setVisible(true);
                dispose();
            }
            else    {
                JOptionPane.showMessageDialog(null, "U heeft niet alle velden correct ingevuld");
                amountInput.setText("");
                toInput.setText("");
                amountInput.requestFocus();
            }
        });
    }

    //Eventhandler backbutton
    public void setBackToMain(){
        backToMain.addActionListener(ae -> {
            overviewPanel overviewPanel =new overviewPanel();
            overviewPanel.setVisible(true);
            dispose();
        });
    }

}