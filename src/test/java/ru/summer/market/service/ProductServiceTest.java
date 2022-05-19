package ru.summer.market.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import summer.market.exceptions.ProductNotFoundException;
import summer.market.model.Product;
import summer.market.repositories.ProductRepository;
import summer.market.services.ProductService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

  @InjectMocks
  private ProductService productService;
  @Mock
  private ProductRepository productRepository;

  private Product product;

  @BeforeEach
  public void setUp() {
    product = Product.builder()
      .id(1L)
      .price(30)
      .title("testProduct")
      .build();
  }

  @Test
  public void findProductByIdTest() {
    when(productRepository.findById(1L)).thenReturn(Optional.of(product));
    Product test = productService.findById(1L);
    assertEquals(test, product);
    verify(productRepository).findById(1L);
  }

  @Test
  public void findProductByIdWhenExceptionWasThrown() {
    when(productRepository.findById(3L)).thenThrow(ProductNotFoundException.class);
    assertThrows(ProductNotFoundException.class, () -> productService.findById(3L));
    verify(productRepository).findById(3L);
  }

  @Test
  public void findAllTest() {
    productService.findAll();
    verify(productRepository).findAll();
  }

  @Test
  public void findByMinPriceTest() {
    productService.findByMinPrice(1);
    verify(productRepository).findAllByPriceGreaterThanEqual(1);
  }

  @Test
  public void saveNewProduct() {
    assertDoesNotThrow(() -> productService.saveNewProduct("test", 1));
  }

}
