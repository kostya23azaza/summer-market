package summer.market.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import summer.market.exceptions.ProductNotFoundException;
import summer.market.model.Product;
import summer.market.repositories.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        // Проверяем гит
    }

    public void saveNewProduct(String title, int price) {
        Product product = new Product();
        product.setTitle(title);
        product.setPrice(price);
        if (product.getPrice() <= 0) {
            return;
        }
        productRepository.save(product);
    }

    public List<Product> findByMinPrice(int minPrice) {
        return productRepository.findAllByPriceGreaterThanEqual(minPrice);
    }
}
