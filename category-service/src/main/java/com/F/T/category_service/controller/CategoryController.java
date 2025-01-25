package com.F.T.category_service.controller;

import com.F.T.category_service.request.RequestForCreateCategory;
import com.F.T.category_service.request.RequestForUpdateCategory;
import com.F.T.category_service.service.CategoryService;
import org.example.CategoryDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody RequestForCreateCategory request){
        return ResponseEntity.ok(categoryService.createCategory(request));
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable("categoryId") String categoryID,
                                                      @RequestBody RequestForUpdateCategory request){
        return ResponseEntity.ok(categoryService.updateCategory(categoryID,request));
    }

    @PutMapping("/category-id/{categoryId}/add-book/{bookId}")
    public ResponseEntity<String> addBookToCategory(@PathVariable("categoryId")String categoryId,
                                                    @PathVariable("bookId")String bookId){
        categoryService.addBookToCategory(categoryId,bookId);
        return ResponseEntity.ok("Book added successfully ...");
    }

    @PutMapping("/category-id/{categoryId}/delete-book/{bookId}")
    public ResponseEntity<String> deleteBookToCategory(@PathVariable("categoryId")String categoryId,
                                                    @PathVariable("bookId")String bookId){
        categoryService.deleteBookToCategory(categoryId,bookId);
        return ResponseEntity.ok("Book deleted successfully ...");
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> findCategoryById(@PathVariable("categoryId") String categoryID){
        return ResponseEntity.ok(categoryService.findCategoryById(categoryID));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllCategory(){
        categoryService.deleteAllCategory();
        return ResponseEntity.ok("Categories deleted successfully ! ");
    }
}
