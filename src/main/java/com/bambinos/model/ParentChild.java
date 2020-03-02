package com.bambinos.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class ParentChild implements Serializable {

    private String parentChildId;
    private String parentId;
    private String childId;
    private String createdBy;
    private Timestamp createDate;

    public String getParentChildId() {
        return parentChildId;
    }

    public void setParentChildId(String parentChildId) {
        this.parentChildId = parentChildId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getChildId() {
        return childId;
    }

    public void setChildId(String childId) {
        this.childId = childId;
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
