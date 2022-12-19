package br.pessoal.hibernate.demo;

import br.pessoal.hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteStudentDemo {
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

           //delete the student
           System.out.println("Deleting student: "+student);
//           session.delete(student);

           //delete student id=2
            System.out.println("deleting student id=2");
            session.createQuery("delete from Student where id=2").executeUpdate();

           session.getTransaction().commit();

            System.out.println("Done!");
        }
    }
}
