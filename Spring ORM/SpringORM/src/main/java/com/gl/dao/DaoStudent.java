package com.gl.dao;

import com.gl.model.Student;
import java.util.List;

public interface DaoStudent {
	void save(Student student);
    Student findById(int id);
    List<Student> findAll();
    void update(Student student);
    void delete(int id);
}
