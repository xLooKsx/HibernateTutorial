package br.pessoal.hibernate.demo;

import br.pessoal.hibernate.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Objects;

public class EmployeeAllInOne {
    public static void main(String[] args) {

        final String newCompanyName = "Anor Londo";

        SessionFactory sessionFactory = new Configuration()
                .configure()
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

        try(sessionFactory){

            Employee employee = saveEmployee(getSession(sessionFactory));
            checkIfEmployeeWasCreated(employee, getSession(sessionFactory));
            updateEmployeeToAnotherCompany("Anor Londo", employee, getSession(sessionFactory));
            Employee newEmployee = checkIfEmployeeWasChangedCompany(newCompanyName, getSession(sessionFactory));
            System.out.println("I found it in a new company called: "+newCompanyName);
            timeToSayGoodBye(newEmployee, getSession(sessionFactory));

        }
    }

    private static Session getSession(SessionFactory sessionFactory) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        return session;
    }

    private static void timeToSayGoodBye(Employee employee, Session session) {

        session.delete(employee);
        session.getTransaction().commit();
        System.out.println("Employee deleted bye bye");
    }

    private static Employee checkIfEmployeeWasChangedCompany(String companyName, Session session) {

        Employee employee = (Employee) session.createQuery("from Employee e Where e.company = '"+companyName+"'").getSingleResult();

        session.getTransaction().commit();

        return employee;
    }

    private static void updateEmployeeToAnotherCompany(String newCompanyName, Employee employee, Session session) {

        Employee employee1 = session.get(Employee.class, employee.getId());
        employee1.setCompany(newCompanyName);

        session.update(employee1);
        System.out.println("this employee is now in a new company, good luck");

        session.getTransaction().commit();
    }

    private static void checkIfEmployeeWasCreated(Employee employee, Session session) {

        Employee employee1 = session.get(Employee.class, employee.getId());

        if (Objects.nonNull(employee1)){
            System.out.println("I've found It :D");
            System.out.println(employee1);
        }else{
            System.out.println("Something Wrong it's not right :/");
        }
        session.getTransaction().commit();
    }

    private static Employee saveEmployee(Session session) {

        Employee employee = new Employee("Lucas", "Oliveira", "Firelink");

        session.save(employee);
        System.out.println("We have a new employee "+employee);

        session.getTransaction().commit();
        return employee;
    }
}
