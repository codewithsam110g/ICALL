package com.codewithsam.ichat.model;

public class DbProfile {
    String UserName;
    String PassWord;
    String PhoneNumber;
    String FullName;


    public void StoreData(String FullName,String Password,String PhoneNumber,String Username){

        this.FullName = FullName;
        this.PassWord = Password;
        this.PhoneNumber = PhoneNumber;
        this.UserName = Username;

    }

    public String getFullName(){return FullName; }
    public String getUserName(){return UserName; }
    public String getPassWord(){return PassWord; }
    public String getPhoneNumber(){return PhoneNumber; }
}
