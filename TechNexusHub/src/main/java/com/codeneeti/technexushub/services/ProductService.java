package com.codeneeti.technexushub.services;

import com.codeneeti.technexushub.dtos.PageableResponse;
import com.codeneeti.technexushub.dtos.ProductDTO;

import java.util.List;

public interface ProductService {
    ProductDTO create(ProductDTO productDTO);

    ProductDTO update(ProductDTO productDTO, String productId);

    void delete(String productId);

    ProductDTO get(String productId);

    PageableResponse<ProductDTO> getAll(int pageNumber,
                                        int pageSize,
                                        String sortBy,
                                        String sortDir);

    PageableResponse<ProductDTO> getAllLive(int pageNumber,
                                            int pageSize,
                                            String sortBy,
                                            String sortDir);

    PageableResponse<ProductDTO> searchByTitle(String pageNumber,
                                               int numberSize,
                                               int pageSize,
                                               String sortBy,
                                               String sortDir);

}
