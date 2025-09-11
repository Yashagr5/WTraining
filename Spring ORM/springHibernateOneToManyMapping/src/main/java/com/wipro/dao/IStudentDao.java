package com.wipro.dao;

import com.wipro.entity.Student;

public interface IStudentDao {
	void saveStudent(Student student);
    Student getStudent(Long id);
}
