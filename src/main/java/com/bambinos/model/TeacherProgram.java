package com.bambinos.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class TeacherProgram implements Serializable {

    private String teacherProgramId;
    private String teacherId;
    private String programId;
    private Timestamp createDate;
    private String createdBy;

    public String getTeacherProgramId() {
        return teacherProgramId;
    }

    public void setTeacherProgramId(String teacherProgramId) {
        this.teacherProgramId = teacherProgramId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
