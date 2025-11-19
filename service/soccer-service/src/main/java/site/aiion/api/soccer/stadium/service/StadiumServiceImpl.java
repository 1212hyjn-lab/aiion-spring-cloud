package site.aiion.api.soccer.stadium.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import site.aiion.api.soccer.common.domain.Messenger;
import site.aiion.api.soccer.stadium.domain.StadiumModel;
import site.aiion.api.soccer.stadium.domain.Stadium;
import site.aiion.api.soccer.stadium.repository.StadiumRepository;

@Service
@RequiredArgsConstructor
public class StadiumServiceImpl implements StadiumService {

    private final StadiumRepository stadiumRepository;

    private StadiumModel entityToModel(Stadium entity) {
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

    private Stadium modelToEntity(StadiumModel model) {
        return Stadium.builder()
                .id(model.id)
                .stadium_uk(model.stadium_uk)
                .stadium_name(model.stadium_name)
                .hometeam_uk(model.hometeam_uk)
                .seat_count(model.seat_count)
                .address(model.address)
                .ddd(model.ddd)
                .tel(model.tel)
                .build();
    }

    @Override
    public Messenger findById(StadiumModel stadiumModel) {
        Optional<Stadium> entity = stadiumRepository.findById(stadiumModel.id);
        if (entity.isPresent()) {
            StadiumModel model = entityToModel(entity.get());
            return Messenger.builder()
                    .code(200)
                    .message("조회 성공")
                    .data(model)
                    .build();
        } else {
            return Messenger.builder()
                    .code(404)
                    .message("경기장을 찾을 수 없습니다.")
                    .build();
        }
    }

    @Override
    public Messenger findAll() {
        List<Stadium> entities = stadiumRepository.findAll();
        List<StadiumModel> modelList = entities.stream()
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
    public Messenger save(StadiumModel stadiumModel) {
        Stadium entity = modelToEntity(stadiumModel);
        Stadium saved = stadiumRepository.save(entity);
        StadiumModel model = entityToModel(saved);
        return Messenger.builder()
                .code(200)
                .message("저장 성공: " + saved.getId())
                .data(model)
                .build();
    }

    @Override
    @Transactional
    public Messenger saveAll(List<StadiumModel> stadiumModelList) {
        List<Stadium> entities = stadiumModelList.stream()
                .map(this::modelToEntity)
                .collect(Collectors.toList());
        
        List<Stadium> saved = stadiumRepository.saveAll(entities);
        return Messenger.builder()
                .code(200)
                .message("일괄 저장 성공: " + saved.size() + "개")
                .build();
    }

    @Override
    @Transactional
    public Messenger update(StadiumModel stadiumModel) {
        Optional<Stadium> optionalEntity = stadiumRepository.findById(stadiumModel.id);
        if (optionalEntity.isPresent()) {
            Stadium existing = optionalEntity.get();
            Stadium updated = Stadium.builder()
                    .id(existing.getId())
                    .stadium_uk(stadiumModel.stadium_uk != null ? stadiumModel.stadium_uk : existing.getStadium_uk())
                    .stadium_name(stadiumModel.stadium_name != null ? stadiumModel.stadium_name : existing.getStadium_name())
                    .hometeam_uk(stadiumModel.hometeam_uk != null ? stadiumModel.hometeam_uk : existing.getHometeam_uk())
                    .seat_count(stadiumModel.seat_count != null ? stadiumModel.seat_count : existing.getSeat_count())
                    .address(stadiumModel.address != null ? stadiumModel.address : existing.getAddress())
                    .ddd(stadiumModel.ddd != null ? stadiumModel.ddd : existing.getDdd())
                    .tel(stadiumModel.tel != null ? stadiumModel.tel : existing.getTel())
                    .schedules(existing.getSchedules())
                    .teams(existing.getTeams())
                    .build();
            
            Stadium saved = stadiumRepository.save(updated);
            StadiumModel model = entityToModel(saved);
            return Messenger.builder()
                    .code(200)
                    .message("수정 성공: " + stadiumModel.id)
                    .data(model)
                    .build();
        } else {
            return Messenger.builder()
                    .code(404)
                    .message("수정할 경기장을 찾을 수 없습니다.")
                    .build();
        }
    }

    @Override
    @Transactional
    public Messenger delete(StadiumModel stadiumModel) {
        Optional<Stadium> optionalEntity = stadiumRepository.findById(stadiumModel.id);
        if (optionalEntity.isPresent()) {
            stadiumRepository.deleteById(stadiumModel.id);
            return Messenger.builder()
                    .code(200)
                    .message("삭제 성공: " + stadiumModel.id)
                    .build();
        } else {
            return Messenger.builder()
                    .code(404)
                    .message("삭제할 경기장을 찾을 수 없습니다.")
                    .build();
        }
    }

}

