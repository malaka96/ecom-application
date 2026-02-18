package edu.malaka96.ecom_application.controller;

import edu.malaka96.ecom_application.model.dto.CartItemRequest;
import edu.malaka96.ecom_application.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CartItemController {
    private final CartItemService cartItemService;

    @PostMapping("/api/cart")
    public ResponseEntity<String> addToCart(@RequestHeader("X-User-ID") String userId,
                                            @RequestBody CartItemRequest request){
        if(!cartItemService.addToCart(userId, request)){
            return ResponseEntity.badRequest().body("Product Out of Stock or User Not Found");
        }else{
            return ResponseEntity.status(HttpStatus.CREATED).body("Item added to the cart");
        }
    }

    @DeleteMapping("/api/cart/delete/{id}")
    public ResponseEntity<String> deleteFromCart(@RequestHeader("X-User-ID") String userId,
                                                 @PathVariable Long id){
        if(!cartItemService.deleteItemFromCart(userId, id)){
            return ResponseEntity.badRequest().body("Product Not Found in Cart or User Not Found");
        }else{
            return ResponseEntity.ok("Item removed from the cart");
        }
    }
}
