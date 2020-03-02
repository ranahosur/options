package com.bambinos.model;

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

    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private User user;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
