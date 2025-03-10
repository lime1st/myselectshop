package lime1st.myselectshop.naver.controller;

import lime1st.myselectshop.aop.Timer;
import lime1st.myselectshop.naver.dto.ItemDto;
import lime1st.myselectshop.naver.service.NaverApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class NaverApiController {

    private final NaverApiService naverApiService;

    @GetMapping("/search")
    @Timer
    public List<ItemDto> searchItems(@RequestParam("query") String query)  {
        return naverApiService.searchItems(query);
    }
}