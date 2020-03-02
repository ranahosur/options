package com.bambinos.dao.impl;

import com.bambinos.dao.CenterDAO;
import com.bambinos.dao.LoginDAO;
import com.bambinos.model.BambinoConstants;
import com.bambinos.model.Center;
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
public class CenterDAOTest {
    public static final String AMBULANCE_NUMBER = "823495992";
    public static final String HOSPITAL_NUMBER = "2384324234";
    public static final String LOCATION_123 = "Location 123";
    public static final String LAND_MARK = "near Bhive";
    public static final String CENTER_CODE = "1001";
    @Autowired
    public CenterDAO centerDAO;

    @Test
    public void runAllTests(){
        List<Center> centers = centerDAO.findCenters();
        if(centers == null || centers.size() == 0){
            createCenter();
        }
        centers = centerDAO.findCenters();
        assertEquals(centers.size(), 1);
        Center c = centers.get(0);
        assertEquals(c.getAmbulanceNumber(),AMBULANCE_NUMBER);
        assertEquals(c.getHospitalNumber(),HOSPITAL_NUMBER);
        assertEquals(c.getCenterCode(),CENTER_CODE);
        assertEquals(c.getCreatedBy(),BambinoConstants.ADMIN_USER);
        assertEquals(c.getLandMark(),LAND_MARK);
        assertEquals(c.getMapLocation(),LOCATION_123);
        centerDAO.deleteCenter(c.getCenterId());


    }

    public void createCenter() {
        Center center = new Center();
        center.setAmbulanceNumber(AMBULANCE_NUMBER);
        center.setHospitalNumber(HOSPITAL_NUMBER);
        center.setMapLocation(LOCATION_123);
        center.setLandMark(LAND_MARK);
        center.setCenterCode(CENTER_CODE);
        center.setCreatedBy(BambinoConstants.ADMIN_USER);
        centerDAO.createCenter(center);
    }


}
