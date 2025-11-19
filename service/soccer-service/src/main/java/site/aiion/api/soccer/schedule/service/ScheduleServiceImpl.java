package site.aiion.api.soccer.schedule.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import site.aiion.api.soccer.common.domain.Messenger;
import site.aiion.api.soccer.schedule.domain.ScheduleModel;
import site.aiion.api.soccer.schedule.domain.Schedule;
import site.aiion.api.soccer.schedule.repository.ScheduleRepository;
import site.aiion.api.soccer.stadium.repository.StadiumRepository;
import site.aiion.api.soccer.team.repository.TeamRepository;
import site.aiion.api.soccer.stadium.domain.Stadium;
import site.aiion.api.soccer.team.domain.Team;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final StadiumRepository stadiumRepository;
    private final TeamRepository teamRepository;

    private ScheduleModel entityToModel(Schedule entity) {
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

    private Schedule modelToEntity(ScheduleModel model) {
        Stadium stadium = null;
        Team hometeam = null;
        Team awayteam = null;
        
        if (model.stadium_uk != null) {
            stadium = stadiumRepository.findByStadium_uk(model.stadium_uk).orElse(null);
        }
        if (model.hometeam_uk != null) {
            hometeam = teamRepository.findByTeam_uk(model.hometeam_uk).orElse(null);
        }
        if (model.awayteam_uk != null) {
            awayteam = teamRepository.findByTeam_uk(model.awayteam_uk).orElse(null);
        }
        
        return Schedule.builder()
                .id(model.id)
                .sche_date(model.sche_date)
                .stadium_uk(model.stadium_uk)
                .gubun(model.gubun)
                .hometeam_uk(model.hometeam_uk)
                .awayteam_uk(model.awayteam_uk)
                .home_score(model.home_score)
                .away_score(model.away_score)
                .stadium(stadium)
                .hometeam(hometeam)
                .awayteam(awayteam)
                .build();
    }

    @Override
    public Messenger findById(ScheduleModel scheduleModel) {
        Optional<Schedule> entity = scheduleRepository.findById(scheduleModel.id);
        if (entity.isPresent()) {
            ScheduleModel model = entityToModel(entity.get());
            return Messenger.builder()
                    .code(200)
                    .message("조회 성공")
                    .data(model)
                    .build();
        } else {
            return Messenger.builder()
                    .code(404)
                    .message("일정을 찾을 수 없습니다.")
                    .build();
        }
    }

    @Override
    public Messenger findAll() {
        List<Schedule> entities = scheduleRepository.findAll();
        List<ScheduleModel> modelList = entities.stream()
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
    public Messenger save(ScheduleModel scheduleModel) {
        Schedule entity = modelToEntity(scheduleModel);
        Schedule saved = scheduleRepository.save(entity);
        ScheduleModel model = entityToModel(saved);
        return Messenger.builder()
                .code(200)
                .message("저장 성공: " + saved.getId())
                .data(model)
                .build();
    }

    @Override
    @Transactional
    public Messenger saveAll(List<ScheduleModel> scheduleModelList) {
        List<Schedule> entities = scheduleModelList.stream()
                .map(model -> {
                    Stadium stadium = null;
                    Team hometeam = null;
                    Team awayteam = null;
                    
                    if (model.stadium_uk != null) {
                        stadium = stadiumRepository.findByStadium_uk(model.stadium_uk).orElse(null);
                    }
                    if (model.hometeam_uk != null) {
                        hometeam = teamRepository.findByTeam_uk(model.hometeam_uk).orElse(null);
                    }
                    if (model.awayteam_uk != null) {
                        awayteam = teamRepository.findByTeam_uk(model.awayteam_uk).orElse(null);
                    }
                    
                    return Schedule.builder()
                            .id(model.id)
                            .sche_date(model.sche_date)
                            .stadium_uk(model.stadium_uk)
                            .gubun(model.gubun)
                            .hometeam_uk(model.hometeam_uk)
                            .awayteam_uk(model.awayteam_uk)
                            .home_score(model.home_score)
                            .away_score(model.away_score)
                            .stadium(stadium)
                            .hometeam(hometeam)
                            .awayteam(awayteam)
                            .build();
                })
                .collect(Collectors.toList());
        
        List<Schedule> saved = scheduleRepository.saveAll(entities);
        List<ScheduleModel> modelList = saved.stream()
                .map(this::entityToModel)
                .collect(Collectors.toList());
        return Messenger.builder()
                .code(200)
                .message("일괄 저장 성공: " + modelList.size() + "개")
                .data(modelList)
                .build();
    }

    @Override
    @Transactional
    public Messenger update(ScheduleModel scheduleModel) {
        Optional<Schedule> optionalEntity = scheduleRepository.findById(scheduleModel.id);
        if (optionalEntity.isPresent()) {
            Schedule existing = optionalEntity.get();
            
            Stadium stadium = scheduleModel.stadium_uk != null 
                    ? stadiumRepository.findByStadium_uk(scheduleModel.stadium_uk).orElse(existing.getStadium()) 
                    : existing.getStadium();
            Team hometeam = scheduleModel.hometeam_uk != null 
                    ? teamRepository.findByTeam_uk(scheduleModel.hometeam_uk).orElse(existing.getHometeam()) 
                    : existing.getHometeam();
            Team awayteam = scheduleModel.awayteam_uk != null 
                    ? teamRepository.findByTeam_uk(scheduleModel.awayteam_uk).orElse(existing.getAwayteam()) 
                    : existing.getAwayteam();
            
            Schedule updated = Schedule.builder()
                    .id(existing.getId())
                    .sche_date(scheduleModel.sche_date != null ? scheduleModel.sche_date : existing.getSche_date())
                    .stadium_uk(scheduleModel.stadium_uk != null ? scheduleModel.stadium_uk : existing.getStadium_uk())
                    .gubun(scheduleModel.gubun != null ? scheduleModel.gubun : existing.getGubun())
                    .hometeam_uk(scheduleModel.hometeam_uk != null ? scheduleModel.hometeam_uk : existing.getHometeam_uk())
                    .awayteam_uk(scheduleModel.awayteam_uk != null ? scheduleModel.awayteam_uk : existing.getAwayteam_uk())
                    .home_score(scheduleModel.home_score != null ? scheduleModel.home_score : existing.getHome_score())
                    .away_score(scheduleModel.away_score != null ? scheduleModel.away_score : existing.getAway_score())
                    .stadium(stadium)
                    .hometeam(hometeam)
                    .awayteam(awayteam)
                    .build();
            
            Schedule saved = scheduleRepository.save(updated);
            ScheduleModel model = entityToModel(saved);
            return Messenger.builder()
                    .code(200)
                    .message("수정 성공: " + scheduleModel.id)
                    .data(model)
                    .build();
        } else {
            return Messenger.builder()
                    .code(404)
                    .message("수정할 일정을 찾을 수 없습니다.")
                    .build();
        }
    }

    @Override
    @Transactional
    public Messenger delete(ScheduleModel scheduleModel) {
        Optional<Schedule> optionalEntity = scheduleRepository.findById(scheduleModel.id);
        if (optionalEntity.isPresent()) {
            scheduleRepository.deleteById(scheduleModel.id);
            return Messenger.builder()
                    .code(200)
                    .message("삭제 성공: " + scheduleModel.id)
                    .build();
        } else {
            return Messenger.builder()
                    .code(404)
                    .message("삭제할 일정을 찾을 수 없습니다.")
                    .build();
        }
    }

}

