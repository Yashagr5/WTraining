package com.example.dao;

import com.example.model.Student;

public interface StudentDao {
	void saveStudent(Student student);
    Student getStudent(Long id);
}
