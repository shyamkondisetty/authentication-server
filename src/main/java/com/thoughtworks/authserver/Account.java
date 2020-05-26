package com.thoughtworks.authserver;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//class representing a bank account
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String ifscCode;
    private long accountNumber;
    private String accountHolderName;

    public Account(String accountHolderName, long accountNumber, String ifscCode) {
        this.accountHolderName = accountHolderName;
        this.accountNumber = accountNumber;
        this.ifscCode = ifscCode;
    }

    public Account() {
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public int getId() {
        return id;
    }
}
