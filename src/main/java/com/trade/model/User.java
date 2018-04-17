package com.trade.model;

import java.sql.Date;
import java.util.List;

public class User {

  private String userId;
  private String username;
  private String password;
  private String firstName;
  private String lastName;
  private String email;
  private String houseNo;
  private String street;
  private String location;
  private String city;
  private String state;
  private String pinCode;
  private String residencePhone;
  private String mobilePhone;
  private String officePhone;
  private String day;
  private String month;
  private String year;
  private Date dateOfBirth;
  private String gender;
  private String category;
  private String newpassTxt;
  private String repassTxt;

  List<UserPaymentService> userPaymentServiceList;


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

  public String getHouseNo() {
    return houseNo;
  }

  public void setHouseNo(String houseNo) {
    this.houseNo = houseNo;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
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

  public String getPinCode() {
    return pinCode;
  }

  public void setPinCode(String pinCode) {
    this.pinCode = pinCode;
  }

  public String getResidencePhone() {
    return residencePhone;
  }

  public void setResidencePhone(String residencePhone) {
    this.residencePhone = residencePhone;
  }

  public String getMobilePhone() {
    return mobilePhone;
  }

  public void setMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
  }

  public String getOfficePhone() {
    return officePhone;
  }

  public void setOfficePhone(String officePhone) {
    this.officePhone = officePhone;
  }

  public String getDay() {
    return day;
  }

  public void setDay(String day) {
    this.day = day;
  }

  public String getMonth() {
    return month;
  }

  public void setMonth(String month) {
    this.month = month;
  }

  public String getYear() {
    return year;
  }

  public void setYear(String year) {
    this.year = year;
  }

  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(Date dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
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

  public List<UserPaymentService> getUserPaymentServiceList() {
    return userPaymentServiceList;
  }

  public void setUserPaymentServiceList(List<UserPaymentService> userPaymentServiceList) {
    this.userPaymentServiceList = userPaymentServiceList;
  }
}
