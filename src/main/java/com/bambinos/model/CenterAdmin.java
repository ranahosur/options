package com.bambinos.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class CenterAdmin implements Serializable {

    private String centerAdminId;
    private String adminId;
    private String centerId;
    private String roleType;
    private Timestamp createDate;
    private String active;
    private String createdBy;

    public String getCenterAdminId() {
        return centerAdminId;
    }

    public void setCenterAdminId(String centerAdminId) {
        this.centerAdminId = centerAdminId;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getCenterId() {
        return centerId;
    }

    public void setCenterId(String centerId) {
        this.centerId = centerId;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
