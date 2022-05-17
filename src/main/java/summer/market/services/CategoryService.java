package summer.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import summer.market.exceptions.CategoryNotFoundException;
import summer.market.model.Category;
import summer.market.repositories.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;


    public Optional<Category> findByIdWithProducts(Long id) {
        return categoryRepository.findCategoryWithProducts(id);
    }

    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
    }

    public void deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);
    }
}
