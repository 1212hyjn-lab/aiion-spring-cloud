package site.aiion.api.soccer.search.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import site.aiion.api.soccer.common.domain.Messenger;
import site.aiion.api.soccer.search.service.SearchService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/search")
public class SearchController {

    private final SearchService searchService;

    @GetMapping("/keyword")
    public Messenger searchByKeyword(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "domain", required = false) String domain) {
        System.out.println("검색 요청 받음 - 키워드: [" + keyword + "], 도메인: [" + domain + "]");
        
        Messenger result = searchService.searchByKeyword(keyword, domain);
        
        // 컬럼 정보 반환 시 로그 출력
        if (result.getData() != null && result.getData() instanceof Map) {
            @SuppressWarnings("unchecked")
            Map<String, Object> data = (Map<String, Object>) result.getData();
            if (data.containsKey("table") && data.containsKey("columns")) {
                String tableName = (String) data.get("table");
                List<?> columns = (List<?>) data.get("columns");
                System.out.println("컬럼 정보 반환: " + tableName + " 테이블, " + columns.size() + "개 컬럼");
                System.out.println("컬럼 목록:");
                for (int i = 0; i < columns.size(); i++) {
                    System.out.println("  " + (i + 1) + ". " + columns.get(i));
                }
            }
        }
        
        return result;
    }
}

