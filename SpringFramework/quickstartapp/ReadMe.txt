@OneToOne --- Many ---- @ManyToOne--- @ManyToMany

Two more annotations
@Embeddable -- Using the attributes of one class to another 

for eg:
(which has not existence without any entity class)
@Embeddable
public class Address
{
private String street;
private String city;
private String zip;

}

@Entity
public class Student
{
@Id
@GeneratedValue
private Long id;

private String name;

@Embdedded  // Will embed the fields or properties of address in student entity class
private Address address;

}

for eg : the result will look like this
id name street city zip
12 fdsf dfd    nd    23

Another way 

2) @DiscriminatorValue --  generally we use it with InheritanceType
In inheritance mapping (@Inheritance(Single_table) , all subclasses are stored in one table). JPA adds a hidden column (called Discriminator Column  with any name you can give)to differentiate rows.
@DiscriminatorVale("address") will tell to hibernate : that when dtype = address  it will map to address entity

for eg:

@Entity
@Inheritance(strategy = InheritanceType.Single_Table)
@DiscriminatorColumn(name="dtype")
public abstract class Person
{

@Id @GeneratedValue
Long id;
String name;


}

@Entity
@DiscriminatorValue("STUDENT")
public class Student extends Person
{
private String course;

}

@Entity
@DiscriminatorValue("TEACHER")
public class Teacher extends Person
{
private String subject;

}

// Output will be like:

id	name	course	subject	 dtype
1	Niti	BTech	null     STUDENT
2       Jiya	null    AI	 TEACHER 

The inheritance type will be 3 types

1) SingleTable -- There is one Parent Table and rest are the child tables ( Unnecessary memory wastage (nullable columns for subclass) . We use discriminator type and value, 
2) Joined -- Each class has its own table and Parent table has common fields , child tables have specific fields , queries require the joins ( No null)
3) Table_Per_Class -- There is no common Parent Table , No joins when querying child entities, it's hard to query because there is no common parent table
for eg:

@Entity
@Inheritance(strategy = InheritanceType.Table_Per_Class)
public abstract class Person
{

@Id @GeneratedValue
Long id;
String name;


}

@Entity
public class Student extends Person
{
private String course;

}

@Entity
public class Teacher extends Person
{
private String subject;

}

// output looks like:
Student :  id name course
Teacher :  id name subject

//






















```````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````

Spring Fundamentals :

1)Spring is a comprehensive enterprise application framework for java
2)It provides a container for managing objects through DI 
(Dependency Injection ) and AOP (Aspect-Oriented Programming)
3) It simplifies application development for the developers with various modules 
such as Spring Boot, Spring MVC , Spring Data , Spring Security

Modules 			Purpose
Spring Core			Dependency Injection(Ioc Containers)
Spring MVC			Web Applications & Rest APIs( http://localhost:8080/swiggy)
Spring Boot			Auto-configured framework for rapid development
Spring AOP 			Aspect Oriented Programming(Logging , security)
Spring Security 	Authentication & Authorization
Spring Data			simplifies database access (JDBC , JPA , Hibernate)
Spring Cloud  		Microservices & Distributed Systems



Install prerequisite
-- JDK 17 or higher version
-- Maven (for adding dependencies (just like in core java(we were adding jar files and setting in a class path))
-- IDE  (Intellij Idea / Eclipse / VS  Code
-- Postman / Swagger -- To test the APIs or Endpoints

To create a spring Project -- 
-- create a maven project / gradle -- Which is a building tool
-- After creating a spring project using a maven build tool , 
-- Then we have to add all the required 
dependencies in your pom.xml (A main configuration file of your project)
 
Q from where we will add all the dependencies
Ans: Mavenrepository.com  is the url and  we also call it as central repository

 
Spring Core : What is DI 
DI is a design pattern in which Spring injects dependencies(objects) into a class rather than the class creating them itself.

Dependency injection types :
Constructor Injection:used when depedency is required
Setter Injection: used when dependency is optional 

Earlier we were creating an object of a Bean class(class properties and methods)
class Student
{
int id;
String name;

}

Student s = new Student();
Now in spring , the container has taken a control back from the developers to create an object themselves (IOC) Inversion of control, 
here spring provides you two containers (BeanFactory (Core Spring Container) , ApplicationContext(Advanced Container - Most used)
so by adding a spring context dependency from mavenrepository you will be getting a IOC Container

The advanced features provided by applicationcontext container not by the BeanFactory (which is a core spring container and which is used for creating light weight applications instead of enterprise level) 
ApplicationContext  :  even propagation , work with annotation like @Autowired ,@Component ,  web application support and best used for creating enterprise applications
  

Summary :

Injection types :  Constructor and Setter
DI Containers   :  BeanFactory and ApplicationContext
