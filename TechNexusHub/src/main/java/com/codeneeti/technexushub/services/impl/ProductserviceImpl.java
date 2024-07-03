package com.codeneeti.technexushub.services.impl;

import com.codeneeti.technexushub.dtos.PageableResponse;
import com.codeneeti.technexushub.dtos.ProductDTO;
import com.codeneeti.technexushub.entities.Category;
import com.codeneeti.technexushub.entities.Product;
import com.codeneeti.technexushub.exceptions.ResourceNotFoundException;
import com.codeneeti.technexushub.helper.Helper;
import com.codeneeti.technexushub.repositories.CategoryRepository;
import com.codeneeti.technexushub.repositories.ProductRepository;
import com.codeneeti.technexushub.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

//import static sun.awt.image.MultiResolutionCachedImage.map;
@Service
public class ProductserviceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private CategoryRepository categoryRepository;

//    @Override
//    public ProductDTO create(ProductDTO productDTO) {
//        Product product = mapper.map(productDTO, Product.class);
//        String stringUUID = UUID.randomUUID().toString();
//        productDTO.setProductId(stringUUID);
//        productDTO.setAddedDate(new Date());
//        Product saved = productRepository.save(product);
//        return mapper.map(saved, ProductDTO.class);
//    }
@Override
public ProductDTO create(ProductDTO productDTO) {
    Product product = mapper.map(productDTO, Product.class);
    String stringUUID = UUID.randomUUID().toString();
    product.setProductId(stringUUID); // Assign the UUID to the entity
    product.setAddedDate(new Date());
    Product saved = productRepository.save(product);
    return mapper.map(saved, ProductDTO.class);
}


    @Override
    public ProductDTO update(ProductDTO productDTO, String productId) {
        Product productRepositoryById = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("product not foundbased onthis id"));

        productRepositoryById.setTitle(productDTO.getTitle());
        productRepositoryById.setDescription(productDTO.getDescription());
        productRepositoryById.setPrice(productDTO.getPrice());
        productRepositoryById.setDiscountPrice(productDTO.getDiscountPrice());
        productRepositoryById.setQuantity(productDTO.getQuantity());
        productRepositoryById.setStock(productDTO.isStock());
        productRepositoryById.setLive(productDTO.isLive());
        productRepositoryById.setProductImageName(productDTO.getProductImageName());
        Product saved = productRepository.save(productRepositoryById);


        return mapper.map(saved, ProductDTO.class);
    }

    @Override
    public void delete(String productId) {
        Product productRepositoryById = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("product not foundbased onthis id"));
        productRepository.delete(productRepositoryById);
    }

    @Override
    public ProductDTO get(String productId) {
        Product productRepositoryById = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("product not foundbased onthis id"));
        return mapper.map(productRepositoryById, ProductDTO.class);
    }

    @Override
    public PageableResponse<ProductDTO> getAll(int pageNumber,
                                               int pageSize,
                                               String sortBy,
                                               String sortDir) {
        Sort sort=(sortDir.equalsIgnoreCase("desc"))?(Sort.by(sortBy).descending()) :(Sort.by(sortBy).ascending());
        Pageable pageable= PageRequest.of(pageNumber,pageSize,sort);
        Page<Product> all = productRepository.findAll(pageable);

        return Helper.getPageableResponse(all,ProductDTO.class);
    }

    @Override
    public PageableResponse<ProductDTO> getAllLive(int pageNumber,
                                                   int pageSize,
                                                   String sortBy,
                                                   String sortDir) {
        Sort sort=(sortDir.equalsIgnoreCase("desc"))?(Sort.by(sortBy).descending()) :(Sort.by(sortBy).ascending());
        Pageable pageable= PageRequest.of(pageNumber,pageSize,sort);
        Page<Product> all = productRepository.findByLiveTrue(pageable);

        return Helper.getPageableResponse(all,ProductDTO.class);
    }

    @Override
    public PageableResponse<ProductDTO> searchByTitle(String subTitle, int pageNumber,
                                                      int pageSize,
                                                      String sortBy,
                                                      String sortDir) {

        Sort sort=(sortDir.equalsIgnoreCase("desc"))?(Sort.by(sortBy).descending()) :(Sort.by(sortBy).ascending());
        Pageable pageable= PageRequest.of(pageNumber,pageSize,sort);
        Page<Product> all = productRepository.findByTitleContaining(subTitle,pageable);

        return Helper.getPageableResponse(all,ProductDTO.class);
    }

    @Override
    public ProductDTO createWithCategory(ProductDTO productDTO, String categoryId) {
        // Find the category by its ID
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new ResourceNotFoundException("Category not found based on this ID"));

        // Map the DTO to the Product entity
        Product product = mapper.map(productDTO, Product.class);

        // Generate a UUID and set it on the product entity
        String stringUUID = UUID.randomUUID().toString();
        product.setProductId(stringUUID); // Ensure the ID is set on the entity

        // Set other fields on the product entity
        product.setAddedDate(new Date());
        product.setCategory(category);
        // Save the product entity to the repository
        Product saved = productRepository.save(product);

        // Map the saved entity back to a DTO
        return mapper.map(saved, ProductDTO.class);
    }

    @Override
    public ProductDTO updateCategory(String productId, String categoryId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("product not found based onthis id"));
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found based onthis id"));
product.setCategory(category);
        Product savedProduct = productRepository.save(product);

        return mapper.map(savedProduct,ProductDTO.class);
    }

    @Override
    public PageableResponse<ProductDTO> getAllOfCategory(String categoryId,int pageNumber,
                                                         int pageSize,
                                                         String sortBy,
                                                         String sortDir) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("category not found based on this ID"));
        Sort sort=(sortDir.equalsIgnoreCase("desc"))?(Sort.by(sortBy).descending()) :(Sort.by(sortBy).ascending());
        Pageable pageable=PageRequest.of(pageNumber,pageSize,sort);
        Page<Product> byCategory = productRepository.findByCategory(category,pageable);
        return Helper.getPageableResponse(byCategory,ProductDTO.class);
    }

}
