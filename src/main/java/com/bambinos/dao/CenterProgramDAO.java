package com.bambinos.dao;

import com.bambinos.model.CenterProgram;

import java.util.List;

public interface CenterProgramDAO {

    CenterProgram createCenterProgram(CenterProgram centerProgram);
    //never going to update
    void deleteCenterProgram(String centerProgramId);
    List<CenterProgram> findCenterProgramsByCenterId(String centerId);
}
