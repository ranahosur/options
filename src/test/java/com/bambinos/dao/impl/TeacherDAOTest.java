package com.bambinos.dao.impl;

import com.bambinos.dao.CenterDAO;
import com.bambinos.dao.LoginDAO;
import com.bambinos.dao.TeacherDAO;
import com.bambinos.model.BambinoConstants;
import com.bambinos.model.Center;
import com.bambinos.model.Teacher;
import com.bambinos.util.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import static org.junit.Assert.*;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;
import java.util.List;

@ContextConfiguration(locations = { "classpath:/bambinos/config/user-beans.xml" })
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class TeacherDAOTest {

    public static final String LINKED_IN_PROFILE = "www.google.com";
    public static final BigDecimal COST_PER_HOUR = new BigDecimal("255.0");
    public static final String QUALIFICATION = "Doctor";
    public static final String TECH = "BTech";
    public static final String EMPLOYMENT_TYPE = "Contract";
    public static final String SEX = "M";
    public static final String LAST_NAME = "Chopra";
    public static final String FIRST_NAME = "Madan";
    @Autowired
    TeacherDAO teacherDAO;

    @Test
    public void createT() {
        Teacher teacher = new Teacher();
        teacher.setCostPerHour(COST_PER_HOUR);
        teacher.setLinkedInProfile(LINKED_IN_PROFILE);
        teacher.setMedicalQualification(QUALIFICATION);
        teacher.setQualification(TECH);
        teacher.setEmploymentType(EMPLOYMENT_TYPE);
        teacher.setDob(DateUtil.currentDate());
        teacher.setSex(SEX);
        teacher.setLastName(LAST_NAME);
        teacher.setFirstName(FIRST_NAME);
        teacher.setCreatedBy(BambinoConstants.ADMIN_USER);

        teacherDAO.createTeacher(teacher);
        Teacher t = teacherDAO.findTeachersAll().get(0);
//        assertEquals(t.getCostPerHour(),COST_PER_HOUR);
        assertEquals(t.getCreatedBy(),BambinoConstants.ADMIN_USER);
        assertEquals(t.getEmploymentType(),EMPLOYMENT_TYPE);
        t.setSex("F");
        teacherDAO.updateTeacher(t);
    }
}
