package lime1st.myselectshop.service;

import lime1st.myselectshop.dto.ProductMypriceRequestDto;
import lime1st.myselectshop.dto.ProductRequestDto;
import lime1st.myselectshop.dto.ProductResponseDto;
import lime1st.myselectshop.entity.Product;
import lime1st.myselectshop.entity.User;
import lime1st.myselectshop.repository.FolderRepository;
import lime1st.myselectshop.repository.ProductFolderRepository;
import lime1st.myselectshop.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;

import java.util.Locale;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class) // @Mock 사용을 위해 설정합니다.
class ProductServiceTest {

    @Mock
    ProductRepository productRepository;

    @Mock
    FolderRepository folderRepository;

    @Mock
    ProductFolderRepository productFolderRepository;

    @Mock
    MessageSource messageSource;

    @Test
    @DisplayName("관심 상품 희망가 - 최저가 이상으로 변경")
    void test1() {
        // given
        Long productId = 100L;
        int myprice = ProductService.MIN_MY_PRICE + 3_000_000;

        ProductMypriceRequestDto requestMyPriceDto = new ProductMypriceRequestDto();
        requestMyPriceDto.setMyprice(myprice);

        User user = new User();
        ProductRequestDto requestProductDto = new ProductRequestDto(
                "Apple <b>맥북</b> <b>프로</b> 16형 2021년 <b>M1</b> Max 10코어 실버 (MK1H3KH/A) ",
                "https://shopping-phinf.pstatic.net/main_2941337/29413376619.20220705152340.jpg",
                "https://search.shopping.naver.com/gate.nhn?id=29413376619",
                3515000
        );

        Product product = new Product(requestProductDto, user);

        ProductService productService = new ProductService(
                productRepository, folderRepository, productFolderRepository, messageSource);

        given(productRepository.findById(productId)).willReturn(Optional.of(product));

        // when
        ProductResponseDto result = productService.updateProduct(productId, requestMyPriceDto);

        // then
        assertEquals(myprice, result.getMyprice());
    }

    @Test
    @DisplayName("관심 상품 희망가 - 최저가 미만으로 변경")
    void test2() {
        // given
        Long productId = 200L;
        int myprice = ProductService.MIN_MY_PRICE - 50;

        ProductMypriceRequestDto requestMyPriceDto = new ProductMypriceRequestDto();
        requestMyPriceDto.setMyprice(myprice);

        ProductService productService = new ProductService(
                productRepository, folderRepository, productFolderRepository, messageSource);

        // updateProduct 메서드에서 최저가 비교하는 부분은 상품을 검색하기 전에 진행되므로 test1 처럼 given 을 할 필요가 없다.
        given(messageSource.getMessage(
                "below.min.my.price",
                new Integer[]{ProductService.MIN_MY_PRICE},
                "Wrong Price",
                Locale.getDefault()
        )).willReturn("최저 희망가는 최소 " + ProductService.MIN_MY_PRICE +"원 이상으로 설정해 주세요.");

        // when
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> productService.updateProduct(productId, requestMyPriceDto));

        // then
        assertEquals(
                "최저 희망가는 최소 " + ProductService.MIN_MY_PRICE + "원 이상으로 설정해 주세요.",
                exception.getMessage()
        );
    }
}