package rikkei.academy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rikkei.academy.model.Product;

public interface IProductRepository extends JpaRepository<Product, Long> {
}
