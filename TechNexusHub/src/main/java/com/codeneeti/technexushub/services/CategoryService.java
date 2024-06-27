package com.codeneeti.technexushub.services;

import com.codeneeti.technexushub.dtos.CategoryDTO;
import com.codeneeti.technexushub.dtos.PageableResponse;

public interface CategoryService {
    CategoryDTO create(CategoryDTO categoryDTO);
    CategoryDTO update(CategoryDTO categoryDTO,String categoryId);
    void  delete(String categoryId);
    CategoryDTO getSingleDTO(String categoryId);
    PageableResponse<CategoryDTO>getAll(int pageNumber,int pageSize,String sortBy,String sortDir);
}
