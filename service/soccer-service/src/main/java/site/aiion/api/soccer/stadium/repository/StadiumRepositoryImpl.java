package site.aiion.api.soccer.stadium.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StadiumRepositoryImpl implements StadiumRepositoryCustom {
    private final JPAQueryFactory queryFactory;
}

