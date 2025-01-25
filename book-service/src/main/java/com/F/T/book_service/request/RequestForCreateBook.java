package com.F.T.book_service.request;

import java.math.BigDecimal;
import java.util.List;

public class RequestForCreateBook {

    private String bookName;
    private String author;
    private int pageCount;
    private String explanation;
    private List<String> categoryIdList;
    private BigDecimal price;
    private Long stock;

    public RequestForCreateBook(String bookName,
                                String author,
                                int pageCount,
                                String explanation,
                                List<String> categoryIdList,
                                BigDecimal price,
                                Long stock){
        this.bookName=bookName;
        this.author=author;
        this.pageCount=pageCount;
        this.explanation=explanation;
        this.categoryIdList=categoryIdList;
        this.price=price;
        this.stock=stock;
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

    public List<String> getCategoryIdList(){
        return categoryIdList;
    }

    public BigDecimal getPrice(){
        return price;
    }

    public Long getStock(){
        return stock;
    }
}
