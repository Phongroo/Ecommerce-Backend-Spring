package com.example.ecommerce.controller;

import com.example.ecommerce.model.Cart;
import com.example.ecommerce.model.ImageModel;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@CrossOrigin("http://localhost:4200/")
@RestController
@RequestMapping("/admin")
public class ProductController {
    @Autowired
    private ProductService productService;
    @PostMapping(value = {"/addNewProduct"},consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Product addProduct(@RequestPart Product product, @RequestPart("imageFile")MultipartFile[] file){
       // return ResponseEntity.ok(this.productService.addProduct(product));
        try {
            Set<ImageModel> imageModels = uploadImage(file);
            product.setProductImages(imageModels);
            return productService.addProduct(product);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }

    }
    public Set<ImageModel> uploadImage(MultipartFile[] multipartFile)throws IOException {
        Set<ImageModel> imageModels=new HashSet<>();
        for (MultipartFile file:multipartFile){
            ImageModel imageModel=new ImageModel(
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getBytes()
            );
            imageModels.add(imageModel);

        }
        return imageModels;
    }
    @GetMapping("/getAllProduct")
    public ResponseEntity<?>ListProduct(){
        return ResponseEntity.ok(this.productService.listProduct());
    }
    @DeleteMapping("/product/{productId}")
    public void deleteProduct(@PathVariable("productId") Long productId){
        this.productService.deleteProduct(productId);
    }
    @GetMapping("/product/{productId}")
    public Product getProduct(@PathVariable("productId") Long productId){
       return this.productService.getProduct(productId);
    }
    @GetMapping("/getProductDetail/{isSingleCheckout}")
    public List<Cart> getProductDetail(@PathVariable(name = "isSingleCheckout")boolean isSingleCheckout){
        return productService.getProductDetail(isSingleCheckout);
    }
}
