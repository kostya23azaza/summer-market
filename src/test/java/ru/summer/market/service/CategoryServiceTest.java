package ru.summer.market.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import summer.market.exceptions.CategoryNotFoundException;
import summer.market.model.Category;
import summer.market.repositories.CategoryRepository;
import summer.market.services.CategoryService;



@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

  @Mock
  private CategoryService categoryService;
  @Mock
  private CategoryRepository categoryRepository;

  private static Category category;


  @BeforeAll
  public static void setUp() {
    category = Category
      .builder()
      .id(1L)
      .title("testCategory")
      .products(new ArrayList<>())
      .build();
  }


  @Test
  public void findCategoryById() {
    when(categoryService.findById(1L)).thenReturn(category);
    assertEquals(categoryService.findById(1L), category);
  }

  @Test
  public void findCategoryByIdWhenCategoryNotFoundExceptionWasThrown() {
    when(categoryService.findById(3L)).thenThrow(CategoryNotFoundException.class);
    assertThrows(CategoryNotFoundException.class, () -> categoryService.findById(3L));
  }

}
