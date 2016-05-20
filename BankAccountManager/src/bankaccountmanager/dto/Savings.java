/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankaccountmanager.dto;

/**
 *
 * @author apprentice
 */
public class Savings extends Account {

    private String accountType = "Savings";

    public Savings() {
    }

    public String getAccountType() {
        return accountType;
    }

    @Override
    public void withdraw(double amount) {
        balance -= amount + 5;
        if (balance < 0) {
            balance -= 10;
        }
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
    }

}
