package com.bambinos.dao;

import com.bambinos.model.Teacher;
import com.bambinos.model.TeacherProgram;

import java.util.List;

public interface TeacherProgramDAO {

    TeacherProgram createTeacherProgram(TeacherProgram teacherProgram);
//    TeacherProgram updateTeacherProgram(TeacherProgram teacherProgram);
    void deleteTeacherProgram(String teacherProgramId);
//    TeacherProgram findTeacherProgramByTeacherProgramId(String teacherProgramId);
    List<TeacherProgram> findTeacherProgramsByTeacherId(String teacherId);
}
