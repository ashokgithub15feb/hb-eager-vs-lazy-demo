package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class GetInstructorDetailDemo {

	public static void main(String[] args) {
		
		//create session factory 
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try
		{
			session.beginTransaction();
			
			int theId = 1000;
			
			InstructorDetail instructorDetail = session.get(InstructorDetail.class, theId);
			
			//
			System.out.println("instructorDetail: "+instructorDetail);
			
			//pribat the associated instructor
			System.out.println("the associated instructor: "+instructorDetail.getInstructor());
			
			session.getTransaction().commit();
			System.out.println("--------Done!!!---------");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			session.close();
			System.out.println("Close factory object!!");
			factory.close();
		
		}
		
	}

}
