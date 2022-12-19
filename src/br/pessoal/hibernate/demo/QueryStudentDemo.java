package br.pessoal.hibernate.demo;

import br.pessoal.hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class QueryStudentDemo {
    public static void main(String[] args) {

        //create session factory
        SessionFactory sessionFactory = new Configuration()
                .configure()//It searches for the default file(hibernate.cfg.xml) in /src if it's not there I need to specify
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();



        try (sessionFactory) {

            //create a session
            Session session = sessionFactory.getCurrentSession();

            //start transaction
            session.beginTransaction();

            //Query students
            List<Student> students = session.createQuery("from Student", Student.class).list();

            //Display the students
            students.forEach(System.out::println);

            //Query students: lastName='Doe'
            students = session.createQuery("from Student s Where s.lastName='Doe'", Student.class).list();

            //Display the students
            System.out.println("\n\nStudents who have last name of Doe");
            students.forEach(System.out::println);

            //Query students: lastName = 'Doe' OR firstName = 'Daffy'
            students = session.createQuery("from Student s Where s.lastName='Doe' OR s.firstName = 'Daffy'", Student.class).list();

            System.out.println("\n\nStudents who have last name of Doe Or firstName Daffy");
            students.forEach(System.out::println);

            //Query students where email LIKE '%undead.com'
            students = session.createQuery("from Student s Where s.email LIKE '%undead.com'", Student.class).list();

            System.out.println("\n\nStudents who have email as undead");
            students.forEach(System.out::println);

            //commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        }
    }
}
