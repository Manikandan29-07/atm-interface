/*1.Create a class to represent the ATM machine.

2. Design the user interface for the ATM, including options such as withdrawing, depositing, and
checking the balance.

3. Implement methods for each option, such as withdraw(amount), deposit(amount), and
checkBalance().

4. Create a class to represent the user's bank account, which stores the account balance.

5. Connect the ATM class with the user's bank account class to access and modify the account
balance.

6. Validate user input to ensure it is within acceptable limits (e.g., sufficient balance for withdrawals).

7. Display appropriate messages to the user based on their chosen options and the success or failure
of their transactions. */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

class ATM implements ActionListener
{
    JButton withdraw_button , deposit_button , balance_button ,next_button ,close_button;
    JFrame frame ;
    JTextField text_1 ;
    JLabel label_1 ;

    User_Account user = new User_Account();

    ATM()
    {
        frame = new JFrame("ATM INTERFACE");
        frame.setSize(300,200);
        frame.setVisible(true);
        frame.setResizable(true);
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        withdraw_button = new JButton("Withdrawal");
        withdraw_button.addActionListener(this);

        deposit_button = new JButton("Deposit");
        deposit_button.addActionListener(this);

        balance_button = new JButton("Check Balance");
        balance_button.addActionListener(this);

        close_button = new JButton("Close"); 
        close_button.addActionListener(e -> System.exit(0));

        frame.add(withdraw_button);
        frame.add(deposit_button);
        frame.add(balance_button);
        frame.setLocationRelativeTo(null);
        frame.revalidate();

    }

    public void actionPerformed(ActionEvent e)
    {
        frame.getContentPane().removeAll();  
        frame.add(withdraw_button);
        frame.add(deposit_button);
        frame.add(balance_button);
        
        if(e.getSource() == withdraw_button)
        {
            handleWithdrawal();
        }
        else if(e.getSource() == deposit_button)
        {
            handleDeposit();
        }
        else if(e.getSource() == balance_button)
        {
            handleCheckBalance();
        }

        frame.revalidate();
        frame.repaint();
    }

    private void handleWithdrawal()
    {
        label_1 = new JLabel("Enter the amount to Withdraw");
        text_1 = new JTextField(10);
        next_button = new JButton("Next");
        frame.add(label_1);
        frame.add(text_1);
        frame.add(next_button);
        next_button.addActionListener(new ActionListener() 
        {                                                        
            public void actionPerformed(ActionEvent a)
            {
                try 
                {
                    int amount = Integer.parseInt(text_1.getText());
                    user.setter("withdraw", amount);
                    user.withdrawOrDeposit();
                }
                catch(NumberFormatException nfe)
                {
                    JOptionPane.showMessageDialog(null,"Invalid Amount, please Enter a valid amount","ATM MESSAGE",JOptionPane.ERROR_MESSAGE,null);
                }
                resetFrame();   
            }
        });
    }

    private void handleDeposit()
    {
        text_1 = new JTextField(10);
        label_1 = new JLabel("Enter the amount to deposit");
        next_button = new JButton("Next");
        frame.add(label_1);
        frame.add(text_1);
        frame.add(next_button);
        next_button.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    int amount = Integer.parseInt(text_1.getText());
                    user.setter("deposit", amount);
                    user.withdrawOrDeposit();
                }
                catch(NumberFormatException nfe)
                {
                    JOptionPane.showMessageDialog(null,"Invalid Amount.please enter a valid amount","ATM MESSAGE",JOptionPane.ERROR_MESSAGE,null);
                }
                resetFrame();
            }    
        });
    }

    public void handleCheckBalance()
    {
        user.balanceCheckOnly();
    }

    private void resetFrame() 
    {
        frame.getContentPane().removeAll();
        frame.add(withdraw_button);
        frame.add(deposit_button);
        frame.add(balance_button);
        frame.add(close_button);
        frame.revalidate();
        frame.repaint();
    }
}

class User_Account
{
    private double balance = 10000 ;
    private int amount ;
    private String action ;

    void setter(String action ,int amount)
    {
        this.action = action ;
        this.amount = amount ;
    }

    public void withdrawOrDeposit()
    {
        if("withdraw".equals(action))
        {
            if(amount > balance && amount < 20000)
            {
                JOptionPane.showMessageDialog(null,"Insufficient Balance","ATM MESSAGE",JOptionPane.WARNING_MESSAGE,null) ;
            }
            else
            {
                if(amount > 20000)
                {
                    JOptionPane.showMessageDialog(null,"Withdrawal limit is 20000 INR...Please enter less than 20000 INR","ATM MESSAGE",JOptionPane.WARNING_MESSAGE,null);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Withdrawal Successfull...","ATM MESSAGE",JOptionPane.INFORMATION_MESSAGE,null);
                    balance -= amount ;
                    JOptionPane.showMessageDialog(null, "Available Balance : "+balance+" INR","ATM MESSAGE",JOptionPane.INFORMATION_MESSAGE,null);
                }
            } 
        }
        else if("deposit".equals(action))
        {
            balance += amount;
            JOptionPane.showMessageDialog(null, "Deposit Successfull","ATM MESSAGE",JOptionPane.INFORMATION_MESSAGE,null);
            JOptionPane.showMessageDialog(null, "New Balance : "+balance+" INR","ATM MESSAGE",JOptionPane.INFORMATION_MESSAGE,null);
        }
    }

    public void balanceCheckOnly()
    {
       JOptionPane.showMessageDialog(null, "Current Balance : " + balance+" INR","ATM MESSAGE",JOptionPane.INFORMATION_MESSAGE,null);
    }
}

class MainClass
{
    public static void main(String[] args) 
    {
        new ATM();    
    }
}