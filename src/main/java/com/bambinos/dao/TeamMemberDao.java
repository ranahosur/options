package com.bambinos.dao;

import com.bambinos.model.TeamMember;

import java.util.List;

public interface TeamMemberDao {

    TeamMember createTeamMember(TeamMember teamMember);

    List<TeamMember> findTeamMemberByTeamId(String teamId);

    void updateTeamMember(TeamMember teamMember);

    void deleteTeamMember(TeamMember teamMember);

}
