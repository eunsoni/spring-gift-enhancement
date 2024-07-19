package gift.service;

import gift.dto.ProductDto;
import gift.entity.Category;
import gift.entity.Product;
import gift.exception.ProductNotFoundException;
import gift.repository.CategoryRepository;
import gift.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public Page<Product> getProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> ProductNotFoundException.of(id));
    }

    public void addProduct(ProductDto productDto) {
        Category category = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        Product product = new Product(productDto.getName(), productDto.getPrice(), productDto.getImageUrl(), category);
        category.addProduct(product); // Link product with category
        productRepository.save(product);
    }

    public void updateProduct(Long id, ProductDto productDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> ProductNotFoundException.of(id));
        Category category = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        product.update(productDto.getName(), productDto.getPrice(), productDto.getImageUrl(), category);
        productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> ProductNotFoundException.of(id));
        Category category = product.getCategory();
        if (category != null) {
            category.removeProduct(product); // Remove product from category
        }
        productRepository.delete(product);
    }
}
