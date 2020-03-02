package com.bambinos.dao.impl;

import com.bambinos.dao.ChildDAO;
import com.bambinos.model.Child;
import com.bambinos.model.Child;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ChildDAOImpl extends BaseDaoImpl implements ChildDAO {

    private static final Logger logger = Logger.getLogger(ChildDAOImpl.class);

    @Autowired
    DataSource datasource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Child createChild(Child child) {
        String sql = "insert into child (child_id ,first_name ,last_name  ,sex ,dob ,address_line1 ,address_line2 ,city ,state ,pincode ," +
                " mobile_number ,father_name ,mother_name ,father_mobile ,mother_mobile ,father_occupation ,father_company_name ,father_company_phone ," +
                " mother_occupation ,mother_company_name ,mother_company_phone ,blood_group ,allergy ,allergy_description ,dietary_need,food_needed ," +
                " allow_social_media ,cctv_access ,joining_date ,emergency_contact_name ,emergency_contact_number,emergency_contact_alt_number,emergency_contact_relation ," +
                " payment_freq ,created_by ) " +
                " values( ?, ?, ?, ?,?, ?, ?, ?,  ?, ?, ?, ?,?, ?, ?, ?,?, ?, ?, ?,?, ?, ?, ?,  ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?)";

        child.setChildId(generateId());
        logger.debug("child created with id "+ child.getChildId());
        jdbcTemplate.update(sql,  child.getChildId(),child.getFirstName(),child.getLastName(),child.getSex(), child.getDob(),
                child.getAddress1(),child.getAddress2(), child.getCity(),child.getState(),child.getPincode(),child.getMobileNumber(),
                child.getFatherName(),child.getMotherName(),child.getFatherMobile(),child.getMotherMobile(),child.getFatherOccupation(),child.getFatherCompanyName(),child.getFatherCompanyNumber(),
                child.getMotherOccupation(),child.getMotherCompanyName(),child.getMotherCompanyNumber(),child.getBloodGroup(),child.getAllergy(),child.getAllergyDescription(),child.getDietaryNeed(),child.getFoodNeeded(),
                child.getAllowSocialMedia(),child.getCctvAccess(),child.getJoiningDate(),child.getEmergencyContactName(),child.getEmergencyContactNumber(),child.getEmergencyContactAltNumber(),child.getEmergencyContactRelation(),
                child.getPaymentFreq(),child.getCreatedBy());
        return child;
    }

    @Override
    public Child updateChild(Child child) {
    String sql = "update child  set first_name  = ? , last_name   = ? , sex  = ? , dob  = ? , address_line1  = ? , address_line2  = ? , city  = ? , state  = ? , pincode  = ? , " +
            "mobile_number  = ? , father_name  = ? , mother_name  = ? , father_mobile  = ? , mother_mobile  = ? , father_occupation  = ? , father_company_name  = ? , father_company_phone  = ? , " +
            "mother_occupation  = ? , mother_company_name  = ? , mother_company_phone  = ? , blood_group  = ? , allergy  = ? , allergy_description  = ? , dietary_need = ? , food_needed  = ? , " +
            "allow_social_media  = ? , cctv_access  = ? , joining_date  = ? , emergency_contact_name  = ? , emergency_contact_number = ? , emergency_contact_alt_number = ? , emergency_contact_relation  = ? , " +
            "payment_freq  = ? , create_date  = ? , created_by  = ? where  child_id = ?)";

        child.setChildId(generateId());
        logger.debug("child created with id "+ child.getChildId());
        jdbcTemplate.update(sql,  child.getFirstName(),child.getLastName(),child.getSex(), child.getDob(),
            child.getAddress1(),child.getAddress2(), child.getCity(),child.getState(),child.getPincode(),child.getMobileNumber(),
            child.getFatherName(),child.getMotherName(),child.getFatherMobile(),child.getMotherMobile(),child.getFatherOccupation(),child.getFatherCompanyName(),child.getFatherCompanyNumber(),
            child.getMotherOccupation(),child.getMotherCompanyName(),child.getMotherCompanyNumber(),child.getBloodGroup(),child.getAllergy(),child.getAllergyDescription(),child.getDietaryNeed(),child.getFoodNeeded(),
            child.getAllowSocialMedia(),child.getCctvAccess(),child.getJoiningDate(),child.getEmergencyContactName(),child.getEmergencyContactNumber(),child.getEmergencyContactAltNumber(),child.getEmergencyContactRelation(),
            child.getPaymentFreq(),child.getCreatedBy(),child.getChildId());
        return child;
    }

    @Override
    public List<Child> findChildByMobile(String mobile) {
        String sql = "select * from child where father_mobile = '"+mobile+"' or mother_mobile = '"+mobile+"'";

        List<Child> childList = jdbcTemplate.query(sql, new ChildDAOImpl.ChildMapper());
        return childList;
    }

    class ChildMapper implements RowMapper<Child> {

        public Child mapRow(ResultSet rs, int arg1) throws SQLException {
            Child child = new Child();
            child.setChildId(rs.getString("child_id"));
            child.setFirstName(rs.getString("first_name"));
            child.setLastName(rs.getString("last_name"));
            child.setSex(rs.getString("sex"));
            child.setDob(rs.getDate("dob"));
            child.setAddress1(rs.getString("address_line1"));
            child.setAddress2(rs.getString("address_line2"));
            child.setCity(rs.getString("city"));
            child.setState(rs.getString("state"));
            child.setPincode(rs.getString("pincode"));
            child.setMobileNumber(rs.getString("mobile_number"));
            child.setFatherName(rs.getString("father_name"));
            child.setMotherName(rs.getString("mother_name"));
            child.setFatherMobile(rs.getString("father_mobile"));
            child.setMotherMobile(rs.getString("mother_mobile"));
            child.setFatherOccupation(rs.getString("father_occupation"));
            child.setFatherCompanyName(rs.getString("father_company_name"));
            child.setFatherCompanyNumber(rs.getString("father_company_phone"));
            child.setMotherOccupation(rs.getString("mother_occupation"));
            child.setMotherCompanyName(rs.getString("mother_company_name"));
            child.setMotherCompanyNumber(rs.getString("mother_company_phone"));
            child.setBloodGroup(rs.getString("blood_group"));
            child.setAllergy(rs.getString("allergy"));
            child.setAllergyDescription(rs.getString("allergy_description"));
            child.setDietaryNeed(rs.getString("dietary_need"));
            child.setFoodNeeded(rs.getString("food_needed"));
            child.setAllowSocialMedia(rs.getString("allow_social_media"));
            child.setCctvAccess(rs.getString("cctv_access"));
            child.setJoiningDate(rs.getDate("joining_date"));
            child.setEmergencyContactName(rs.getString("emergency_contact_name"));
            child.setEmergencyContactNumber(rs.getString("emergency_contact_number"));
            child.setEmergencyContactAltNumber(rs.getString("emergency_contact_alt_number"));
            child.setEmergencyContactRelation(rs.getString("emergency_contact_relation"));
            child.setPaymentFreq(rs.getInt("payment_freq"));
            child.setCreatedBy(rs.getString("created_by"));
            child.setCreateDate(rs.getTimestamp("create_date"));
            return child;
        }
    }
}
