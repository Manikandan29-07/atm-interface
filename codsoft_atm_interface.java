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

import java.io.*;
import java.util.*;

class ATM
{
    private UserAccount user ;
    private Scanner sc ;
    
    public ATM(UserAccount user)
    {
        this.user = user ;
        this.sc = new Scanner(System.in);
    }

    public void withdraw(int amount)
    {
        user.handleWithdrawal(amount);
    }
    public void deposit(int amount)
    {
        user.handleDeposit(amount);
    }
    public void checkBalance()
    {
        user.checkBalance();
    }
}

class UserAccount
{
    private double balance ;
    private int pin ;

    public UserAccount(double initialBalance , int pin)
    {
        this.balance = initialBalance;
        this.pin = pin ;
    }

    public void handleWithdrawal(int amount)
    {
        if(amount > 20000)
        {
            System.out.println("MAXIMUM WITHDRAWAL LIMIT IS 20000 INR");
        }
        else if(amount > balance)
        {
            System.out.println("INSUFFICIENT BALANCE");
        }
        else
        {
            System.out.println("WITHDRAWAL SUCCESSFUL");
            balance -= amount ;
            displayBalance();
        }
    }
    
    public void handleDeposit(int amount)
    {
        if(amount > 100000)
        {
            System.out.println("MAXIMUM DEPOSIT LIMIT IS 100000 INR");
        }
        else
        {
            System.out.println("DEPOSIT SUCCESSFUL");
            balance += amount ;
            displayBalance();
        }
    }

    public void checkBalance()
    {
        System.out.println("BALANCE : "+balance + " INR");
    }

    private void displayBalance()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.println("DO YOU WANT TO DISPLAY YOUR BALANCE ON SCREEN(yes/no)");
        String option = sc.nextLine();
        if(option.equals("yes"))
        {
            System.out.println("BALANCE : " + balance +" INR");
        }
    }

    public boolean verifyPin(int inputPin) 
    {
        return this.pin == inputPin;
    }
}

class MainClass
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        UserAccount user = new UserAccount(10000,1234);
        ATM atm = new ATM(user);
        System.out.println();
        System.out.println("WELCOME TO ATM");
        System.out.println();
        System.out.println("INSERT YOUR CARD"); 
        System.out.print("PLEASE ENTER YOUR 4 DIGIT PIN : ");
        int inputPin = sc.nextInt();
        sc.nextLine();
        if(!user.verifyPin(inputPin))
        {
            System.out.println("INCORRECT PIN...EXIT");
            return ;
        }
        
        String contineOption ;
        do
        {   
            System.out.printf("\n1 - WITHDRAWAL\n2 - DEPOSIT\n3 - CHECK BALANCE\n");
            System.out.print("ENTER THE SERVICE : ");
            int choice = sc.nextInt(); 
            sc.nextLine();
            switch(choice)
            {
                case 1->{
                            System.out.println("ENTER THE AMOUNT : ") ;
                            int amount = sc.nextInt();
                            sc.nextLine();
                            atm.withdraw(amount);
                        }
                case 2->{
                            System.out.println("ENTER THE AMOUNT : ") ;
                            int amount = sc.nextInt();
                            sc.nextLine();
                            atm.deposit(amount);
                        }
                case 3->{
                            atm.checkBalance() ;
                        }   
                default -> System.out.println("ENTERED SERVICE IS INVALID");
            }
            System.out.println();
            System.out.print("DO YOU WANT TO CONTINUE : ");
            contineOption = sc.nextLine();
        }while (contineOption.equalsIgnoreCase("yes"));
        System.out.println("THANK YOU...HAVE A NICE DAY");
        sc.close();
    }
}