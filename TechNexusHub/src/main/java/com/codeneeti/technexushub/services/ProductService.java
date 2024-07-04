package com.codeneeti.technexushub.services;

import com.codeneeti.technexushub.dtos.PageableResponse;
import com.codeneeti.technexushub.dtos.ProductDto;

public interface ProductService {
    ProductDto create(ProductDto productDTO);

    ProductDto update(ProductDto productDTO, String productId);

    void delete(String productId);

    ProductDto get(String productId);

    PageableResponse<ProductDto> getAll(int pageNumber,
                                        int pageSize,
                                        String sortBy,
                                        String sortDir);

    PageableResponse<ProductDto> getAllLive(int pageNumber,
                                            int pageSize,
                                            String sortBy,
                                            String sortDir);

    PageableResponse<ProductDto> searchByTitle(String pageNumber,
                                               int numberSize,
                                               int pageSize,
                                               String sortBy,
                                               String sortDir);
    //create product with Category
    ProductDto createWithCategory(ProductDto productDTO, String categoryId);
    ProductDto updateCategory(String productId, String categoryId);

    PageableResponse<ProductDto>getAllOfCategory(String categoryId, int pageNumber,
                                                 int pageSize,
                                                 String sortBy,
                                                 String sortDir);

}
