package ru.geekbrains.winter.market.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.winter.market.api.ProductDto;
import ru.geekbrains.winter.market.api.ResourceNotFoundException;
import ru.geekbrains.winter.market.core.converters.ProductConverter;
import ru.geekbrains.winter.market.core.entities.Category;
import ru.geekbrains.winter.market.core.entities.Product;
import ru.geekbrains.winter.market.core.repositories.ProductRepository;
import ru.geekbrains.winter.market.core.repositories.specifications.ProductsSpecifications;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final IdentityMap identityMap;
    private final ProductConverter productConverter;

    public Page<Product> findAll(Specification<Product> spec, int page) {
        return productRepository.findAll(spec, PageRequest.of(page, 5));
    }

    public Optional<Product> findById(Long id) {
        try {
            if (identityMap.isInto(id)==null){
                Optional<Product> product = productRepository.findById(id);
                identityMap.add(product);
                return product;
            } else return identityMap.isInto(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public Product createNewProduct(ProductDto productDto) {
        Product product = new Product();
        product.setPrice(productDto.getPrice());
        product.setTitle(productDto.getTitle());
        Category category = categoryService.findByTitle(productDto.getCategoryTitle()).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        product.setCategory(category);
        productRepository.save(product);
        return product;
    }

    public Specification<Product> createSpecByFilters(Integer minPrice, Integer maxPrice, String title) {
        Specification<Product> spec = Specification.where(null);
        if (minPrice != null) {
            spec = spec.and(ProductsSpecifications.priceGreaterOrEqualsThan(minPrice));
        }
        if (maxPrice != null) {
            spec = spec.and(ProductsSpecifications.priceLessThanOrEqualsThan(maxPrice));
        }
        if (title != null) {
            spec = spec.and(ProductsSpecifications.titleLike(title));
        }
        return spec;
    }
}
