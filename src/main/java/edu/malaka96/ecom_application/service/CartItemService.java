package edu.malaka96.ecom_application.service;

import edu.malaka96.ecom_application.model.CartItem;
import edu.malaka96.ecom_application.model.Product;
import edu.malaka96.ecom_application.model.User;
import edu.malaka96.ecom_application.model.dto.CartItemRequest;
import edu.malaka96.ecom_application.repository.CartItemRepository;
import edu.malaka96.ecom_application.repository.ProductRepository;
import edu.malaka96.ecom_application.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CartItemService {
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public boolean addToCart(String userId, CartItemRequest cartItemRequest){
        Optional<Product> productOptional = productRepository.findById(cartItemRequest.getProductId());
        if(productOptional.isEmpty())
            return false;
        Product product = productOptional.get();

        if(product.getStockQuantity() < cartItemRequest.getQuantity())
            return false;

        Optional<User> userOptional = userRepository.findById(Long.valueOf(userId));
        if(userOptional.isEmpty())
            return false;
        User user = userOptional.get();

        CartItem exisistingCartItem = cartItemRepository.findByUserAndProduct(user, product);
        if(exisistingCartItem != null){
            // update quantity
            exisistingCartItem.setQuantity(exisistingCartItem.getQuantity() + cartItemRequest.getQuantity());
            exisistingCartItem.setPrice(exisistingCartItem.getPrice().add(product.getPrice().multiply(BigDecimal.valueOf(cartItemRequest.getQuantity()))));
            cartItemRepository.save(exisistingCartItem);
        }else{
            // add new item
            CartItem cartItem = CartItem.builder()
                    .user(user)
                    .product(product)
                    .quantity(cartItemRequest.getQuantity())
                    .price(product.getPrice().multiply(BigDecimal.valueOf(cartItemRequest.getQuantity())))
                    .build();
            cartItemRepository.save(cartItem);
        }
        return true;

    }

    public boolean deleteItemFromCart(String userId, Long productId){
        Optional<Product> productOptional = productRepository.findById(productId);
        if(productOptional.isEmpty())
            return false;

        Optional<User> userOptional = userRepository.findById(Long.valueOf(userId));
        if(userOptional.isEmpty())
            return false;

        productOptional.ifPresent(product -> cartItemRepository.deleteByUserAndProduct(
                userOptional.get(),
                product
        ));

        return true;
    }
}
