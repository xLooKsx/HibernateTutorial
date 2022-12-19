package br.pessoal.hibernate.demo;

import br.pessoal.hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UpdateStudentDemo {
    public static void main(String[] args) {

        //create session factory
        SessionFactory sessionFactory = new Configuration()
                .configure()//It searches for the default file(hibernate.cfg.xml) in /src if it's not there I need to specify
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();



        try (sessionFactory) {

            int studentId = 1;
            //create a session
            Session session = sessionFactory.getCurrentSession();

            //start transaction
            session.beginTransaction();

            //find out the student's id: primary key
            System.out.println("Saved student, generate Id: "+ studentId);

           Student student = session.get(Student.class, studentId);

            System.out.println("Updating student...");
            student.setFirstName("Scooby");

            session.getTransaction().commit();

            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            //Update email for all students
            System.out.println("update email for all students");
            session.createQuery( "update Student set email = 'foo@gmail.com'").executeUpdate();

            session.getTransaction().commit();

            System.out.println("Done!");
        }
    }
}
