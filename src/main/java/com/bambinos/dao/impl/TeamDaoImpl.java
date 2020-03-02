package com.bambinos.dao.impl;

import com.bambinos.dao.TeamDao;
import com.bambinos.model.Team;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TeamDaoImpl extends BaseDaoImpl implements TeamDao {

    private static final Logger logger = Logger.getLogger(TeamDaoImpl.class);
    @Autowired
    DataSource datasource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    public Team createTeam(Team team) {
        String sql = "insert into team (team_id,team_name,admin_id,user_id,create_date,active) values (?, ? , ?, ?, ? ,?)";

        team.setTeamId(generateId());
        logger.debug("Team created with teamId "+team.getTeamId());
        jdbcTemplate.update(sql, new Object[] { team.getTeamId(),team.getTeamName(), team.getAdminId(), team.getUserId(), getCurrentTimeStamp(),
                convertBoolean(team.isActive())});
        return team;

    }

    public List<Team> findTeamByAdminId(String adminId) {
        String sql = "select * from team where admin_id = '" + adminId + "'";
        return jdbcTemplate.query(sql, new TeamMapper());
    }

    public Team findTeamByTeamId(String teamId) {
        String sql = "select * from team where team_id = '" + teamId + "'";
        List<Team> teams =  jdbcTemplate.query(sql, new TeamMapper());
        return teams.size() > 0 ? teams.get(0) : null;
    }

    public void updateTeam(Team team) {
        String sql = "UPDATE team SET active = ?, team_name = ?  WHERE team_id = ?";
        jdbcTemplate.update(sql,convertBoolean(team.isActive()) , team.getTeamName(), team.getTeamId());

    }

    public void deleteTeam(Team teamMember) {
        String sql = "delete from team WHERE team_id = ?";
        jdbcTemplate.update(sql, teamMember.getTeamId());

    }


    class TeamMapper implements RowMapper<Team> {

        public Team mapRow(ResultSet rs, int arg1) throws SQLException {
            Team user = new Team();
            user.setAdminId(rs.getString("admin_id"));
            user.setUserId(rs.getString("user_id"));
            user.setTeamId(rs.getString("team_id"));
            user.setTeamName(rs.getString("team_name"));
            user.setActive("Y".equals(rs.getString("active")));
            user.setCreateDate(rs.getTimestamp("create_date"));
            return user;
        }
    }
}
