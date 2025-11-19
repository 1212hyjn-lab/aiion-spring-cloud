package site.aiion.api.soccer.team.service;

import java.util.List;
import site.aiion.api.soccer.team.domain.TeamModel;
import site.aiion.api.soccer.common.domain.Messenger;

public interface TeamService {
    public Messenger findById(TeamModel teamModel);
    public Messenger findAll();
    public Messenger save(TeamModel teamModel);
    public Messenger saveAll(List<TeamModel> teamModelList);
    public Messenger update(TeamModel teamModel);
    public Messenger delete(TeamModel teamModel);
}
