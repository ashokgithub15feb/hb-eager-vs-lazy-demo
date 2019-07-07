package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class CreateDemo {

	public static void main(String[] args) {
		
		//create session factory 
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try
		{
			//create the object
//			Instructor tempInstructor = new Instructor("Chad", "Darby", "darby@luv2code.com");
//			
//			InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.luv2code.com/youtube", "Luv 2 Code");

			InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.luv2code.com/youtube", "Guitar");

			Instructor tempInstructor = new Instructor("Sita", "Desai", "sita@luv2code.com");
			
			//associate the object
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			
			//start a transaction
			session.beginTransaction();
		
			//save the Instructor
			//Node: this will also save the details object
			//because of CascadeTpe.ALL
//			System.out.println("Savng Instructor: "+tempInstructor);
			session.save(tempInstructor);

			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("--------Done!!!---------");
		}finally
		{
			System.out.println("Close factory object!!");
			factory.close();
		}
		
	}

}
