package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class DeleteInstructorDetailDemo {

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
			
			int theId = 2;
			
			InstructorDetail instructorDetail = session.get(InstructorDetail.class, theId);
			
			//
			System.out.println("instructorDetail: "+instructorDetail);
			
			//pribat the associated instructor
			System.out.println("the associated instructor: "+instructorDetail.getInstructor());
			
			//now let's delete the instructor details
			System.out.println("Deleteing instructor details : "+instructorDetail);
			//remove the association object instructor 
			//break bi-directonal link
			
			//(remove deleted object from associations): [com.luv2code.hibernate.demo.entity.InstructorDetail#2]]
		//	instructorDetail.getInstructor().setInstructorDetail(null);
			session.delete(instructorDetail);
			
			
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
