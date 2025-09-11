package com.wipro;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.wipro.config.AppConfig;
import com.wipro.entity.ReportCard;
import com.wipro.entity.Student;
import com.wipro.service.StudentService;


//App.java --> Controller  --> Service --- > Repository -- JPA --> Hibernate ---> jdbc --- >Database
public class App 
{
    public static void main( String[] args )
    {
    	

    	AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        StudentService service = ctx.getBean(StudentService.class);

        // Save student
        ReportCard rc = new ReportCard(490);
        Student student = new Student("nisha");
        student.setReportcard(rc);
        service.saveData(student);

        // Fetch student by ID
        Student fetchedStudent = service.getStudent(1L);
        System.out.println("Fetched Student: " + fetchedStudent);

        ctx.close();
    }
}