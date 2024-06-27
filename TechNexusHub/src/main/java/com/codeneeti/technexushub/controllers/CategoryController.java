package com.codeneeti.technexushub.controllers;

import com.codeneeti.technexushub.dtos.ApiResponse;
import com.codeneeti.technexushub.dtos.CategoryDTO;
import com.codeneeti.technexushub.dtos.ImageResponse;
import com.codeneeti.technexushub.dtos.PageableResponse;
import com.codeneeti.technexushub.repositories.CategoryRepository;
import com.codeneeti.technexushub.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryServicee;

    @PostMapping("/create")
    public ResponseEntity<CategoryDTO> create(
            @RequestBody CategoryDTO categoryDTO
    ) {
        CategoryDTO categoryDTO1 = categoryServicee.create(categoryDTO);
        return new ResponseEntity<>(categoryDTO1, HttpStatus.CREATED);
    }

    @PutMapping("/update/{categoryId}")
    public ResponseEntity<CategoryDTO> update(
            @PathVariable String categoryId,
            @RequestBody CategoryDTO categoryDTO
    ) {
        CategoryDTO updated = categoryServicee.update(categoryDTO, categoryId);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(
            @PathVariable String categoryId
    ) {
        categoryServicee.delete(categoryId);
        ApiResponse apiResponse = ApiResponse.builder()
                .message("Categorry is deleted")
                .success(true)
                .status(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<PageableResponse<CategoryDTO>> getAll(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "name", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
    ) {
        PageableResponse<CategoryDTO> allPageAbleResponse = categoryServicee.getAll(pageNumber, pageSize, sortBy, sortDir);
//        return new ResponseEntity<>(allPageAbleResponse, HttpStatus.OK);
        return ResponseEntity.ok(allPageAbleResponse);
        //we can send response in both wqy
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO>getSingle(
            @PathVariable("categoryId")String categoryId
    ){
        CategoryDTO singleDTO = categoryServicee.getSingleDTO(categoryId);
        return ResponseEntity.ok(singleDTO);
    }

   }
