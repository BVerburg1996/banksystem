package Panels;

import accountManagment.Account;
import javax.swing.*;

public class depositPanel<headers> extends JFrame {

    public static void main(String[] args) {
        depositPanel frameTabel = new depositPanel();
    }

    //Create all labels, inputs and buttons
    JLabel title = new JLabel("Storten op rekening");
    JLabel preAmount = new JLabel("Hoeveel wilt u storten?");
    JLabel preToInput = new JLabel("Naar Spaarrekening");
    JLabel preFromInput = new JLabel("Vanaf Spaarrekening");
    JTextField amountInput = new JTextField();
    JTextField toInput = new JTextField();
    JTextField fromInput = new JTextField();
    JButton submitDeposit = new JButton("Verzenden");
    JButton backToMain = new JButton("Terug");
    JPanel panel = new JPanel();

    //Deposit panel layout
    depositPanel(){
        super("B$nk");
        setSize(500,500);
        setLocation(700,300);
        panel.setLayout (null);

        //Set locations of all labels, inputs and buttons
        title.setBounds(125,10,250,60);
        preAmount.setBounds(100, 70, 250, 25);
        amountInput.setBounds(100, 90, 250, 25);
        preToInput.setBounds(100,120, 250, 25);
        toInput.setBounds(100,140, 250, 25);
        preFromInput.setBounds(100,170, 250, 25);
        fromInput.setBounds(100,190, 250, 25);
        submitDeposit.setBounds(140,250, 150, 25);
        backToMain.setBounds(140,285, 150, 25);

        //Add labels, inputs and buttons to panel
        panel.add(title);
        panel.add(preAmount);
        panel.add(amountInput);
        panel.add(preToInput);
        panel.add(toInput);
        panel.add(preFromInput);
        panel.add(fromInput);
        panel.add(submitDeposit);
        panel.add(backToMain);
        title.setFont(overviewPanel.f);

        //Set visible to true and add eventlisteners
        getContentPane().add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSubmitDeposit();
        setBackToMain();
    }

    //Submit deposit button event listener
    public void setSubmitDeposit() {
        submitDeposit.addActionListener(e -> {

            //Create all necessary parameters
            String descriptionFrom = fromInput.getText();
            double amount = Double.valueOf(amountInput.getText());
            String descriptionTo = toInput.getText();
            int accountID = loginPanel.currentAccountID;

            //Amount cant be negative, accountID and description can't be empty
            if(amount > 0.0 && accountID != 0 && !descriptionTo.equals("")) {
                //Create new account and execute the deposit
                Account account = new Account();
                account.Deposit(descriptionFrom, amount, descriptionTo, accountID);

                //Show confirmation, create new overview panel and dispose the deposit panel
                JOptionPane.showMessageDialog(null, "Uw bedrag van " + amount + " is overgestort van " + descriptionFrom + " naar " + descriptionTo);
                overviewPanel overviewPanel = new overviewPanel();
                overviewPanel.setVisible(true);
                dispose();
            }
            //Else return error and reset panel
            else {
                JOptionPane.showMessageDialog(null, "U heeft niet alle velden correct ingevuld");
                amountInput.setText("");
                toInput.setText("");
                fromInput.setText("");
                amountInput.requestFocus();
            }
        });
    }

    //Back button event listener
    public void setBackToMain(){
        backToMain.addActionListener(ae -> {
            //Create new overview panel and dispose the deposit panel
            overviewPanel overviewPanel =new overviewPanel();
            overviewPanel.setVisible(true);
            dispose();
        });
    }

}