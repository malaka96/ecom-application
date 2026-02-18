package edu.malaka96.ecom_application.controller;

import edu.malaka96.ecom_application.model.dto.OrderResponse;
import edu.malaka96.ecom_application.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/api/placeorder")
    public ResponseEntity<?> placeOrder(@RequestHeader("X-User-ID") String userId){
        Optional<OrderResponse> orderResponse = orderService.placeOrder(userId);
        if(orderResponse.isEmpty()){
            return ResponseEntity.badRequest().build();
        }else{
            return ResponseEntity.ok(orderResponse);
        }
    }

}
