package com.MohammadVarrelBramastaJSleepDN.jsleep_android.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Account extends Serializable
{
    public double balance;
    public String name;
    public String email;
    public String password;
    public Renter renter;

    @Override
    public String toString()
    {
        return "Account{" +
                "balance=" + balance +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", renter= " + renter +
                '}';
    }
}
