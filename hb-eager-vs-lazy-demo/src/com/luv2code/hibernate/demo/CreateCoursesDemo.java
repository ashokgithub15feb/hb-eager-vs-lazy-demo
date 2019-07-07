package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class CreateCoursesDemo {

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
			int theId = 1;
			Instructor instructor = session.get(Instructor.class, theId);
			
			//create the object
			Course course1 = new Course("Air Guitar - The Ultimate Guide");
			Course course2 = new Course("The Pinball Masterclass");
			Course course3 = new Course("Yoga Class");
			Course course4 = new Course("Gym Class");
		
			if(instructor != null)
			{
				instructor.add(course1);
				instructor.add(course2);
				instructor.add(course3);
				instructor.add(course4);
			}
			else
			{
				System.out.println("instructor is: "+instructor);
				return;
			}
			
			//save the Instructor
			session.save(course1);
			session.save(course2);
			session.save(course3);
			session.save(course4);
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("--------Done!!!---------");
		}finally
		{
			System.out.println("Close factory object!!");
			session.close();
			factory.close();
		}
		
	}

}
