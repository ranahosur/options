package com.bambinos.dao;

import com.bambinos.model.TeacherCenter;

import java.util.List;

public interface TeacherCenterDAO {

    TeacherCenter createTeacherCenter(TeacherCenter teacherCenter);
    //    TeacherCenter updateTeacherCenter(TeacherCenter teacherCenter);
    void deleteTeacherCenter(String teacherCenterId);
    //    TeacherCenter findTeacherCenterByTeacherCenterId(String teacherCenterId);
    List<TeacherCenter> findTeacherCentersByTeacherId(String teacherId);
}
