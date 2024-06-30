package com.codeneeti.technexushub.services.impl;

import com.codeneeti.technexushub.dtos.PageableResponse;
import com.codeneeti.technexushub.dtos.ProductDTO;
import com.codeneeti.technexushub.entities.Product;
import com.codeneeti.technexushub.exceptions.ResourceNotFoundException;
import com.codeneeti.technexushub.helper.Helper;
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

    @Override
    public ProductDTO create(ProductDTO productDTO) {
        String stringUUID = UUID.randomUUID().toString();
        productDTO.setProductId(stringUUID);
        productDTO.setAddedDate(new Date());
        Product product = mapper.map(productDTO, Product.class);
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
}
