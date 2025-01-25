package com.F.T.book_service.feignclient;

import org.example.CategoryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "category-service")
public interface CategoryFeignClient {

    @GetMapping("/api/v1/category/{categoryId}")
    public ResponseEntity<CategoryDto> findCategoryById(@PathVariable("categoryId") String categoryID);

    @PutMapping("/api/v1/category/category-id/{categoryId}/add-book/{bookId}")
    public ResponseEntity<String> addBookToCategory(@PathVariable("categoryId")String categoryId,
                                                    @PathVariable("bookId")String bookId);

    @PutMapping("/api/v1/category/category-id/{categoryId}/delete-book/{bookId}")
    public ResponseEntity<String> deleteBookToCategory(@PathVariable("categoryId")String categoryId,
                                                       @PathVariable("bookId")String bookId);
}
