package com.F.T.category_service.model;

import com.F.T.category_service.request.RequestForUpdateCategory;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Document(collection = "categories")
public class Category {

    @Id
    private String id;

    private String categoryName;
    private List<String> booksId;

    public Category(){

    }

    public Category(String categoryName){
        this.categoryName=categoryName;
        this.booksId=new ArrayList<>();
    }

    public String getId(){
        return id;
    }

    public String getCategoryName(){
        return categoryName;
    }

    public List<String> getBooksId(){
        return booksId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(categoryName, category.categoryName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(categoryName);
    }

    public void updateCategory(RequestForUpdateCategory request){
        this.categoryName= request.getCategoryName();
    }
}
