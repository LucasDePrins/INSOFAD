package com.example.powerflex.dao;

import com.example.powerflex.dto.ProductDTO;
import com.example.powerflex.models.Category;
import com.example.powerflex.models.Product;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@Component
public class ProductDAO {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductDAO(ProductRepository repository, CategoryRepository category) {
        this.productRepository = repository;
        this.categoryRepository = category;
    }

    public List<Product> getAllProducts(){
        System.out.println(this.productRepository.findAll());
        return this.productRepository.findAll();
    }

    public Product getProductById(long id) {
        Optional<Product> productOptional = this.productRepository.findById(id);
        if (productOptional.isPresent()) {
            return productOptional.get();
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "No product found with that ID"
        );
    }

    public List<Product> getAllProductsByCategory(long id){
        Optional<List<Product>> products = this.productRepository.findByCategoryId(id);

        if (products.isEmpty()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No products found with that category id"
            );
        }

        return products.get();
    }

    @Transactional
    public void createProduct(ProductDTO productDTO){
        Optional<Category> category = this.categoryRepository.findById(productDTO.categoryId);

        if (category.isPresent()){
            Product product = new Product(productDTO.name, productDTO.description, productDTO.price, category.get(), productDTO.imageUrl);
            this.productRepository.save(product);
            return;
        }

        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Category not found"
        );
    }

    @Transactional
    public void createProduct(Product product){
        this.categoryRepository.save(product.getCategory());
        this.productRepository.save(product);
    }

    public void updateProduct(ProductDTO productDTO, Long id){
        Optional<Product> product = this.productRepository.findById(id);

        if (product.isPresent()){
            product.get().setDescription(productDTO.description);
            product.get().setName(productDTO.name);

            this.productRepository.save(product.get());
            return;
        }

        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Product ID not found"
        );
    }

    public void deleteById(Long id) {
        this.productRepository.deleteById(id);
    }
}
