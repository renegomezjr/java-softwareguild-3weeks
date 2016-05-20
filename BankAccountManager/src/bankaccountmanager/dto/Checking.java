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
public class Checking extends Account {

    private String accountType = "Checking";

    public String getAccountType() {
        return accountType;
    }

    @Override
    public void withdraw(double amount) {
        balance -= amount;
        if (balance < 0) {
            balance -= 10;
        }
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
    }

}
