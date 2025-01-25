package com.F.T.user_service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Objects;

@Document(collection = "users")
public class User {

    @Id
    private String id;

    private String firstname;
    private String lastname;
    private String tc;
    private String email;
    private String phoneNumber;
    private LocalDate birthday;
    private LocalDate registrationDate;

    public User(){

    }

    public User(String firstname, String lastname, String tc, String email,String phoneNumber, LocalDate birthday) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.tc = tc;
        this.email = email;
        this.phoneNumber=phoneNumber;
        this.birthday = birthday;
        this.registrationDate=LocalDate.now();
    }

    public String getId() {
        return id;
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

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email) && Objects.equals(phoneNumber, user.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, phoneNumber);
    }

    public void updateUser(String email,String phoneNumber){
        if(email==null){
            this.email=getEmail();
            this.phoneNumber=phoneNumber;
        }
        if(phoneNumber==null){
            this.phoneNumber=getPhoneNumber();
            this.email=email;
        }
        if(phoneNumber!=null && email!=null){
            this.email=email;
            this.phoneNumber=phoneNumber;
        }

    }
}
