package com.F.T.notification_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String recipient;
    private String message;
    private String type;
    private LocalDateTime sentAt;

    public Notification(){

    }

    public Notification(String recipient,String message,String type){
        this.recipient=recipient;
        this.message=message;
        this.type=type;
        this.sentAt=LocalDateTime.now();
    }

    public Long getId(){
        return id;
    }

    public String getRecipient(){
        return recipient;
    }

    public String getMessage(){
        return message;
    }

    public String getType(){
        return type;
    }

    public LocalDateTime getSentAt(){
        return sentAt;
    }
}
