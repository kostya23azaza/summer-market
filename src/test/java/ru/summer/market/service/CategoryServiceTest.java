package ru.summer.market.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import summer.market.exceptions.CategoryNotFoundException;
import summer.market.model.Category;
import summer.market.repositories.CategoryRepository;
import summer.market.services.CategoryService;



@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

  @InjectMocks
  private CategoryService categoryService;
  @Mock
  private CategoryRepository categoryRepository;

  private Category category;
  private List<Category> categoryList;


  @BeforeEach
  public void setUp() {
    category = Category
      .builder()
      .id(1L)
      .title("testCategory")
      .products(new ArrayList<>())
      .build();
    categoryList = new ArrayList<>();
    categoryList.add(category);
  }


  @Test
  public void findCategoryById() {
    when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
    Category test = categoryService.findById(1L);
    assertEquals(test, category);
  }

  @Test
  public void findCategoryByIdWhenCategoryNotFoundExceptionWasThrown() {
    when(categoryRepository.findById(3L)).thenReturn(Optional.empty());
    assertThrows(CategoryNotFoundException.class, () -> categoryService.findById(3L));
    verify(categoryRepository).findById(3L);
  }

  @Test
  public void deleteAllCategories() {
    categoryService.deleteCategoryById(3L);
    verify(categoryRepository).deleteById(3L);
  }

}
