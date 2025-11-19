package site.aiion.api.soccer.search.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import site.aiion.api.soccer.common.domain.Messenger;
import site.aiion.api.soccer.player.domain.Player;
import site.aiion.api.soccer.player.domain.PlayerModel;
import site.aiion.api.soccer.player.repository.PlayerRepository;
import site.aiion.api.soccer.schedule.domain.Schedule;
import site.aiion.api.soccer.schedule.domain.ScheduleModel;
import site.aiion.api.soccer.schedule.repository.ScheduleRepository;
import site.aiion.api.soccer.stadium.domain.Stadium;
import site.aiion.api.soccer.stadium.domain.StadiumModel;
import site.aiion.api.soccer.stadium.repository.StadiumRepository;
import site.aiion.api.soccer.team.domain.Team;
import site.aiion.api.soccer.team.domain.TeamModel;
import site.aiion.api.soccer.team.repository.TeamRepository;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;
    private final StadiumRepository stadiumRepository;
    private final ScheduleRepository scheduleRepository;

    @Override
    public Messenger searchByKeyword(String keyword, String domain) {
        // 도메인이 지정된 경우 빈 키워드 허용 (컬럼 정보 조회 또는 전체 조회)
        boolean domainSpecified = domain != null && !domain.isEmpty() && !"K_LEAGUE".equalsIgnoreCase(domain);
        
        // keyword가 null이거나 빈 문자열인지 체크 (null-safe)
        boolean isEmptyKeyword = (keyword == null || keyword.trim().isEmpty());
        
        // 빈 키워드인 경우
        if (isEmptyKeyword) {
            // domain이 지정되지 않았으면 에러
            if (!domainSpecified) {
                return Messenger.builder()
                        .code(400)
                        .message("keyword 파라미터는 필수입니다.")
                        .build();
            }
            // 빈 키워드 + 도메인 지정 시 컬럼 정보 반환
            return getColumnInfo(domain);
        }

        // isEmptyKeyword가 false이므로 keyword는 null이 아님
        String trimmedKeyword = (keyword != null) ? keyword.trim() : "";
        Map<String, Object> results = new HashMap<>();

        // 도메인 필터링: K_LEAGUE 또는 빈 값이면 모든 도메인 검색
        boolean searchAll = domain == null || domain.isEmpty() || "K_LEAGUE".equalsIgnoreCase(domain);

        if (searchAll || "TEAM".equalsIgnoreCase(domain)) {
            List<Team> teams = teamRepository.findByKeyword(trimmedKeyword);
            List<TeamModel> teamModels = teams.stream()
                    .map(this::teamEntityToModel)
                    .collect(Collectors.toList());
            results.put("teams", teamModels);
        }

        if (searchAll || "PLAYER".equalsIgnoreCase(domain)) {
            List<Player> players = playerRepository.findByKeyword(trimmedKeyword);
            List<PlayerModel> playerModels = players.stream()
                    .map(this::playerEntityToModel)
                    .collect(Collectors.toList());
            results.put("players", playerModels);
        }

        if (searchAll || "STADIUM".equalsIgnoreCase(domain)) {
            List<Stadium> stadiums = stadiumRepository.findByKeyword(trimmedKeyword);
            List<StadiumModel> stadiumModels = stadiums.stream()
                    .map(this::stadiumEntityToModel)
                    .collect(Collectors.toList());
            results.put("stadiums", stadiumModels);
        }

        if (searchAll || "SCHEDULE".equalsIgnoreCase(domain)) {
            List<Schedule> schedules = scheduleRepository.findByKeyword(trimmedKeyword);
            List<ScheduleModel> scheduleModels = schedules.stream()
                    .map(this::scheduleEntityToModel)
                    .collect(Collectors.toList());
            results.put("schedules", scheduleModels);
        }

        // 전체 결과 개수 계산
        int totalCount = 0;
        if (results.containsKey("teams")) {
            totalCount += ((List<?>) results.get("teams")).size();
        }
        if (results.containsKey("players")) {
            totalCount += ((List<?>) results.get("players")).size();
        }
        if (results.containsKey("stadiums")) {
            totalCount += ((List<?>) results.get("stadiums")).size();
        }
        if (results.containsKey("schedules")) {
            totalCount += ((List<?>) results.get("schedules")).size();
        }

        return Messenger.builder()
                .code(200)
                .message("검색 완료: 총 " + totalCount + "개 결과")
                .data(results)
                .build();
    }

    private TeamModel teamEntityToModel(Team entity) {
        return TeamModel.builder()
                .id(entity.getId())
                .team_uk(entity.getTeam_uk())
                .region_name(entity.getRegion_name())
                .team_name(entity.getTeam_name())
                .e_team_name(entity.getE_team_name())
                .orig_yyyy(entity.getOrig_yyyy())
                .zip_code1(entity.getZip_code1())
                .zip_code2(entity.getZip_code2())
                .address(entity.getAddress())
                .ddd(entity.getDdd())
                .tel(entity.getTel())
                .fax(entity.getFax())
                .homepage(entity.getHomepage())
                .owner(entity.getOwner())
                .stadium_uk(entity.getStadium_uk())
                .build();
    }

    private PlayerModel playerEntityToModel(Player entity) {
        return PlayerModel.builder()
                .id(entity.getId())
                .player_uk(entity.getPlayer_uk())
                .player_name(entity.getPlayer_name())
                .e_player_name(entity.getE_player_name())
                .nickname(entity.getNickname())
                .join_yyyy(entity.getJoin_yyyy())
                .position(entity.getPosition())
                .back_no(entity.getBack_no())
                .nation(entity.getNation())
                .birth_date(entity.getBirth_date())
                .solar(entity.getSolar())
                .height(entity.getHeight())
                .weight(entity.getWeight())
                .team_uk(entity.getTeam_uk())
                .build();
    }

    private StadiumModel stadiumEntityToModel(Stadium entity) {
        return StadiumModel.builder()
                .id(entity.getId())
                .stadium_uk(entity.getStadium_uk())
                .stadium_name(entity.getStadium_name())
                .hometeam_uk(entity.getHometeam_uk())
                .seat_count(entity.getSeat_count())
                .address(entity.getAddress())
                .ddd(entity.getDdd())
                .tel(entity.getTel())
                .build();
    }

    private ScheduleModel scheduleEntityToModel(Schedule entity) {
        return ScheduleModel.builder()
                .id(entity.getId())
                .sche_date(entity.getSche_date())
                .stadium_uk(entity.getStadium_uk())
                .gubun(entity.getGubun())
                .hometeam_uk(entity.getHometeam_uk())
                .awayteam_uk(entity.getAwayteam_uk())
                .home_score(entity.getHome_score())
                .away_score(entity.getAway_score())
                .build();
    }

    /**
     * 도메인별 컬럼 정보 반환
     */
    private Messenger getColumnInfo(String domain) {
        Map<String, Object> columnInfo = new HashMap<>();
        
        if ("TEAM".equalsIgnoreCase(domain)) {
            List<String> columns = new ArrayList<>();
            columns.add("id");
            columns.add("team_uk");
            columns.add("region_name");
            columns.add("team_name");
            columns.add("e_team_name");
            columns.add("orig_yyyy");
            columns.add("zip_code1");
            columns.add("zip_code2");
            columns.add("address");
            columns.add("ddd");
            columns.add("tel");
            columns.add("fax");
            columns.add("homepage");
            columns.add("owner");
            columns.add("stadium_uk");
            columnInfo.put("table", "teams");
            columnInfo.put("columns", columns);
        } else if ("PLAYER".equalsIgnoreCase(domain)) {
            // playerEntityToModel 메서드의 필드 순서와 동일하게 컬럼 정보 반환
            List<String> columns = new ArrayList<>();
            columns.add("id");                    // entity.getId()
            columns.add("player_uk");             // entity.getPlayer_uk()
            columns.add("player_name");           // entity.getPlayer_name()
            columns.add("e_player_name");        // entity.getE_player_name()
            columns.add("nickname");              // entity.getNickname()
            columns.add("join_yyyy");             // entity.getJoin_yyyy()
            columns.add("position");              // entity.getPosition()
            columns.add("back_no");               // entity.getBack_no()
            columns.add("nation");                // entity.getNation()
            columns.add("birth_date");            // entity.getBirth_date()
            columns.add("solar");                 // entity.getSolar()
            columns.add("height");                // entity.getHeight()
            columns.add("weight");                // entity.getWeight()
            columns.add("team_uk");              // entity.getTeam_uk()
            columnInfo.put("table", "players");
            columnInfo.put("columns", columns);
        } else if ("STADIUM".equalsIgnoreCase(domain)) {
            List<String> columns = new ArrayList<>();
            columns.add("id");
            columns.add("stadium_uk");
            columns.add("stadium_name");
            columns.add("hometeam_uk");
            columns.add("seat_count");
            columns.add("address");
            columns.add("ddd");
            columns.add("tel");
            columnInfo.put("table", "stadiums");
            columnInfo.put("columns", columns);
        } else if ("SCHEDULE".equalsIgnoreCase(domain)) {
            List<String> columns = new ArrayList<>();
            columns.add("id");
            columns.add("sche_date");
            columns.add("stadium_uk");
            columns.add("gubun");
            columns.add("hometeam_uk");
            columns.add("awayteam_uk");
            columns.add("home_score");
            columns.add("away_score");
            columnInfo.put("table", "schedules");
            columnInfo.put("columns", columns);
        } else {
            return Messenger.builder()
                    .code(400)
                    .message("지원하지 않는 도메인입니다: " + domain)
                    .build();
        }
        
        return Messenger.builder()
                .code(200)
                .message(columnInfo.get("table") + " 테이블 컬럼 정보")
                .data(columnInfo)
                .build();
    }
}

