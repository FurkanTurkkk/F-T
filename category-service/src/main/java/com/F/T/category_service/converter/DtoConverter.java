package com.F.T.category_service.converter;

import com.F.T.category_service.model.Category;
import org.example.CategoryDto;
import org.springframework.stereotype.Component;


@Component
public class DtoConverter {

    public CategoryDto convert(Category category){
        return new CategoryDto(category.getCategoryName());
    }
}
