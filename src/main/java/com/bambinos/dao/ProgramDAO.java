package com.bambinos.dao;

import com.bambinos.model.Program;

import java.util.List;

public interface ProgramDAO {

    Program createProgram(Program program);
    Program updateProgram(Program program);
    void deleteProgram(String programId);
    List<Program> findPrograms();
}
