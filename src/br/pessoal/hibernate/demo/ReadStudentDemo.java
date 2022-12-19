package br.pessoal.hibernate.demo;

import br.pessoal.hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ReadStudentDemo {
    public static void main(String[] args) {

        //create session factory
        SessionFactory sessionFactory = new Configuration()
                .configure()//It searches for the default file(hibernate.cfg.xml) in /src if it's not there I need to specify
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();



        try (sessionFactory) {

            //create a session
            Session session = sessionFactory.getCurrentSession();
            //Use the session object to save Java object
            System.out.println("Creating new student object...");

            Student student = new Student("Daffy", "duck", "Daffy.duck@undead.com");

            //start transaction
            session.beginTransaction();

            //save the student object
            System.out.println("Saving the student....");
            System.out.println(student);
            session.save(student);

            //commit transaction
            session.getTransaction().commit();

            //find out the student's id: primary key
            System.out.println("Saved student, generate Id: "+student.getId());

            //now get a new session and start transaction
            /**
             * After use session.getTransaction().commit() the connection is closed, so it's necessary
             * to call session factory and create a new one.
             * If session.getTransaction().commit() wasn't called above wouldn't be necessary
             * to call sessionFactory.getCurrentSession() again for a new session
             * */
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            //retrieve st based on the id: primary key
            System.out.println("Getting student with id:: "+student.getId());
            Student myStudent = session.get(Student.class, student.getId());

            System.out.println("Get Complete "+myStudent);
            //commit the transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        }
    }
}
