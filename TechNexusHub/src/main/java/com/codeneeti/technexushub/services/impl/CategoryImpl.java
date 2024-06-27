package com.codeneeti.technexushub.services.impl;

import com.codeneeti.technexushub.dtos.CategoryDTO;
import com.codeneeti.technexushub.dtos.PageableResponse;
import com.codeneeti.technexushub.entities.Category;
import com.codeneeti.technexushub.exceptions.ResourceNotFoundException;
import com.codeneeti.technexushub.helper.Helper;
import com.codeneeti.technexushub.repositories.CategoryRepository;
import com.codeneeti.technexushub.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.rmi.server.UID;
import java.util.UUID;

@Service
public class CategoryImpl implements CategoryService {
//    @Autowired
//    private CategoryService categoryService;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper mapper;

    @Override
    public CategoryDTO create(CategoryDTO categoryDTO) {
        String categoryId = UUID.randomUUID().toString();
        categoryDTO.setCategoryId(categoryId);
        Category mapped = mapper.map(categoryDTO, Category.class);
        Category saved = categoryRepository.save(mapped);
        CategoryDTO mapped1 = mapper.map(mapped, CategoryDTO.class);
        return mapped1;
    }

    @Override
    public CategoryDTO update(CategoryDTO categoryDTO, String categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("category is not found based on this ID"));

        category.setTitle(categoryDTO.getTitle());
        category.setCoverImage(categoryDTO.getCoverImage());

        category.setDescription(categoryDTO.getDescription());
        categoryRepository.save(category);
        return mapper.map(category, CategoryDTO.class);
    }

    @Override
    public void delete(String categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("category is not found based on this ID"));
        categoryRepository.delete(category);
    }

    @Override
    public CategoryDTO getSingleDTO(String categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("category is not found based on this ID"));

        return mapper.map(category, CategoryDTO.class);
    }

    @Override
    public PageableResponse<CategoryDTO> getAll(int pageNumber, int pageSize, String sortBy, String sortDir) {
//        Sort sort=()?():();
        Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Category> page = categoryRepository.findAll(pageable);
        PageableResponse<CategoryDTO> pageableResponse = Helper.getPageableResponse(page, CategoryDTO.class);
        return pageableResponse;
    }
}
