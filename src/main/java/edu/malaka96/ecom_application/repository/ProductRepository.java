package edu.malaka96.ecom_application.repository;

import edu.malaka96.ecom_application.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
