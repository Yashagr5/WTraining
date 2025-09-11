package com.example.service;

import org.springframework.stereotype.Service;

import com.example.dao.StudentDao;
import com.example.model.Student;

@Service
public class StudentService {
	private final StudentDao dao;  // <-- inject interface, not impl

    public StudentService(StudentDao dao) {
    	super();
        this.dao = dao;
    }
    
    public void saveData(Student student) {
        dao.saveStudent(student);
    }
    
    public Student getStudent(Long id) {
        return dao.getStudent(id);
    }

}
