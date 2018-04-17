package com.trade.model;

import java.sql.Date;
import java.sql.Timestamp;

public class AdminPrivilege {

    private String adminPrivilegeId;
    private String userId;
    private int maxTeamCount;
    private int maxTeamMemberCount;
    private Date trialPeriodEndDate;
    private Timestamp createDate;
    private boolean active;

    public String getAdminPrivilegeId() {
        return adminPrivilegeId;
    }

    public void setAdminPrivilegeId(String adminPrivilegeId) {
        this.adminPrivilegeId = adminPrivilegeId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getMaxTeamCount() {
        return maxTeamCount;
    }

    public void setMaxTeamCount(int maxTeamCount) {
        this.maxTeamCount = maxTeamCount;
    }

    public int getMaxTeamMemberCount() {
        return maxTeamMemberCount;
    }

    public void setMaxTeamMemberCount(int maxTeamMemberCount) {
        this.maxTeamMemberCount = maxTeamMemberCount;
    }

    public Date getTrialPeriodEndDate() {
        return trialPeriodEndDate;
    }

    public void setTrialPeriodEndDate(Date trialPeriodEndDate) {
        this.trialPeriodEndDate = trialPeriodEndDate;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
