package com.codeneeti.technexushub.services;

import com.codeneeti.technexushub.dtos.CategoryDto;
import com.codeneeti.technexushub.dtos.PageableResponse;

public interface CategoryService {
    CategoryDto create(CategoryDto categoryDTO);
    CategoryDto update(CategoryDto categoryDTO, String categoryId);
    void  delete(String categoryId);
    CategoryDto getSingleDTO(String categoryId);
    PageableResponse<CategoryDto>getAll(int pageNumber, int pageSize, String sortBy, String sortDir);
}
