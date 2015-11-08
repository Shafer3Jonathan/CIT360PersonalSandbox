/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pgen;

//import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Jonathan the Boss McDos of java programming
 */
//@ManagedBean
@Named
@RequestScoped
public class EmployeeController implements Serializable {
    /*Declare session variables for use in the Employee Controller Class
    * Calls the HibernateUtil.java class to enable this to create the following objects
    */
    private static SessionFactory factory;
    private Session session;
    private String testdata = "sample";
    
    /*Default Constructor for the Employee Controller Class every time this class is instanciated this method is called*/
    public EmployeeController(){
        //this attempts to open the Database Connection the the employee database defined in the database connector specified hibernate.cfg.xml
        try{
            factory = new Configuration().configure().buildSessionFactory();
            session = factory.openSession();
        }catch (Throwable ex) { 
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex); 
        }
    }
    public String getPassword(){
      return "Password " + testdata;
    }
    /*This Method closes the Hibernate DB connection*/
    private void CloseConnection(){
         session.close(); 
    }
    /*This is how one would add an employee to the employee table of the employee database*/
    public void empAdd(String firstname, String lastname, Integer salary){
        EmployeeController EC = new EmployeeController();
        EC.addEmployee(firstname, lastname, salary);
        testdata = "sample changed";
        CloseConnection();
    }
    /*This is how one would list the contents of the employee table of the employee database*/
    public void empList(){
        EmployeeController EC = new EmployeeController();
        EC.listEmployees();
        CloseConnection();
    }
    /*This is how one would update a salary for someon in the employee database*/
    public void empUpd(int EmpID, int Salary){
        EmployeeController EC = new EmployeeController();
        EC.updateEmployee(EmpID, Salary);
        CloseConnection();
    }
    /*Use this to delete an employee from the employee table of the employee database*/
    public void empDel(int EmpID) {
        EmployeeController EC = new EmployeeController();
        EC.deleteEmployee(EmpID);
        CloseConnection();
    }
    
    /* Method to ADD an Employee */
    private Integer addEmployee(String fname, String lname, int salary){
      Transaction tx = null;
      Integer employeeID = null;
      try{
         tx = session.beginTransaction();
         Employee employee = new Employee(fname, lname, salary);
         employeeID = (Integer) session.save(employee); 
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }
      
      return employeeID;
   }
    /* Method to LIST all employees */
    private void listEmployees( ){
      Transaction tx = null;
      try{
         tx = session.beginTransaction();
         List employees = session.createQuery("FROM Employee").list(); 
         for (Iterator iterator = 
                           employees.iterator(); iterator.hasNext();){
            Employee employee = (Employee) iterator.next(); 
            System.out.print("First Name: " + employee.getFirstName()); 
            System.out.print("  Last Name: " + employee.getLastName()); 
            System.out.println("  Salary: " + employee.getSalary()); 
         }
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }
   }
   
   /* Method to UPDATE salary for an employee */
   private void updateEmployee(Integer EmployeeID, int salary ){
      Transaction tx = null;
      try{
         tx = session.beginTransaction();
         Employee employee = 
                    (Employee)session.get(Employee.class, EmployeeID); 
         employee.setSalary( salary );
		 session.update(employee); 
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }
   }
   
   /* Method to DELETE an employee from the records */
   private void deleteEmployee(Integer EmployeeID){
      Transaction tx = null;
      try{
         tx = session.beginTransaction();
         Employee employee = 
                   (Employee)session.get(Employee.class, EmployeeID); 
         session.delete(employee); 
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }
   }
}
