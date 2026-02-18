package edu.malaka96.ecom_application.service;

import edu.malaka96.ecom_application.model.Product;
import edu.malaka96.ecom_application.model.dto.ProductRequest;
import edu.malaka96.ecom_application.model.dto.ProductResponse;
import edu.malaka96.ecom_application.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductResponse> getAllProducts() {
        return productRepository.findByIsActiveTrue().stream()
                .map(this::mapToProductResponse)
                .toList();
    }

    public void addProduct(ProductRequest productRequest){
        productRequest.setIsActive(true);
        productRepository.save(mapToProductEntity(productRequest));
    }

    public List<ProductResponse> searchProducts(String keyword){
        return productRepository.searchProducts(keyword).stream()
                .map(this::mapToProductResponse)
                .toList();

    }

    public boolean deleteProduct(Long id){
        return productRepository.findById(id).map(product -> {
            product.setIsActive(false);
            productRepository.save(product);
            return true;
        }).orElse(false);
    }


    public boolean updateProduct(Long id, ProductRequest productRequest){
        return productRepository.findById(id).map(existingProduct -> {
            existingProduct.setName(productRequest.getName());
            existingProduct.setDescription(productRequest.getDescription());
            existingProduct.setPrice(productRequest.getPrice());
            existingProduct.setStockQuantity(productRequest.getStockQuantity());
            existingProduct.setCategory(productRequest.getCategory());
            existingProduct.setImageUrl(productRequest.getImageUrl());
            existingProduct.setIsActive(productRequest.getIsActive());
            productRepository.save(existingProduct);
            return true;
        }).orElse(false);
    }

    private Product mapToProductEntity(ProductRequest productRequest){
        return Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .stockQuantity(productRequest.getStockQuantity())
                .category(productRequest.getCategory())
                .imageUrl(productRequest.getImageUrl())
                .isActive(productRequest.getIsActive())
                .build();
    }

    private ProductResponse mapToProductResponse(Product product){
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stockQuantity(product.getStockQuantity())
                .category(product.getCategory())
                .imageUrl(product.getImageUrl())
                .build();
    }


}
