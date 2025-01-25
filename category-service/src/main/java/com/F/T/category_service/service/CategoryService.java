package com.F.T.category_service.service;

import com.F.T.category_service.converter.DtoConverter;
import com.F.T.category_service.exception.CategoryAlreadyExistException;
import com.F.T.category_service.exception.CategoryNotFoundException;
import com.F.T.category_service.model.Category;
import com.F.T.category_service.repository.CategoryRepository;
import com.F.T.category_service.request.RequestForCreateCategory;
import com.F.T.category_service.request.RequestForUpdateCategory;
import org.example.CategoryDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final DtoConverter converter;

    public CategoryService(CategoryRepository categoryRepository, DtoConverter converter) {
        this.categoryRepository = categoryRepository;
        this.converter = converter;
    }

    public CategoryDto createCategory(RequestForCreateCategory request) {
        checkForCreateCategoryByCategoryName(request.getCategoryName());
        return converter.convert(categoryRepository.save(new Category(request.getCategoryName())));
    }

    public CategoryDto updateCategory(String categoryID, RequestForUpdateCategory request) {
        Category category=checkCategoryByCategoryId(categoryID);
        category.updateCategory(request);
        return converter.convert(categoryRepository.save(category));
    }

    public void addBookToCategory(String categoryId, String bookId) {
        Category category=checkCategoryByCategoryId(categoryId);
        category.getBooksId().add(bookId);
        categoryRepository.save(category);
    }

    public CategoryDto findCategoryById(String categoryID) {
        return converter.convert(checkCategoryByCategoryId(categoryID));
    }

    public void deleteBookToCategory(String categoryId, String bookId) {
        Category category=checkCategoryByCategoryId(categoryId);
        category.getBooksId().remove(bookId);
        categoryRepository.save(category);
    }

    public void deleteAllCategory(){
        categoryRepository.deleteAll();
    }

    private void checkForCreateCategoryByCategoryName(String categoryName){
        Optional<Category> registeredCategory=categoryRepository.findByCategoryName(categoryName);
        if(registeredCategory.isPresent()){
            throw new CategoryAlreadyExistException("Category already exist by category name : "+categoryName);
        }
    }

    private Category checkCategoryByCategoryId(String categoryId){
        Optional<Category> registeredCategory= categoryRepository.findById(categoryId);
        if(registeredCategory.isEmpty()){
            throw new CategoryNotFoundException("Category could not found by category id : "+categoryId);
        }
        return registeredCategory.get();
    }

}
