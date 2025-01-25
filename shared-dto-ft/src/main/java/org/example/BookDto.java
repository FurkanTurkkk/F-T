package org.example;

import java.math.BigDecimal;
import java.util.List;

public class BookDto {

    private String bookName;
    private String authorName;
    private List<String> categoryName;
    private int pageCount;
    private String explanation;
    private BigDecimal price;
    private Long stock;

    public BookDto(){

    }

    public BookDto(String bookName,
                   String authorName,
                   List<String> categoryName,
                   int pageCount,
                   String explanation,
                   BigDecimal price,
                   Long stock) {
        this.bookName = bookName;
        this.authorName = authorName;
        this.categoryName = categoryName;
        this.pageCount = pageCount;
        this.explanation = explanation;
        this.price=price;
        this.stock=stock;
    }

    public String getBookName() {
        return bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public List<String> getCategoryName() {
        return categoryName;
    }

    public int getPageCount() {
        return pageCount;
    }

    public String getExplanation() {
        return explanation;
    }

    public BigDecimal getPrice(){
        return price;
    }

    public Long getStock(){
        return stock;
    }

}
