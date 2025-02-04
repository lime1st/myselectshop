package lime1st.myselectshop.controller;

import lime1st.myselectshop.dto.ProductMyPriceRequestDto;
import lime1st.myselectshop.dto.ProductRequestDto;
import lime1st.myselectshop.dto.ProductResponseDto;
import lime1st.myselectshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j(topic = "ProductController")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/products")
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto requestDto) {
        return productService.createProduct(requestDto);
    }

    @PutMapping("/products/{id}")
    public ProductResponseDto updateProduct(@PathVariable Long id, @RequestBody ProductMyPriceRequestDto requestDto) {
        log.info("price {}", requestDto.getMyprice());
        return productService.updateProduct(id, requestDto);
    }
}
