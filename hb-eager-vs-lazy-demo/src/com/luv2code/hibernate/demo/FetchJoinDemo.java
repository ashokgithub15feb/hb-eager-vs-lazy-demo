package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class FetchJoinDemo {

	public static void main(String[] args) {
		
		//create session factory 
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class).buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try
		{
			//start a transaction
			session.beginTransaction();
		
			//get the instructor from db;
			int theId = 2;
			
			Query<Instructor> query = session.createQuery("select i from Instructor i "+" Join FETCH i.courses "+ " where i.id = :theInstructorId", Instructor.class);
			
			query.setParameter("theInstructorId", theId);
			
			Instructor singleResult = query.getSingleResult();
			
			System.out.println("singleResult: "+singleResult);
		
			session.getTransaction().commit();
			
			session.close();
			//exception- org.hibernate.LazyInitializationException - 
			//failed to lazily initialize a collection of role course could not initialize proxy - no session
			
			//option 1- call getter method while session is open
			
			System.out.println("Courses: "+singleResult.getCourses());
			
			//commit transaction
			
			System.out.println("--------Done!!!---------");
		}finally
		{
			System.out.println("Close factory object!!");
			session.close();
			factory.close();
		}
		
	}

}
