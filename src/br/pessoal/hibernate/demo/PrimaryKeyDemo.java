package br.pessoal.hibernate.demo;

import br.pessoal.hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PrimaryKeyDemo {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure()//It searches for the default file(hibernate.cfg.xml) in /src if it's not there I need to specify
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();



        try (sessionFactory) {
            //create a session
            Session session = sessionFactory.getCurrentSession();

            //Use the session object to save Java object
            System.out.println("Creating 3 student object...");
            Student student = new Student("John", "Doe", "John.Doe@undead.com");
            Student student2 = new Student("Mary", "Public", "Mary.Public@undead.com");
            Student student3 = new Student("Bonita", "Applebum", "Bonita.Applebaum@undead.com");

            //start transaction
            session.beginTransaction();

            //save the student object
            System.out.println("Saving the students....");
            session.save(student);
            session.save(student2);
            session.save(student3);

            //commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        }
    }
}
