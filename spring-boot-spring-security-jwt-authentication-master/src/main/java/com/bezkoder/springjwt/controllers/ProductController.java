package com.bezkoder.springjwt.controllers;


import com.bezkoder.springjwt.models.Product;
import com.bezkoder.springjwt.repository.ProductRepository;
import com.bezkoder.springjwt.security.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.events.Event;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    ProductController(ProductRepository repository) {
        this.productRepository = repository;
    }

    @GetMapping("/products")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }


    @GetMapping("/products/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Product> get(@PathVariable Long id) {
        try {
            Optional<Product> optionalProduct = productRepository.findById(id);
            Product product = optionalProduct.get();
            return new ResponseEntity<Product>(product, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
    }


    //    @PostMapping("/products")
//    @PreAuthorize("hasRole('ADMIN')")
//    Product newProduct(@RequestBody Product newProduct) {
//        return productRepository.save(newProduct);
//    }
    @PostMapping("/products")
    @PreAuthorize("hasRole('ADMIN')")
    public void add(@RequestBody Product product) {
        productService.save(product);
    }


    @PutMapping("/products/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    Product replaceEmployee(@RequestBody Product newProduct, @PathVariable Long id) {

        return productRepository.findById(id)
                .map(product -> {
                    product.setName(newProduct.getName());
                    product.setContent(newProduct.getContent());
                    product.setPrice(newProduct.getPrice());
                    return productRepository.save(product);
                })
                .orElseGet(() -> {
                    newProduct.setId(id);
                    return productRepository.save(newProduct);
                });
    }

    @DeleteMapping("/products/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    void deleteEmployee(@PathVariable Long id) {
        productRepository.deleteById(id);
    }

}
