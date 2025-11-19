package site.aiion.api.soccer.team.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TeamRepositoryImpl implements TeamRepositoryCustom {
    private final JPAQueryFactory queryFactory;
}

