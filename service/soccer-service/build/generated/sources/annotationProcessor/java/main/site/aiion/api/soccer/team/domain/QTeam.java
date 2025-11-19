package site.aiion.api.soccer.team.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTeam is a Querydsl query type for Team
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTeam extends EntityPathBase<Team> {

    private static final long serialVersionUID = -234058126L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTeam team = new QTeam("team");

    public final StringPath address = createString("address");

    public final StringPath ddd = createString("ddd");

    public final StringPath e_team_name = createString("e_team_name");

    public final StringPath fax = createString("fax");

    public final StringPath homepage = createString("homepage");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath orig_yyyy = createString("orig_yyyy");

    public final StringPath owner = createString("owner");

    public final ListPath<site.aiion.api.soccer.player.domain.Player, site.aiion.api.soccer.player.domain.QPlayer> players = this.<site.aiion.api.soccer.player.domain.Player, site.aiion.api.soccer.player.domain.QPlayer>createList("players", site.aiion.api.soccer.player.domain.Player.class, site.aiion.api.soccer.player.domain.QPlayer.class, PathInits.DIRECT2);

    public final StringPath region_name = createString("region_name");

    public final site.aiion.api.soccer.stadium.domain.QStadium stadium;

    public final StringPath stadium_uk = createString("stadium_uk");

    public final StringPath team_name = createString("team_name");

    public final StringPath team_uk = createString("team_uk");

    public final StringPath tel = createString("tel");

    public final StringPath zip_code1 = createString("zip_code1");

    public final StringPath zip_code2 = createString("zip_code2");

    public QTeam(String variable) {
        this(Team.class, forVariable(variable), INITS);
    }

    public QTeam(Path<? extends Team> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTeam(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTeam(PathMetadata metadata, PathInits inits) {
        this(Team.class, metadata, inits);
    }

    public QTeam(Class<? extends Team> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.stadium = inits.isInitialized("stadium") ? new site.aiion.api.soccer.stadium.domain.QStadium(forProperty("stadium")) : null;
    }

}

