package com.F.T.category_service.request;

public class RequestForUpdateCategory {

    private String categoryName;

    public RequestForUpdateCategory(String categoryName){
        this.categoryName=categoryName;
    }

    public String getCategoryName(){
        return categoryName;
    }

}
