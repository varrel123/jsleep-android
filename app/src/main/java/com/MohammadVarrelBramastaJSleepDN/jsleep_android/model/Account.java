package com.MohammadVarrelBramastaJSleepDN.jsleep_android.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Account extends Serializable
{
    public String name;
    public String email;
    public String password;

    public  static final String REGEX_EMAIL =   "^[A-Za-z0-9] + @[A-Za-z.-]\\.[a-z]$";;


    public  static final String REGEX_PASSWORD = "^(?=.[A-Z]) (?=.[a-z]) (?=.[0-9]) [a-zA-Z\\d] {8,}$";
    public double balance;

//    public Account(String name,String email,String password){
//        this.name = name;
//        this.email = email;
//        this.password = password;
//    }
//
//    public boolean validate(){
//        Pattern patternEmail = Pattern.compile(REGEX_EMAIL);
//        Matcher matcherEmail = patternEmail.matcher(this.email);
//        boolean matchFoundEmail = matcherEmail.find();
//
//        Pattern patternPass = Pattern.compile(REGEX_PASSWORD);
//        Matcher matcherPass = patternPass.matcher(this.password);
//        boolean matchFoundPass = matcherPass.find();
//        return matchFoundEmail && matchFoundPass;
//    }
//
//    public String toString()
//    {
//        String print =    "name     : " + name +
//                "\nemail    : " + email +
//                "\npassword : " +  password +
//                "\nId       : " + id +
//                "\n";
//        return print;
//    }
//
//    public Object write(){
//        return null;
//    }
//
//    public boolean read(String content){
//        return false;
//    }
}
