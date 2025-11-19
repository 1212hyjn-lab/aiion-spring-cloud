package site.aiion.api.soccer.schedule.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSchedule is a Querydsl query type for Schedule
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSchedule extends EntityPathBase<Schedule> {

    private static final long serialVersionUID = -343704986L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSchedule schedule = new QSchedule("schedule");

    public final StringPath away_score = createString("away_score");

    public final site.aiion.api.soccer.team.domain.QTeam awayteam;

    public final StringPath awayteam_uk = createString("awayteam_uk");

    public final StringPath gubun = createString("gubun");

    public final StringPath home_score = createString("home_score");

    public final site.aiion.api.soccer.team.domain.QTeam hometeam;

    public final StringPath hometeam_uk = createString("hometeam_uk");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath sche_date = createString("sche_date");

    public final site.aiion.api.soccer.stadium.domain.QStadium stadium;

    public final StringPath stadium_uk = createString("stadium_uk");

    public QSchedule(String variable) {
        this(Schedule.class, forVariable(variable), INITS);
    }

    public QSchedule(Path<? extends Schedule> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSchedule(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSchedule(PathMetadata metadata, PathInits inits) {
        this(Schedule.class, metadata, inits);
    }

    public QSchedule(Class<? extends Schedule> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.awayteam = inits.isInitialized("awayteam") ? new site.aiion.api.soccer.team.domain.QTeam(forProperty("awayteam"), inits.get("awayteam")) : null;
        this.hometeam = inits.isInitialized("hometeam") ? new site.aiion.api.soccer.team.domain.QTeam(forProperty("hometeam"), inits.get("hometeam")) : null;
        this.stadium = inits.isInitialized("stadium") ? new site.aiion.api.soccer.stadium.domain.QStadium(forProperty("stadium")) : null;
    }

}

