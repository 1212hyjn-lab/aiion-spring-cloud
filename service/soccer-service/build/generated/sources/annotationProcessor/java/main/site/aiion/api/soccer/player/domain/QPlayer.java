package site.aiion.api.soccer.player.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPlayer is a Querydsl query type for Player
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPlayer extends EntityPathBase<Player> {

    private static final long serialVersionUID = -424099782L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPlayer player = new QPlayer("player");

    public final StringPath back_no = createString("back_no");

    public final StringPath birth_date = createString("birth_date");

    public final StringPath e_player_name = createString("e_player_name");

    public final StringPath height = createString("height");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath join_yyyy = createString("join_yyyy");

    public final StringPath nation = createString("nation");

    public final StringPath nickname = createString("nickname");

    public final StringPath player_name = createString("player_name");

    public final StringPath player_uk = createString("player_uk");

    public final StringPath position = createString("position");

    public final StringPath solar = createString("solar");

    public final site.aiion.api.soccer.team.domain.QTeam team;

    public final StringPath team_uk = createString("team_uk");

    public final StringPath weight = createString("weight");

    public QPlayer(String variable) {
        this(Player.class, forVariable(variable), INITS);
    }

    public QPlayer(Path<? extends Player> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPlayer(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPlayer(PathMetadata metadata, PathInits inits) {
        this(Player.class, metadata, inits);
    }

    public QPlayer(Class<? extends Player> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.team = inits.isInitialized("team") ? new site.aiion.api.soccer.team.domain.QTeam(forProperty("team"), inits.get("team")) : null;
    }

}

