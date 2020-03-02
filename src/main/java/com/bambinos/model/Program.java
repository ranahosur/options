package com.bambinos.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class Program implements Serializable {

    private String programId;
    private String programName;
    private String programCode;
    private String description;
    private BigDecimal price;
    private int capacity;
    private int assessmentPeriod;
    private String createdBy;
    private Timestamp createDate;

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getProgramCode() {
        return programCode;
    }

    public void setProgramCode(String programCode) {
        this.programCode = programCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getAssessmentPeriod() {
        return assessmentPeriod;
    }

    public void setAssessmentPeriod(int assessmentPeriod) {
        this.assessmentPeriod = assessmentPeriod;
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
