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
public abstract class Account {

    public double balance = 1000.00;
    protected String pin = "1234";

    public abstract void withdraw(double amount);

    public abstract void deposit(double amount);
}
