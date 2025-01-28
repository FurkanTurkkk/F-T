package com.F.T.order_service.request;


public class RequestForSendMailForSuccessOrder {

    private String userId;
    private String message;
    private String type;

    public RequestForSendMailForSuccessOrder(){

    }

    public RequestForSendMailForSuccessOrder(String userId, String message){
        this.userId=userId;
        this.message=message;
        this.type="E-mail";
    }

    public String getUserId(){
        return userId;
    }

    public String getMessage(){
        return message;
    }

    public String getType(){
        return type;
    }
}
