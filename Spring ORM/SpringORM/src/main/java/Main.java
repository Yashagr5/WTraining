
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.gl.config.HibernateConfig;
import com.gl.dao.DaoStudent;
import com.gl.model.Student;

import java.util.List;
public class Main {
	public static void main(String[] args) {
        // Initialize Spring context
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfig.class);
        DaoStudent studentDao = context.getBean(DaoStudent.class);

        // Insert a new student with ID 101 and name "Nisha"
        Student student1 = new Student();
         // Manually set ID for demonstration
        student1.setId(101);
        student1.setName("Nisha");
        student1.setEmail("bdnfjbnnj@gmail.com");
        studentDao.save(student1);
        System.out.println("Student inserted: " + student1);

        // Update the student's name to "Priya"
        student1.setName("Priya");
        studentDao.update(student1);
        System.out.println("Student updated: " + student1);

        // Retrieve the student with ID 101
        Student retrievedStudent = studentDao.findById(101);
        System.out.println("Retrieved Student: " + retrievedStudent);

        // Insert additional students for demonstration
        Student student2 = new Student();
         // Manually set ID for demonstration
        student2.setId(102);
        student2.setName("Danish");
        studentDao.save(student2);

        Student student3 = new Student();
        // Manually set ID for demonstration
        student3.setId(103); 
        student3.setName("Sneha");
        studentDao.save(student3);

        // Retrieve all students before deletion
        System.out.println("All Students Before Deletion:");
        List<Student> studentsBeforeDeletion = studentDao.findAll();
        studentsBeforeDeletion.forEach(System.out::println);

        // Delete the student with ID 102
        studentDao.delete(102);
        System.out.println("Student with ID 102 deleted.");

        // Retrieve all students after deletion
        System.out.println("All Students After Deletion:");
        List<Student> studentsAfterDeletion = studentDao.findAll();
        studentsAfterDeletion.forEach(System.out::println);

        // Close the Spring context
        context.close();
    }
	
}
