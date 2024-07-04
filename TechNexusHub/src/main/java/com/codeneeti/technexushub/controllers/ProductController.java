package com.codeneeti.technexushub.controllers;

import com.codeneeti.technexushub.dtos.*;
import com.codeneeti.technexushub.services.FileService;
import com.codeneeti.technexushub.services.ProductService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private FileService fileService;
    @Value("${product.image.path}")
    private String imagePath;

    @PostMapping("/create")
    public ResponseEntity<ProductDto>create(@Valid @RequestBody ProductDto productDTO){
        ProductDto productDTO1 = productService.create(productDTO);
        return new ResponseEntity<>(productDTO1, HttpStatus.CREATED);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductDto>update(@PathVariable String productId, @RequestBody ProductDto productDTO){
        ProductDto updated = productService.update(productDTO, productId);
        return  new ResponseEntity<>(updated,HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<ApiResponseMessage>delete(@PathVariable String productId){
        productService.delete(productId);
        ApiResponseMessage apiResponse = ApiResponseMessage.builder()
                .message("product deleted")
                .status(HttpStatus.OK)
                .success(true)
                .build();
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);

    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto>getSinglProd(@PathVariable String productId){
        ProductDto productDTO = productService.get(productId);
        return new ResponseEntity<>(productDTO,HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<PageableResponse<ProductDto>>getAll(
            @RequestParam(value = "pageNumber",defaultValue = "0",required = false)int pageNumber,
            @RequestParam(value = "pageSize",defaultValue = "10",required = false)int pageSize,
            @RequestParam(value = "sortBy",defaultValue = "title",required = false)String sortBy,
            @RequestParam(value = "sortDir",defaultValue = "asc",required = false)String sortDir
    ){
        PageableResponse<ProductDto> all = productService.getAll(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(all,HttpStatus.OK);
    }
    @GetMapping("/live")
    public ResponseEntity<PageableResponse<ProductDto>>getAllLive(
            @RequestParam(value = "pagenumber",defaultValue = "0",required = false)int pageNumber,
            @RequestParam(value = "pageSize",defaultValue = "10",required = false)int pageSize,
            @RequestParam(value = "sortBy",defaultValue = "title",required = false)String sortBy,
            @RequestParam(value = "sortDir",defaultValue = "asc",required = false)String sortDir
    ){
        PageableResponse<ProductDto> all = productService.getAllLive(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(all,HttpStatus.OK);
    }
    @GetMapping("/search/{query}")
    public ResponseEntity<PageableResponse<ProductDto>>searchProduvt(
            @PathVariable String query,
            @RequestParam(value = "pagenumber",defaultValue = "0",required = false)int pageNumber,
            @RequestParam(value = "pageSize",defaultValue = "10",required = false)int pageSize,
            @RequestParam(value = "sortBy",defaultValue = "title",required = false)String sortBy,
            @RequestParam(value = "sortDir",defaultValue = "asc",required = false)String sortDir
    ){
        PageableResponse<ProductDto> all = productService.searchByTitle(query,pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(all,HttpStatus.OK);
    }

    //upload image
    @PostMapping("/image/{productId}")
    public ResponseEntity<ImageResponse>uploadProductImage(
            @PathVariable String productId,
            @RequestParam("productImage")MultipartFile image
            ) throws IOException {
        String uploadFileName = fileService.uploadFile(image, imagePath);
        ProductDto productDTO = productService.get(productId);
        productDTO.setProductImageName(uploadFileName);
        ProductDto updatedProduct = productService.update(productDTO, productId);
        ImageResponse build = ImageResponse.builder().imageName(updatedProduct.getProductImageName())
                .message("image uploaded successfully")
                .success(true).
                status(HttpStatus.OK)
                .build();
        return  new ResponseEntity<>(build,HttpStatus.OK);
    }
    //save image

    @GetMapping("/image/{productId}")
    public void serveUserImage(@PathVariable String productId,
                               HttpServletResponse response
    ) throws IOException {
        ProductDto productDTO = productService.get(productId);
        InputStream resource = fileService.getResource(imagePath, productDTO.getProductImageName());
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource, response.getOutputStream());
    }

}
