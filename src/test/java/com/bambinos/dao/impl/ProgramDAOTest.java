package com.bambinos.dao.impl;

import com.bambinos.dao.CenterDAO;
import com.bambinos.dao.CenterProgramDAO;
import com.bambinos.dao.LoginDAO;
import com.bambinos.dao.ProgramDAO;
import com.bambinos.model.BambinoConstants;
import com.bambinos.model.Center;
import com.bambinos.model.CenterProgram;
import com.bambinos.model.Program;
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
public class ProgramDAOTest {

    public static final int ASSESSMENT_PERIOD = 3;
    public static final int CAPACITY = 10;
    public static final BigDecimal PRICE = new BigDecimal("1000.00");
    public static final String DESCRIPTION = "Day care center";
    public static final String PROGRAM_CODE = "PROG001";
    public static final String PROGRAM_NAME = "PGM Name";
    @Autowired
    ProgramDAO programDAO;

    @Autowired
    CenterProgramDAO centerProgramDAO;

    @Autowired
    CenterDAO centerDAO;

    @Test
    public void runAllTests(){
        List<Program> programs = programDAO.findPrograms();
        if(programs == null || programs.size() == 0){
            Program program = new Program();
            program.setCreatedBy(BambinoConstants.ADMIN_USER);
            program.setAssessmentPeriod(ASSESSMENT_PERIOD);
            program.setCapacity(CAPACITY);
            program.setPrice(PRICE);
            program.setDescription(DESCRIPTION);
            program.setProgramCode(PROGRAM_CODE);
            program.setProgramName(PROGRAM_NAME);

            programDAO.createProgram(program);


        }
        programs = programDAO.findPrograms();
        assertEquals(programs.size() ,1);
        Program p = programs.get(0);
        assertEquals(p.getAssessmentPeriod(),ASSESSMENT_PERIOD);
        assertEquals(p.getProgramCode(),PROGRAM_CODE);
        assertEquals(p.getCapacity(),CAPACITY);
        assertEquals(p.getDescription(),DESCRIPTION);
        assertEquals(p.getProgramCode(),PROGRAM_CODE);
        assertEquals(p.getProgramName(),PROGRAM_NAME);
        p.setProgramName(DESCRIPTION);
        p.setDescription(PROGRAM_NAME);
        programDAO.updateProgram(p);
        programs = programDAO.findPrograms();
        assertEquals(programs.size() ,1);
        p = programs.get(0);
        assertEquals(p.getDescription(),PROGRAM_NAME);
        assertEquals(p.getProgramName(),DESCRIPTION);
    }

    @Test
    public void testCP(){
        CenterProgram cp = new CenterProgram();
        List<Center> centers = centerDAO.findCenters();

        cp.setCenterId(centers.get(0).getCenterId());
        cp.setProgramId(programDAO.findPrograms().get(0).getProgramId());
        List<CenterProgram> centerPrograms = centerProgramDAO.findCenterProgramsByCenterId(centers.get(0).getCenterId());
        if(centerPrograms == null || centerPrograms.size() == 0){
            centerProgramDAO.createCenterProgram(cp);
        }
        centerPrograms = centerProgramDAO.findCenterProgramsByCenterId(centers.get(0).getCenterId());
        assertEquals(centerPrograms.size(),1);
        centerProgramDAO.deleteCenterProgram(centerPrograms.get(0).getCenterProgramId());
        centerPrograms = centerProgramDAO.findCenterProgramsByCenterId(centers.get(0).getCenterId());
        assertEquals(centerPrograms.size(),0);

    }

}
