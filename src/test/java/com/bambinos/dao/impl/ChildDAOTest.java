package com.bambinos.dao.impl;
import com.bambinos.dao.CenterDAO;
import com.bambinos.dao.ChildDAO;
import com.bambinos.dao.LoginDAO;
import com.bambinos.model.BambinoConstants;
import com.bambinos.model.Center;
import com.bambinos.model.Child;
import com.bambinos.util.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import static org.junit.Assert.*;
import static org.junit.Assert.assertNull;

import java.util.List;

@ContextConfiguration(locations = { "classpath:/bambinos/config/user-beans.xml" })
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class ChildDAOTest {


    public static final int PAYMENT_FREQ = 4;
    public static final String EMERGENCY_CONTACT_RELATION = "Friend";
    public static final String EMERGENCY_CONTACT_ALT_NUMBER = "272348234";
    public static final String EMERGENCY_CONTACT_NUMBER = "2384999923";
    public static final String EMERGENCY_CONTACT_NAME = "owsvm posdf";
    public static final String CCTV_ACCESS = "Y";
    public static final String BLOOD_GROUP = "B+";
    public static final String MOTHER_COMPANY_NAME = "M C L";
    public static final String MOTHER_COMPANY_NUMBER = "782343333";
    public static final String MOTHER_OCCUPATION = "software";
    public static final String FATHER_COMPANY_NUMBER = "888888888";
    public static final String FATHER_COMPANY_NAME = "F C L";
    public static final String FATHER_OCCUPATION = "business";
    public static final String MOTHER_MOBILE = "666666666";
    public static final String FATHER_MOBILE = "111111111";
    public static final String YOAN = "yoan";
    public static final String FATHER_NAME = "jina";
    public static final String PINCODE = "88888";
    public static final String STATE = "KA";
    public static final String ADDRESS_1 = "sdf -234  lsdf";
    public static final String ADDRESS_2 = "234 f9nmwwdf  sdf";
    public static final String SEX = "M";
    public static final String FIRST_NAME = "avanish";
    public static final String LAST_NAME = "joaehi";
    public static final String CITY = "chandi";
    public static final String ALLERGY = "Y";
    public static final String ALLERGY_DESCRIPTION = "allergy test";
    public static final String ALLOW_SOCIAL_MEDIA = "Y";
    public static final String DIETARY_NEED = "Veg Only";
    public static final String MOBILE_NUMBER = "2348234234";
    @Autowired
    ChildDAO childDAO;

    @Test
    public void createTest(){
        Child child = new Child();
        child.setPaymentFreq(PAYMENT_FREQ);
        child.setEmergencyContactRelation(EMERGENCY_CONTACT_RELATION);
        child.setEmergencyContactAltNumber(EMERGENCY_CONTACT_ALT_NUMBER);
        child.setEmergencyContactNumber(EMERGENCY_CONTACT_NUMBER);
        child.setEmergencyContactName(EMERGENCY_CONTACT_NAME);
        child.setJoiningDate(DateUtil.currentDate());
        child.setCctvAccess(CCTV_ACCESS);
        child.setBloodGroup(BLOOD_GROUP);
        child.setMotherCompanyName(MOTHER_COMPANY_NAME);
        child.setMotherCompanyNumber(MOTHER_COMPANY_NUMBER);
        child.setMotherOccupation(MOTHER_OCCUPATION);
        child.setFatherCompanyNumber(FATHER_COMPANY_NUMBER);
        child.setFatherCompanyName(FATHER_COMPANY_NAME);
        child.setFatherOccupation(FATHER_OCCUPATION);
        child.setMotherMobile(MOTHER_MOBILE);
        child.setFatherMobile(FATHER_MOBILE);
        child.setMotherName(YOAN);
        child.setFatherName(FATHER_NAME);
        child.setPincode(PINCODE);
        child.setState(STATE);
        child.setAddress1(ADDRESS_1);
        child.setAddress2(ADDRESS_2);
        child.setDob(DateUtil.currentDate());
        child.setSex(SEX);
        child.setFirstName(FIRST_NAME);
        child.setLastName(LAST_NAME);
        child.setCity(CITY);
        child.setCreatedBy(BambinoConstants.ADMIN_USER);
        child.setAllergy(ALLERGY);
        child.setAllergyDescription(ALLERGY_DESCRIPTION);
        child.setAllowSocialMedia(ALLOW_SOCIAL_MEDIA);
        child.setDietaryNeed(DIETARY_NEED);
        child.setMobileNumber(MOBILE_NUMBER);


        childDAO.createChild(child);

        List<Child> childList = childDAO.findChildByMobile(FATHER_MOBILE);
        Child c = childList.get(0);
        assertEquals(c.getAddress1(),ADDRESS_1);
        assertEquals(c.getAddress2(),ADDRESS_2);
        assertEquals(c.getAllergy(),ALLERGY);
        assertEquals(c.getAllergyDescription(),ALLERGY_DESCRIPTION);
        assertEquals(c.getAllowSocialMedia(),ALLOW_SOCIAL_MEDIA);
        assertEquals(c.getBloodGroup(),BLOOD_GROUP);
        assertEquals(c.getCctvAccess(),CCTV_ACCESS);
        assertEquals(c.getDietaryNeed(),DIETARY_NEED);
        assertEquals(c.getCity(),CITY);
        assertEquals(c.getCreatedBy(),BambinoConstants.ADMIN_USER);
        assertEquals(c.getEmergencyContactAltNumber(),EMERGENCY_CONTACT_NUMBER);
        assertEquals(c.getEmergencyContactName(),EMERGENCY_CONTACT_NAME);
        assertEquals(c.getEmergencyContactNumber(),EMERGENCY_CONTACT_NUMBER);
        assertEquals(c.getEmergencyContactRelation(),EMERGENCY_CONTACT_RELATION);
        assertEquals(c.getFatherCompanyName(),FATHER_COMPANY_NAME);
        assertEquals(c.getFatherCompanyNumber(),FATHER_COMPANY_NUMBER);
        assertEquals(c.getFatherMobile(),FATHER_MOBILE);
        assertEquals(c.getFatherName(),FATHER_NAME);
        assertEquals(c.getFatherOccupation(),FATHER_OCCUPATION);
        assertEquals(c.getFirstName(),FIRST_NAME);
        assertEquals(c.getMobileNumber(),MOBILE_NUMBER);
        assertEquals(c.getMotherCompanyName(),MOTHER_COMPANY_NAME);
        assertEquals(c.getMotherCompanyNumber(),MOTHER_COMPANY_NUMBER);
        assertEquals(c.getMotherMobile(),MOTHER_MOBILE);
        assertEquals(c.getMotherOccupation(),MOTHER_OCCUPATION);
        assertEquals(c.getMotherName(),YOAN);
        assertEquals(c.getSex(),SEX);
        assertEquals(c.getState(),STATE);
        assertEquals(c.getPaymentFreq(),PAYMENT_FREQ);
        assertEquals(c.getPincode(),PINCODE);

    }
}
