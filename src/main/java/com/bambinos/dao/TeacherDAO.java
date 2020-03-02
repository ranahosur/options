package com.bambinos.dao;

import com.bambinos.model.Teacher;

import java.util.List;

public interface TeacherDAO {
    Teacher createTeacher(Teacher teacher);
    Teacher updateTeacher(Teacher teacher);
    void deleteTeacher(String teacherId);
    Teacher findTeacherByTeacherId(String teacherId);
    List<Teacher> findTeachersAll();
}
