package com.bambinos.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class CenterProgram implements Serializable {

    private String centerProgramId;
    private String centerId;
    private String programId;
    private String createdBy;
    private Timestamp createDate;

    public String getCenterProgramId() {
        return centerProgramId;
    }

    public void setCenterProgramId(String centerProgramId) {
        this.centerProgramId = centerProgramId;
    }

    public String getCenterId() {
        return centerId;
    }

    public void setCenterId(String centerId) {
        this.centerId = centerId;
    }

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }
}
