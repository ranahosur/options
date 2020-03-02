package com.bambinos.model;

import java.sql.Timestamp;
import java.util.List;

public class User {

  private String userId;
  private String username;
  private String password;
  private String firstName;
  private String lastName;
  private String email;
  private String addressLine1;
  private String addressLine2;
  private String city;
  private String state;
  private String zip;
  private String phoneNumber;
  private Timestamp createDate;
  private String countryCode;
  private String countryName;
  private String verificationToken;
  private String active;

  private String newpassTxt;
  private String repassTxt;

  private UserRole userRole;

  private List<AdminPrivilege> adminPrivileges;

  private Participant participant;


  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
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

  public String getAddressLine1() {
    return addressLine1;
  }

  public void setAddressLine1(String addressLine1) {
    this.addressLine1 = addressLine1;
  }

  public String getAddressLine2() {
    return addressLine2;
  }

  public void setAddressLine2(String addressLine2) {
    this.addressLine2 = addressLine2;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getZip() {
    return zip;
  }

  public void setZip(String zip) {
    this.zip = zip;
  }

  public String getCountryCode() {
    return countryCode;
  }

  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }

  public String getCountryName() {
    return countryName;
  }

  public void setCountryName(String countryName) {
    this.countryName = countryName;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

    public String getVerificationToken() {
        return verificationToken;
    }

    public void setVerificationToken(String verificationToken) {
        this.verificationToken = verificationToken;
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

  public String getNewpassTxt() {
    return newpassTxt;
  }

  public void setNewpassTxt(String newpassTxt) {
    this.newpassTxt = newpassTxt;
  }

  public String getRepassTxt() {
    return repassTxt;
  }

  public void setRepassTxt(String repassTxt) {
    this.repassTxt = repassTxt;
  }

  public UserRole getUserRole() {
    return userRole;
  }

  public void setUserRole(UserRole userRole) {
    this.userRole = userRole;
  }

    public List<AdminPrivilege> getAdminPrivileges() {
        return adminPrivileges;
    }

    public void setAdminPrivileges(List<AdminPrivilege> adminPrivileges) {
        this.adminPrivileges = adminPrivileges;
    }


  public Participant getParticipant() {
    return participant;
  }

  public void setParticipant(Participant participant) {
    this.participant = participant;
  }
}
