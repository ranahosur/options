package com.bambinos.dao;

import com.bambinos.model.Team;

import java.util.List;

public interface TeamDao {

    Team createTeam(Team team);

    public List<Team> findTeamByAdminId(String adminId);

    public void updateTeam(Team team);

    public void deleteTeam(Team team);

    public Team findTeamByTeamId(String  teamId);
}
