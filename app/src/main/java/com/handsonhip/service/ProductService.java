package com.handsonhip.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.handsonhip.model.Product;
import com.handsonhip.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    //Product creation process
    public Product createProduct(Product product){
        return productRepository.save(product);
    }

    //Find a product by their id
    public Product getProductById(Long id){
        Optional<Product> product = productRepository.findById(id);
        return product.orElse(null);
    }

    //List all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    //Update product
    public Product updateProduct(Long id, Product updatedProduct) {
        if (productRepository.existsById(id)) {
            updatedProduct.setProductID(id);
            return productRepository.save(updatedProduct);
        }
        return null;
    }

    //Delete product
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
