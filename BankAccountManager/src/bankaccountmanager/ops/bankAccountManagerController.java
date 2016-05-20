/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankaccountmanager.ops;

import bankaccountmanager.dto.Account;
import bankaccountmanager.dto.Checking;
import bankaccountmanager.dto.Savings;
import bankaccountmanager.dao.bankAccountDAO;
import bankaccountmanager.ui.ConsoleIO;
import java.util.ArrayList;

/**
 *
 * @author apprentice
 */
public class bankAccountManagerController {

    private ConsoleIO console = new ConsoleIO();
    private bankAccountDAO daoLayer = new bankAccountDAO();
    private Checking mainCheckingAccount = new Checking();
    private Savings mainSavingsAccount = new Savings();
    
    private boolean keepTransacting = true;    
    private String inputPin;
    

    public void run() {

        console.print("Welcome to our ATM.\nFor demonstration purposes, "
                + "please user PIN 1234.\n"
                + "+++++++++++++++++++++++++++++++++++++++++++++++++");

        //1 user loaded with a checking and savings account
        daoLayer.loadSingleDefaultClient();
        boolean correctPin = false;
        inputPin = console.readString("Enter your pin");
        correctPin = checkPin(inputPin);

        if (!correctPin) {
            console.print("You entered an incorrect PIN.  Exiting ATM");
        }

        while (correctPin && keepTransacting) {
            console.print("\nActive Accounts: \n");
            listAccounts(inputPin);
            String choice = console.readString("\nWhich account do you want to access? (\"Checking\" or \"Savings\")\n");
            switch (choice) {
                case "Checking":
                    int subMenuChoice = console.readInt("Select an option (Input 1 or 2):\n1. Deposit\n2. Withdraw\n");
                    mainCheckingAccount = findCheckingAccount();
                    switch (subMenuChoice) {
                        case 1:
                            mainCheckingAccount.deposit(console.readDouble("Amount to deposit: "));
                            break;
                        case 2:
                            double amtToWithdraw = console.readDouble("Amount you would like to withdraw: ");
                            if (amtToWithdraw > mainCheckingAccount.balance + 100.0) {
                                console.print("Can't withdrawl that much...");
                            } else {
                                mainCheckingAccount.withdraw(amtToWithdraw);
                            }
                            break;
                        default:
                            console.print("Invalid Choice.");
                            keepTransacting = false;
                            break;
                    }
                    console.print("Current Checking Balance: ");
                    console.printDouble(mainCheckingAccount.balance);
                    break;
                case "Savings":
                    subMenuChoice = console.readInt("Select an option (Input 1 or 2):\n1. Deposit\n2. Withdraw\n");
                    mainSavingsAccount = findSavingsAccount();
                    switch (subMenuChoice) {
                        case 1:
                            mainSavingsAccount.deposit(console.readDouble("Amount to deposit: "));
                            break;
                        case 2:
                            double amtToWithdraw = console.readDouble("Amount you would like to withdraw: ");
                            if (amtToWithdraw > mainSavingsAccount.balance + 100) {
                                console.print("You've attempted to withdraw too much money!.");
                            } else {
                                console.print("Assessing $5 fee for Savings withdraw");
                                mainSavingsAccount.withdraw(amtToWithdraw);
                            }
                            break;
                        default:
                            console.print("Invalid Choice.");
                            keepTransacting = false;
                            break;
                    }
                    console.print("Current Savings Balance: ");
                    console.printDouble(mainSavingsAccount.balance);
                    break;
                default:
                    keepTransacting = false;
                    break;
            }
            String contTrans = console.readString("Would you like to conduct another transation? (y/n)");
            if (!contTrans.equals("y")) {
                console.print("Thank you, and have a nice day!");
                keepTransacting = false;
            } else {
                keepTransacting = true;
            }
        }
    }

    private boolean checkPin(String pin) {
        return daoLayer.checkPin(pin);
    }

    private void listAccounts(String inputPin) {
        ArrayList<Account> userAccounts = daoLayer.listAllAccounts(inputPin);

        for (Account a : userAccounts) {
            if (a.getClass().equals(Checking.class)) {
                console.print("Checking" + "\tCurrent Balance: " + a.balance);
            } else if (a.getClass().equals(Savings.class)) {
                console.print("Savings" + "\t\tCurrent Balance: " + a.balance);
            } else {
                console.print("No accounts found.");
            }
        }
    }

    private Checking findCheckingAccount() {
        return daoLayer.findCheckingAccount(inputPin);
    }

    private Savings findSavingsAccount() {
        return daoLayer.findSavingsAccount(inputPin);
    }

}
