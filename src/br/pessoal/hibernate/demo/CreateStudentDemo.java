package br.pessoal.hibernate.demo;

import br.pessoal.hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateStudentDemo {
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

            Student student = new Student("Lucas", "Oliveira", "lucas.oliveira@undead.com");

            //start transaction
            session.beginTransaction();

            //save the student object
            System.out.println("Saving the student....");
            session.save(student);

            //commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        }
    }
}
