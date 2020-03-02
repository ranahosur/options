package com.bambinos.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class TeacherCenter implements Serializable {

    private String teacherCenterId;
    private String teacherId;
    private String centerId;
    private String createdBy;
    private Timestamp createDate;

    public String getTeacherCenterId() {
        return teacherCenterId;
    }

    public void setTeacherCenterId(String teacherCenterId) {
        this.teacherCenterId = teacherCenterId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getCenterId() {
        return centerId;
    }

    public void setCenterId(String centerId) {
        this.centerId = centerId;
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
