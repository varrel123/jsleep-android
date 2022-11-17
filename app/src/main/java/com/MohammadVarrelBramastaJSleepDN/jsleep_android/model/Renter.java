package com.MohammadVarrelBramastaJSleepDN.jsleep_android.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Renter extends Serializable
{
    public String username;
    public String address;
    public String phoneNumber;
    public static final String REGEX_PHONE = "[0-9]{9,12}";
    public static final String REGEX_NAME = "^[A-Z][a-z0-9_-]{4,20}$";

//    public Renter(String username, String phoneNumber, String address){
//
//        this.username = username;
//
//        this.address = address;
//
//        this.phoneNumber = phoneNumber;
//
//    }
//
//    public boolean validate(){
//
//        Pattern patternPhone = Pattern.compile(REGEX_PHONE);
//        Matcher matcherPhone = patternPhone.matcher(this.phoneNumber);
//        boolean matchFoundPhone = matcherPhone.find();
//
//
//        Pattern patternName = Pattern.compile(REGEX_NAME);
//        Matcher matcherName = patternName.matcher(this.username);
//        boolean matchFoundName = matcherName.find();
//
//        return matchFoundPhone && matchFoundName;
//
//    }

}



