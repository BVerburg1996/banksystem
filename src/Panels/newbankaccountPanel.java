package Panels;

import accountManagment.Account;
import accountManagment.BankAccount;

import javax.swing.*;

public class newbankaccountPanel<headers> extends JFrame {

    public static void main(String[] args) {
        newbankaccountPanel frameTabel = new newbankaccountPanel();
    }

    //Creates all elements on the page
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

    //Design of the new bankaccount panel
    newbankaccountPanel(){
        super("B$nk");
        setSize(500,500);
        setLocation(700,300);
        panel.setLayout (null);

        //Give all elements their place on the panel
        title.setBounds(125,10,250,60);
        preStartAmount.setBounds(100, 70, 250, 25);
        startAmountInput.setBounds(100, 90, 250, 25);
        preDescription.setBounds(100,120, 250, 25);
        descriptionInput.setBounds(100,140, 250, 25);
        bankAccountNumber.setBounds(100,190, 250, 25);
        confirmation.setBounds(100,170, 250, 25);
        submitnewbankaccount.setBounds(140,230, 150, 25);
        backToMain.setBounds(140,270, 150, 25);

        //Add all to panel
        panel.add(title);
        panel.add(preStartAmount);
        panel.add(startAmountInput);
        panel.add(preDescription);
        panel.add(descriptionInput);
        panel.add(submitnewbankaccount);
        panel.add(bankAccountNumber);
        panel.add(confirmation);
        panel.add(backToMain);
        title.setFont(overviewPanel.f);

        //Set panel to visible and initialise the event handlers
        getContentPane().add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setNewBankAccount();
        setBackToMain();
    }

    //Event handler of the submit button
    public void setNewBankAccount() {
        submitnewbankaccount.addActionListener(e -> {
            //Set all the values necessary for the new bank account
            double amount = Double.parseDouble(startAmountInput.getText());
            String description = descriptionInput.getText();

            //Amount can't be negative and description must be filled
            if(amount>0.0 && !description.equals(null)) {

                //Initialise new bankaccount and account
                BankAccount bankAccount = new BankAccount(amount, description);
                Account account = new Account();

                //Execute the create bank account
                account.CreateBankAccount(bankAccount, bankAccountNumber.getText());

                //Show confirmation, and open the overview panel
                JOptionPane.showMessageDialog(null, "Uw nieuwe bankaccount " + description + " is aangemaakt met " + amount + " als startbedrag");
                overviewPanel overviewPanel = new overviewPanel();
                overviewPanel.setVisible(true);
                dispose();
            }
            //Else return error
            else {
                JOptionPane.showMessageDialog(null, "U heeft niet alle velden correct ingevuld");
                startAmountInput.setText("");
                descriptionInput.setText("");
                startAmountInput.requestFocus();
            }
        });
    }

    //Backbutton event handler
    public void setBackToMain(){
        backToMain.addActionListener(ae -> {
            overviewPanel overviewPanel = new overviewPanel();
            overviewPanel.setVisible(true);
            dispose();
        });
    }
}