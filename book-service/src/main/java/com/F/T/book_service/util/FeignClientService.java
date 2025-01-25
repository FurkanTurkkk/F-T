package com.F.T.book_service.util;

import com.F.T.book_service.feignclient.CategoryFeignClient;
import com.F.T.book_service.model.Book;
import org.example.CategoryDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FeignClientService {

    private final CategoryFeignClient categoryFeignClient;

    public FeignClientService(CategoryFeignClient categoryFeignClient) {
        this.categoryFeignClient = categoryFeignClient;
    }

    public List<String> findCategoryNameByCategoryId(Book book){
        List<CategoryDto> categoryDtoList=book.getCategoryId()
                .stream()
                .map((categoryId)->categoryFeignClient.findCategoryById(categoryId).getBody())
                .toList();
        return categoryDtoList.stream()
                .map(CategoryDto::getName)
                .toList();
    }

    public void addBookToCategory(String categoryId,String bookId){
        categoryFeignClient.addBookToCategory(categoryId,bookId);
    }

    public void deleteBookToCategory(String categoryId,String bookId){
        categoryFeignClient.deleteBookToCategory(categoryId,bookId);
    }
}
