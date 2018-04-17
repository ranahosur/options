package com.trade.dao;

import com.trade.model.AdminPrivilege;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AdminPrivilegeDaoImpl extends BaseDaoImpl implements AdminPrivilegeDao {

    private static final Logger logger = Logger.getLogger(AdminPrivilegeDaoImpl.class);
    @Autowired
    DataSource datasource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    public AdminPrivilege createAdminPrivilege(AdminPrivilege adminPrivilege) {
        String sql = "insert into admin_privilege (admin_privilege_id,user_id,max_team_count,max_team_member_count,trial_period_end_date, create_date,active) values (?, ? , ?, ? ,?, ? , ?)";

        adminPrivilege.setAdminPrivilegeId(generateId());
        logger.debug("Admin Privilege created with admin priv id "+adminPrivilege.getAdminPrivilegeId());
        jdbcTemplate.update(sql, new Object[] { adminPrivilege.getAdminPrivilegeId(),adminPrivilege.getUserId(),adminPrivilege.getMaxTeamCount(), adminPrivilege.getMaxTeamMemberCount(), adminPrivilege.getTrialPeriodEndDate(),  getCurrentTimeStamp(),
                convertBoolean(adminPrivilege.isActive())});
        return adminPrivilege;
    }

    public AdminPrivilege findAdminPrivilegeByUserId(String userId) {
        String sql = "select * from admin_privilege where user_id = '" + userId + "'";
        List<AdminPrivilege> adminPrivileges = jdbcTemplate.query(sql, new AdminPrivilegeMapper());
        if(adminPrivileges != null && adminPrivileges.size() > 0){
            return adminPrivileges.get(0);
        }
        else{
            return null;
        }
    }

    public void updateAdminPrivilege(AdminPrivilege teamMember) {
        String sql = "UPDATE admin_privilege SET active = ?, max_team_count = ? , max_team_member_count = ?, trial_period_end_date = ?  WHERE admin_privilege_id = ?";
        jdbcTemplate.update(sql,convertBoolean(teamMember.isActive()) ,teamMember.getMaxTeamCount(),teamMember.getMaxTeamMemberCount(),teamMember.getTrialPeriodEndDate(), teamMember.getAdminPrivilegeId());

    }

    public void deleteAdminPrivilege(AdminPrivilege teamMember) {
        String sql = "delete from admin_privilege WHERE admin_privilege_id = ?";
        jdbcTemplate.update(sql, teamMember.getAdminPrivilegeId());

    }


    class AdminPrivilegeMapper implements RowMapper<AdminPrivilege> {

        public AdminPrivilege mapRow(ResultSet rs, int arg1) throws SQLException {
            AdminPrivilege user = new AdminPrivilege();
            user.setUserId(rs.getString("user_id"));

            user.setAdminPrivilegeId(rs.getString("admin_privilege_id"));
            user.setMaxTeamMemberCount(rs.getInt("max_team_member_count"));
            user.setMaxTeamCount(rs.getInt("max_team_count"));
            user.setTrialPeriodEndDate(rs.getDate("trial_period_end_date"));
            user.setActive("Y".equals(rs.getString("active")));
            user.setCreateDate(rs.getTimestamp("create_date"));
            return user;
        }
    }
}
