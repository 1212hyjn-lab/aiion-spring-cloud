package site.aiion.api.soccer.team.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import site.aiion.api.soccer.common.domain.Messenger;
import site.aiion.api.soccer.team.domain.TeamModel;
import site.aiion.api.soccer.team.domain.Team;
import site.aiion.api.soccer.team.repository.TeamRepository;
import site.aiion.api.soccer.stadium.repository.StadiumRepository;
import site.aiion.api.soccer.stadium.domain.Stadium;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final StadiumRepository stadiumRepository;

    private TeamModel entityToModel(Team entity) {
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

    private Team modelToEntity(TeamModel model) {
        Stadium stadium = null;
        if (model.stadium_uk != null) {
            stadium = stadiumRepository.findByStadium_uk(model.stadium_uk).orElse(null);
        }
        return Team.builder()
                .id(model.id)
                .team_uk(model.team_uk)
                .region_name(model.region_name)
                .team_name(model.team_name)
                .e_team_name(model.e_team_name)
                .orig_yyyy(model.orig_yyyy)
                .zip_code1(model.zip_code1)
                .zip_code2(model.zip_code2)
                .address(model.address)
                .ddd(model.ddd)
                .tel(model.tel)
                .fax(model.fax)
                .homepage(model.homepage)
                .owner(model.owner)
                .stadium_uk(model.stadium_uk)
                .stadium(stadium)
                .build();
    }

    @Override
    public Messenger findById(TeamModel teamModel) {
        Optional<Team> entity = teamRepository.findById(teamModel.id);
        if (entity.isPresent()) {
            TeamModel model = entityToModel(entity.get());
            return Messenger.builder()
                    .code(200)
                    .message("조회 성공")
                    .data(model)
                    .build();
        } else {
            return Messenger.builder()
                    .code(404)
                    .message("팀을 찾을 수 없습니다.")
                    .build();
        }
    }


    @Override
    public Messenger findAll() {
        List<Team> entities = teamRepository.findAll();
        List<TeamModel> modelList = entities.stream()
                .map(this::entityToModel)
                .collect(Collectors.toList());
        return Messenger.builder()
                .code(200)
                .message("전체 조회 성공: " + modelList.size() + "개")
                .data(modelList)
                .build();
    }

    @Override
    @Transactional
    public Messenger save(TeamModel teamModel) {
        Team entity = modelToEntity(teamModel);
        Team saved = teamRepository.save(entity);
        TeamModel model = entityToModel(saved);
        return Messenger.builder()
                .code(200)
                .message("저장 성공: " + saved.getId())
                .data(model)
                .build();
    }

    @Override
    @Transactional
    public Messenger saveAll(List<TeamModel> teamModelList) {
        List<Team> entities = teamModelList.stream()
                .map(this::modelToEntity)
                .collect(Collectors.toList());
        
        List<Team> saved = teamRepository.saveAll(entities);
        return Messenger.builder()
                .code(200)
                .message("일괄 저장 성공: " + saved.size() + "개")
                .build();
    }

    @Override
    @Transactional
    public Messenger update(TeamModel teamModel) {
        Optional<Team> optionalEntity = teamRepository.findById(teamModel.id);
        if (optionalEntity.isPresent()) {
            Team existing = optionalEntity.get();
            Stadium stadium = teamModel.stadium_uk != null 
                    ? stadiumRepository.findByStadium_uk(teamModel.stadium_uk).orElse(existing.getStadium()) 
                    : existing.getStadium();
            
            Team updated = Team.builder()
                    .id(existing.getId())
                    .team_uk(teamModel.team_uk != null ? teamModel.team_uk : existing.getTeam_uk())
                    .region_name(teamModel.region_name != null ? teamModel.region_name : existing.getRegion_name())
                    .team_name(teamModel.team_name != null ? teamModel.team_name : existing.getTeam_name())
                    .e_team_name(teamModel.e_team_name != null ? teamModel.e_team_name : existing.getE_team_name())
                    .orig_yyyy(teamModel.orig_yyyy != null ? teamModel.orig_yyyy : existing.getOrig_yyyy())
                    .zip_code1(teamModel.zip_code1 != null ? teamModel.zip_code1 : existing.getZip_code1())
                    .zip_code2(teamModel.zip_code2 != null ? teamModel.zip_code2 : existing.getZip_code2())
                    .address(teamModel.address != null ? teamModel.address : existing.getAddress())
                    .ddd(teamModel.ddd != null ? teamModel.ddd : existing.getDdd())
                    .tel(teamModel.tel != null ? teamModel.tel : existing.getTel())
                    .fax(teamModel.fax != null ? teamModel.fax : existing.getFax())
                    .homepage(teamModel.homepage != null ? teamModel.homepage : existing.getHomepage())
                    .owner(teamModel.owner != null ? teamModel.owner : existing.getOwner())
                    .stadium_uk(teamModel.stadium_uk != null ? teamModel.stadium_uk : existing.getStadium_uk())
                    .stadium(stadium)
                    .players(existing.getPlayers())
                    .build();
            
            Team saved = teamRepository.save(updated);
            TeamModel model = entityToModel(saved);
            return Messenger.builder()
                    .code(200)
                    .message("수정 성공: " + teamModel.id)
                    .data(model)
                    .build();
        } else {
            return Messenger.builder()
                    .code(404)
                    .message("수정할 팀을 찾을 수 없습니다.")
                    .build();
        }
    }

    @Override
    @Transactional
    public Messenger delete(TeamModel teamModel) {
        Optional<Team> optionalEntity = teamRepository.findById(teamModel.id);
        if (optionalEntity.isPresent()) {
            teamRepository.deleteById(teamModel.id);
            return Messenger.builder()
                    .code(200)
                    .message("삭제 성공: " + teamModel.id)
                    .build();
        } else {
            return Messenger.builder()
                    .code(404)
                    .message("삭제할 팀을 찾을 수 없습니다.")
                    .build();
        }
    }

}

