package site.aiion.api.soccer.search.service;

import site.aiion.api.soccer.common.domain.Messenger;

public interface SearchService {
    Messenger searchByKeyword(String keyword, String domain);
}

