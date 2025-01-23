package com.F.T.user_service.request;

public class RequestForUpdateUser {

    private String email;
    private String phoneNumber;

    public RequestForUpdateUser(String email,String phoneNumber){
        this.email=email;
        this.phoneNumber=phoneNumber;
    }

    public String getEmail(){
        return email;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

}
