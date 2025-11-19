package site.aiion.api.soccer.player.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PlayerReporitoryImpl implements PlayerRepositoryCustom{
    private final JPAQueryFactory queryFactory;
}
