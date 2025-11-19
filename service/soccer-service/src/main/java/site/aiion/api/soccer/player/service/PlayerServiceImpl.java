package site.aiion.api.soccer.player.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import site.aiion.api.soccer.common.domain.Messenger;
import site.aiion.api.soccer.player.domain.PlayerModel;
import site.aiion.api.soccer.player.domain.Player;
import site.aiion.api.soccer.player.repository.PlayerRepository;
import site.aiion.api.soccer.team.repository.TeamRepository;
import site.aiion.api.soccer.team.domain.Team;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    private PlayerModel entityToDTO(Player entity) {
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

    private Player dtoToEntity(PlayerModel dto) {
        Team team = null;
        if (dto.team_uk != null) {
            team = teamRepository.findByTeam_uk(dto.team_uk).orElse(null);
        }
        return Player.builder()
                .id(dto.id)
                .player_uk(dto.player_uk)
                .player_name(dto.player_name)
                .e_player_name(dto.e_player_name)
                .nickname(dto.nickname)
                .join_yyyy(dto.join_yyyy)
                .position(dto.position)
                .back_no(dto.back_no)
                .nation(dto.nation)
                .birth_date(dto.birth_date)
                .solar(dto.solar)
                .height(dto.height)
                .weight(dto.weight)
                .team_uk(dto.team_uk)
                .team(team)
                .build();
    }

    @Override
    public Messenger findById(PlayerModel playerModel) {
        Optional<Player> entity = playerRepository.findById(playerModel.id);
        if (entity.isPresent()) {
            PlayerModel dto = entityToDTO(entity.get());
            return Messenger.builder()
                    .code(200)
                    .message("조회 성공")
                    .data(dto)
                    .build();
        } else {
            return Messenger.builder()
                    .code(404)
                    .message("선수를 찾을 수 없습니다.")
                    .build();
        }
    }

    @Override
    public Messenger findAll() {
        List<Player> entities = playerRepository.findAll();
        List<PlayerModel> dtoList = entities.stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList());
        return Messenger.builder()
                .code(200)
                .message("전체 조회 성공: " + dtoList.size() + "개")
                .data(dtoList)
                .build();
    }

    @Override
    @Transactional
    public Messenger save(PlayerModel PlayerModel) {
        Player entity = dtoToEntity(PlayerModel);
        Player saved = playerRepository.save(entity);
        PlayerModel dto = entityToDTO(saved);
        return Messenger.builder()
                .code(200)
                .message("저장 성공: " + saved.getId())
                .data(dto)
                .build();
    }

    @Override
    @Transactional
    public Messenger saveAll(List<PlayerModel> PlayerModelList) {
        List<Player> entities = PlayerModelList.stream()
                .map(this::dtoToEntity)
                .collect(Collectors.toList());
        
        List<Player> saved = playerRepository.saveAll(entities);
        return Messenger.builder()
                .code(200)
                .message("일괄 저장 성공: " + saved.size() + "개")
                .build();
    }

    @Override
    @Transactional
    public Messenger update(PlayerModel PlayerModel) {
        Optional<Player> optionalEntity = playerRepository.findById(PlayerModel.id);
        if (optionalEntity.isPresent()) {
            Player existing = optionalEntity.get();
            Team team = PlayerModel.team_uk != null 
                    ? teamRepository.findByTeam_uk(PlayerModel.team_uk).orElse(existing.getTeam()) 
                    : existing.getTeam();
            
            Player updated = Player.builder()
                    .id(existing.getId())
                    .player_uk(PlayerModel.player_uk != null ? PlayerModel.player_uk : existing.getPlayer_uk())
                    .player_name(PlayerModel.player_name != null ? PlayerModel.player_name : existing.getPlayer_name())
                    .e_player_name(PlayerModel.e_player_name != null ? PlayerModel.e_player_name : existing.getE_player_name())
                    .nickname(PlayerModel.nickname != null ? PlayerModel.nickname : existing.getNickname())
                    .join_yyyy(PlayerModel.join_yyyy != null ? PlayerModel.join_yyyy : existing.getJoin_yyyy())
                    .position(PlayerModel.position != null ? PlayerModel.position : existing.getPosition())
                    .back_no(PlayerModel.back_no != null ? PlayerModel.back_no : existing.getBack_no())
                    .nation(PlayerModel.nation != null ? PlayerModel.nation : existing.getNation())
                    .birth_date(PlayerModel.birth_date != null ? PlayerModel.birth_date : existing.getBirth_date())
                    .solar(PlayerModel.solar != null ? PlayerModel.solar : existing.getSolar())
                    .height(PlayerModel.height != null ? PlayerModel.height : existing.getHeight())
                    .weight(PlayerModel.weight != null ? PlayerModel.weight : existing.getWeight())
                    .team_uk(PlayerModel.team_uk != null ? PlayerModel.team_uk : existing.getTeam_uk())
                    .team(team)
                    .build();
            
            Player saved = playerRepository.save(updated);
            PlayerModel dto = entityToDTO(saved);
            return Messenger.builder()
                    .code(200)
                    .message("수정 성공: " + PlayerModel.id)
                    .data(dto)
                    .build();
        } else {
            return Messenger.builder()
                    .code(404)
                    .message("수정할 선수를 찾을 수 없습니다.")
                    .build();
        }
    }

    @Override
    @Transactional
    public Messenger delete(PlayerModel PlayerModel) {
        Optional<Player> optionalEntity = playerRepository.findById(PlayerModel.id);
        if (optionalEntity.isPresent()) {
            playerRepository.deleteById(PlayerModel.id);
            return Messenger.builder()
                    .code(200)
                    .message("삭제 성공: " + PlayerModel.id)
                    .build();
        } else {
            return Messenger.builder()
                    .code(404)
                    .message("삭제할 선수를 찾을 수 없습니다.")
                    .build();
        }
    }

}

