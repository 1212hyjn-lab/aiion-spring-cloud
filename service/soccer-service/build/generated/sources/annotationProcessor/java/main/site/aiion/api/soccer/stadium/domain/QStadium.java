package site.aiion.api.soccer.stadium.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStadium is a Querydsl query type for Stadium
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStadium extends EntityPathBase<Stadium> {

    private static final long serialVersionUID = 433681632L;

    public static final QStadium stadium = new QStadium("stadium");

    public final StringPath address = createString("address");

    public final StringPath ddd = createString("ddd");

    public final StringPath hometeam_uk = createString("hometeam_uk");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<site.aiion.api.soccer.schedule.domain.Schedule, site.aiion.api.soccer.schedule.domain.QSchedule> schedules = this.<site.aiion.api.soccer.schedule.domain.Schedule, site.aiion.api.soccer.schedule.domain.QSchedule>createList("schedules", site.aiion.api.soccer.schedule.domain.Schedule.class, site.aiion.api.soccer.schedule.domain.QSchedule.class, PathInits.DIRECT2);

    public final StringPath seat_count = createString("seat_count");

    public final StringPath stadium_name = createString("stadium_name");

    public final StringPath stadium_uk = createString("stadium_uk");

    public final ListPath<site.aiion.api.soccer.team.domain.Team, site.aiion.api.soccer.team.domain.QTeam> teams = this.<site.aiion.api.soccer.team.domain.Team, site.aiion.api.soccer.team.domain.QTeam>createList("teams", site.aiion.api.soccer.team.domain.Team.class, site.aiion.api.soccer.team.domain.QTeam.class, PathInits.DIRECT2);

    public final StringPath tel = createString("tel");

    public QStadium(String variable) {
        super(Stadium.class, forVariable(variable));
    }

    public QStadium(Path<? extends Stadium> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStadium(PathMetadata metadata) {
        super(Stadium.class, metadata);
    }

}

