package com.F.T.category_service.request;

public class RequestForCreateCategory {
    private String categoryName;

    public RequestForCreateCategory(String categoryName){
        this.categoryName=categoryName;
    }

    public String getCategoryName(){
        return categoryName;
    }
}
