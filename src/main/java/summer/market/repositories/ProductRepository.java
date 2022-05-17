package summer.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import summer.market.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByPriceGreaterThanEqual(int minPrice);
    List<Product> findAllByIdLessThanEqual(Long maxId);
    List<Product> findAllByIdBetweenAndPriceGreaterThan(Long minId, Long maxId, int minPrice);
}
