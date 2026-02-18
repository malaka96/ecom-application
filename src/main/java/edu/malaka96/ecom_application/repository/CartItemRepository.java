package edu.malaka96.ecom_application.repository;

import edu.malaka96.ecom_application.model.CartItem;
import edu.malaka96.ecom_application.model.Product;
import edu.malaka96.ecom_application.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findByUserAndProduct(User user, Product product);
}
