package org.example;

public class UserDto {
    private String firstname;
    private String lastname;
    private String tc;
    private String email;
    private String phoneNumber;
    private int age;

    public UserDto(String firstname, String lastname, String tc, String email,String phoneNumber, int age) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.tc = tc;
        this.email = email;
        this.phoneNumber=phoneNumber;
        this.age = age;
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

    public int getAge() {
        return age;
    }
}
