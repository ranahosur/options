package com.bambinos.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class Child implements Serializable {

    private String childId;
    private String firstName;
    private String lastName;
    private String sex;
    private Date dob;
    private String bloodGroup;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String pincode;
    private String mobileNumber;
    private String fatherName;
    private String motherName;
    private String fatherMobile;
    private String motherMobile;
    private String fatherOccupation;
    private String fatherCompanyName;
    private String fatherCompanyNumber;
    private String motherOccupation;
    private String motherCompanyName;
    private String motherCompanyNumber;
    private String allergy;
    private String allergyDescription;
    private String dietaryNeed;
    private String foodNeeded;
    private String allowSocialMedia;
    private String cctvAccess;
    private Date joiningDate;
    private String emergencyContactName;
    private String emergencyContactNumber;
    private String emergencyContactAltNumber;
    private String emergencyContactRelation;
    private int paymentFreq;
    private Timestamp createDate;
    private String createdBy;

    public String getChildId() {
        return childId;
    }

    public void setChildId(String childId) {
        this.childId = childId;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getAllergy() {
        return allergy;
    }

    public void setAllergy(String allergy) {
        this.allergy = allergy;
    }

    public String getAllergyDescription() {
        return allergyDescription;
    }

    public void setAllergyDescription(String allergyDescription) {
        this.allergyDescription = allergyDescription;
    }

    public String getDietaryNeed() {
        return dietaryNeed;
    }

    public void setDietaryNeed(String dietaryNeed) {
        this.dietaryNeed = dietaryNeed;
    }

    public String getFoodNeeded() {
        return foodNeeded;
    }

    public void setFoodNeeded(String foodNeeded) {
        this.foodNeeded = foodNeeded;
    }

    public String getAllowSocialMedia() {
        return allowSocialMedia;
    }

    public void setAllowSocialMedia(String allowSocialMedia) {
        this.allowSocialMedia = allowSocialMedia;
    }

    public String getCctvAccess() {
        return cctvAccess;
    }

    public void setCctvAccess(String cctvAccess) {
        this.cctvAccess = cctvAccess;
    }

    public Date getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(Date joiningDate) {
        this.joiningDate = joiningDate;
    }

    public String getEmergencyContactName() {
        return emergencyContactName;
    }

    public void setEmergencyContactName(String emergencyContactName) {
        this.emergencyContactName = emergencyContactName;
    }

    public String getEmergencyContactNumber() {
        return emergencyContactNumber;
    }

    public void setEmergencyContactNumber(String emergencyContactNumber) {
        this.emergencyContactNumber = emergencyContactNumber;
    }

    public String getEmergencyContactAltNumber() {
        return emergencyContactAltNumber;
    }

    public void setEmergencyContactAltNumber(String emergencyContactAltNumber) {
        this.emergencyContactAltNumber = emergencyContactAltNumber;
    }

    public String getEmergencyContactRelation() {
        return emergencyContactRelation;
    }

    public void setEmergencyContactRelation(String emergencyContactRelation) {
        this.emergencyContactRelation = emergencyContactRelation;
    }

    public int getPaymentFreq() {
        return paymentFreq;
    }

    public void setPaymentFreq(int paymentFreq) {
        this.paymentFreq = paymentFreq;
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

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
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

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getFatherMobile() {
        return fatherMobile;
    }

    public void setFatherMobile(String fatherMobile) {
        this.fatherMobile = fatherMobile;
    }

    public String getMotherMobile() {
        return motherMobile;
    }

    public void setMotherMobile(String motherMobile) {
        this.motherMobile = motherMobile;
    }

    public String getFatherOccupation() {
        return fatherOccupation;
    }

    public void setFatherOccupation(String fatherOccupation) {
        this.fatherOccupation = fatherOccupation;
    }

    public String getFatherCompanyName() {
        return fatherCompanyName;
    }

    public void setFatherCompanyName(String fatherCompanyName) {
        this.fatherCompanyName = fatherCompanyName;
    }

    public String getFatherCompanyNumber() {
        return fatherCompanyNumber;
    }

    public void setFatherCompanyNumber(String fatherCompanyNumber) {
        this.fatherCompanyNumber = fatherCompanyNumber;
    }

    public String getMotherOccupation() {
        return motherOccupation;
    }

    public void setMotherOccupation(String motherOccupation) {
        this.motherOccupation = motherOccupation;
    }

    public String getMotherCompanyName() {
        return motherCompanyName;
    }

    public void setMotherCompanyName(String motherCompanyName) {
        this.motherCompanyName = motherCompanyName;
    }

    public String getMotherCompanyNumber() {
        return motherCompanyNumber;
    }

    public void setMotherCompanyNumber(String motherCompanyNumber) {
        this.motherCompanyNumber = motherCompanyNumber;
    }
}
