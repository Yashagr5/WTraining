package com.wipro.service;

import org.springframework.stereotype.Service;
import com.wipro.dao.IStudentDao;
import com.wipro.entity.Student;

@Service
public class StudentService {
    
    private final IStudentDao dao;  // <-- inject interface, not impl

    public StudentService(IStudentDao dao) {
        this.dao = dao;
    }
    
    public void saveData(Student student) {
        dao.saveStudent(student);
    }
    
    public Student getStudent(Long id) {
        return dao.getStudent(id);
    }
}
