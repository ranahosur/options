package com.bambinos.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Login implements Serializable {

  private String loginId;
  private String username;
  private String password;
  private String roleType;
  private String loginType; //indicates if mobile no is used or email is used as username
  private String parentId;
  private String parentTable;
  private String active;
  private Timestamp createDate;

  public String getLoginId() {
    return loginId;
  }

  public void setLoginId(String loginId) {
    this.loginId = loginId;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getRoleType() {
    return roleType;
  }

  public void setRoleType(String roleType) {
    this.roleType = roleType;
  }

  public String getLoginType() {
    return loginType;
  }

  public void setLoginType(String loginType) {
    this.loginType = loginType;
  }

  public String getParentId() {
    return parentId;
  }

  public void setParentId(String parentId) {
    this.parentId = parentId;
  }

  public String getParentTable() {
    return parentTable;
  }

  public void setParentTable(String parentTable) {
    this.parentTable = parentTable;
  }

  public String getActive() {
    return active;
  }

  public void setActive(String active) {
    this.active = active;
  }

  public Timestamp getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Timestamp createDate) {
    this.createDate = createDate;
  }
}
