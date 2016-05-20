/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankaccountmanager.dao;

import bankaccountmanager.dto.Account;
import bankaccountmanager.dto.Checking;
import bankaccountmanager.dto.Savings;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author apprentice
 */
public class bankAccountDAO {

    HashMap<String, ArrayList<Account>> allAccounts = new HashMap<>();

    private Checking checking1 = new Checking();
    private Savings savings1 = new Savings();

    //1 user loaded with a checking and savings account
    public void loadSingleDefaultClient() {
        ArrayList<Account> dummy = new ArrayList<>();
        dummy.add(checking1);
        dummy.add(savings1);
        allAccounts.put("1234", dummy);
    }

    public boolean checkPin(String pin) {
        return allAccounts.keySet().contains(pin); //review "contains"
    }

    public ArrayList<Account> listAllAccounts(String inputPin) {
        return allAccounts.get(inputPin);
    }

    public Checking findCheckingAccount(String inputPin) {
        ArrayList<Account> accounts = allAccounts.get(inputPin);
        Checking checkingAccount = new Checking();
        for (Account a : accounts) {
            if (a.getClass().equals(Checking.class)) {
                checkingAccount = (Checking) a;
            }
        }
        return checkingAccount;
    }

    public Savings findSavingsAccount(String inputPin) {
        ArrayList<Account> accounts = allAccounts.get(inputPin);
        Savings savingsAccount = new Savings();
        for (Account a : accounts) {
            if (a.getClass().equals(Savings.class)) {
                savingsAccount = (Savings) a;
            }
        }
        return savingsAccount;
    }

    public void deposit(String accountType, double amtToDeposit, String inputPin) {
        ArrayList<Account> depositAccount = allAccounts.get(inputPin);
        for (Account a : depositAccount) {
            if (a.getClass().equals(Checking.class) && accountType.equals("Checking")) {
                a.balance += amtToDeposit;
            } else if (a.getClass().equals(Savings.class) && accountType.equals("Savings")) {
                a.balance += amtToDeposit;
            }

        }
    }

    public double checkBalance(String accountType, String inputPin) {
        ArrayList<Account> accountList = allAccounts.get(inputPin);

        for (Account a : accountList) {
            if (a.getClass().equals(Checking.class) && accountType.equals("Checking")) {
                return a.balance;
            } else if (a.getClass().equals(Savings.class) && accountType.equals("Savings")) {
                return a.balance;
            }
        }
        return 0.0; //improper account type input
    }
}
