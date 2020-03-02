package com.bambinos.dao.impl;

import com.bambinos.dao.TeamMemberDao;
import com.bambinos.model.TeamMember;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TeamMemberDaoImpl extends BaseDaoImpl implements TeamMemberDao {

    private static final Logger logger = Logger.getLogger(TeamDaoImpl.class);
    @Autowired
    DataSource datasource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    public TeamMember createTeamMember(TeamMember teamMember) {
        String sql = "insert into team_member (team_member_id,team_id,user_id,create_date,active) values (?, ? , ?, ? ,?)";

        teamMember.setTeamMemberId(generateId());
        logger.debug("Team Member created with teamId "+teamMember.getTeamMemberId());
        jdbcTemplate.update(sql, new Object[] { teamMember.getTeamMemberId(),teamMember.getTeamId(),teamMember.getUserId(), getCurrentTimeStamp(),
                convertBoolean(teamMember.isActive())});
        return teamMember;
    }

    public List<TeamMember> findTeamMemberByTeamId(String teamId) {
        String sql = "select * from team_member where team_id = '" + teamId + "'";
        return jdbcTemplate.query(sql, new TeamMemberMapper());
    }

    public void updateTeamMember(TeamMember teamMember) {
        String sql = "UPDATE team_member SET active = ?  WHERE team_member_id = ?";
        jdbcTemplate.update(sql,convertBoolean(teamMember.isActive()) , teamMember.getTeamMemberId());

    }

    public void deleteTeamMember(TeamMember teamMember) {
        String sql = "delete from team_member WHERE team_member_id = ?";
        jdbcTemplate.update(sql, teamMember.getTeamMemberId());

    }


    class TeamMemberMapper implements RowMapper<TeamMember> {

        public TeamMember mapRow(ResultSet rs, int arg1) throws SQLException {
            TeamMember user = new TeamMember();
            user.setUserId(rs.getString("user_id"));
            ;
            user.setTeamId(rs.getString("team_id"));
            user.setTeamMemberId(rs.getString("team_member_id"));
            user.setActive("Y".equals(rs.getString("active")));
            user.setCreateDate(rs.getTimestamp("create_date"));
            return user;
        }
    }
}
