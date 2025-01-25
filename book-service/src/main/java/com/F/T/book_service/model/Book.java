package com.F.T.book_service.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Document(collection = "books")
public class Book {

    @Id
    private String id;

    private String bookName;
    private String author;
    private int pageCount;
    private String explanation;
    private List<String> categoryId;
    private BigDecimal price;
    private Long stock;

    public Book(){

    }

    public Book(String bookName,
                String author,
                int pageCount,
                String explanation,
                List<String> categoryId,
                BigDecimal price,
                Long stock){
        this.bookName=bookName;
        this.author=author;
        this.pageCount=pageCount;
        this.explanation=explanation;
        this.categoryId=categoryId;
        this.price=price;
        this.stock=stock;
    }

    public String getId(){
        return id;
    }

    public String getBookName(){
        return bookName;
    }

    public String getAuthor(){
        return author;
    }

    public int getPageCount(){
        return pageCount;
    }

    public String getExplanation(){
        return explanation;
    }

    public List<String> getCategoryId(){
        return categoryId;
    }

    public BigDecimal getPrice(){
        return price;
    }

    public Long getStock(){
        return stock;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(bookName, book.bookName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(bookName);
    }
}
