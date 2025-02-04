package lime1st.myselectshop.controller;

import lime1st.myselectshop.dto.ProductMyPriceRequestDto;
import lime1st.myselectshop.dto.ProductRequestDto;
import lime1st.myselectshop.dto.ProductResponseDto;
import lime1st.myselectshop.security.UserDetailsImpl;
import lime1st.myselectshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j(topic = "ProductController")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/products")
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto requestDto,
                                            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return productService.createProduct(requestDto, userDetails.getUser());
    }

    @PutMapping("/products/{id}")
    public ProductResponseDto updateProduct(@PathVariable("id") Long id, @RequestBody ProductMyPriceRequestDto requestDto) {
        log.info("price {}", requestDto.getMyprice());
        return productService.updateProduct(id, requestDto);
    }

    @GetMapping("/products")
    public Page<ProductResponseDto> getProducts(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sortBy") String sortBy,
            @RequestParam("isAsc") boolean isAsc,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return productService.getProducts(userDetails.getUser(), page - 1, size, sortBy, isAsc);
    }

    @PostMapping("/products/{productId}/folder")
    public void addFolder(@PathVariable("productId") Long productId, @RequestParam("folderId") Long folderId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        productService.addFolder(productId, folderId, userDetails.getUser());
    }
}
