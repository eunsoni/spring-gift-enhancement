package gift.dto;

import gift.entity.Product;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ProductDto {

    private Long id;

    @NotBlank(message = "Product name is mandatory")
    @Size(max = 15, message = "Product name can be up to 15 characters including spaces")
    @Pattern(
            regexp = "^[\\w\\s\\(\\)\\[\\]\\+\\-\\&\\/가-힣_]*$",
            message = "Product name contains invalid characters"
    )
    private String name;

    @Min(value = 0, message = "Price must be non-negative")
    private int price;

    @NotBlank(message = "Image URL is mandatory")
    private String imageUrl;

    @Min(value = 1, message = "Category ID must be positive")
    private Long categoryId;

    private String categoryName = "";

    // 기본 생성자
    public ProductDto() {
    }

    // 모든 필드를 받는 생성자
    public ProductDto(Long id, String name, int price, String imageUrl, Long categoryId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
        this.categoryId = categoryId;
    }

    // Product 엔티티를 받아서 Dto로 변환하는 생성자
    public ProductDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.imageUrl = product.getImageUrl();
        this.categoryId = product.getCategory().getId();
        this.categoryName = product.getCategory().getName();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public String getCategoryName() { return categoryName; }

    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}