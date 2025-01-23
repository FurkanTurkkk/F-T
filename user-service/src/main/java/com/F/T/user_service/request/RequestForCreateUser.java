package com.F.T.user_service.request;

import java.time.LocalDate;

public class RequestForCreateUser {

    private String firstname;
    private String lastname;
    private String tc;
    private String email;
    private String phoneNumber;
    private LocalDate birthday;

    public RequestForCreateUser(String firstname,
                                String lastname,
                                String tc,
                                String email,
                                String phoneNumber,
                                LocalDate birthday) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.tc = tc;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getTc() {
        return tc;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public LocalDate getBirthday() {
        return birthday;
    }
}
