package edu.malaka96.ecom_application.controller;

import edu.malaka96.ecom_application.model.dto.CartItemRequest;
import edu.malaka96.ecom_application.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CartItemController {
    private final CartItemService cartItemService;

    @PostMapping("/api/cart")
    public ResponseEntity<String> addToCart(@RequestHeader("X-User-ID") String userId,
                                            @RequestBody CartItemRequest request){
        if(!cartItemService.addToCart(userId,request)){
            return ResponseEntity.badRequest().body("Product Out of Stock or User Not Found");
        }else{
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
    }
}
