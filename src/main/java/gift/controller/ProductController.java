package gift.controller;

import gift.dto.ProductDto;
import gift.entity.Product;
import gift.service.CategoryService;
import gift.service.OptionService;
import gift.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/api/products")
public class ProductController {

    private final CategoryService categoryService;
    private final ProductService productService;
    private final OptionService optionService;

    public ProductController(CategoryService categoryService, ProductService productService, OptionService optionService) {
        this.categoryService = categoryService;
        this.productService = productService;
        this.optionService = optionService;
    }



    @PostMapping("/add")
    public String addProduct(@Valid @ModelAttribute("product") ProductDto productDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("product", new ProductDto());

            model.addAttribute("categories", categoryService.getAllCategories());
            return "add-product";
        }
        productService.addProduct(productDto);
        return "redirect:/view/products";
    }



    @PostMapping("/edit/{id}")
    public String updateProduct(@PathVariable Long id, @Valid @ModelAttribute("product") ProductDto productDto, BindingResult result, Model model) {
        Product product = productService.getProductById(productDto.getId());
        if (result.hasErrors()) {
            model.addAttribute("categories", categoryService.getAllCategories());
            model.addAttribute("options", optionService.getAllOptions());
            model.addAttribute("product", new ProductDto(product));
            return "edit-product";
        }
        
        productService.updateProduct(id, productDto);
        return "redirect:/view/products";
    }


}
