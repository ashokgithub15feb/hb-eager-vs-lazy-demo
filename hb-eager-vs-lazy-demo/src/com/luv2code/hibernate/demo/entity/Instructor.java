package com.luv2code.hibernate.demo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "instructor")
public class Instructor {

	// annotate the class as an entity and map to db table.

	// define the field

	// annotate the filed with db column names

	//** set up mapping to InstructorDetail entity
	
	// create constructors

	// getter and setter methods

	// generate toString method

//	@Id
//	@GeneratedValue(generator = "inc-gen")
//	@GenericGenerator(name="gen", strategy="foreign", parameters={@Parameter(name="property", value="txn")})
//	@GeneratedValue(generator="gen")
//	@GenericGenerator(name = "gen", strategy="foreign", parameters={@Parameter(value = "instructorDetail", name = "property")})
	
	@Id
	@GenericGenerator(name = "inc-gen", strategy="identity")
	@Column(name = "id")
	private int id;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastname;
	
	@Column(name = "email")
	private String email;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "instructor_detail_id")
	private InstructorDetail instructorDetail;
	
	/*
	 * More about eager vs Lazy loading
	 * 
	 * default - fetch type:
	 * 1- @OneToOne - FetchType.EAGER
	 * 2- @OneToMany - FetchType.LAZY
	 * 3- @ManyToOne - FetchType.EAGER
	 * 4- @ManyToMany - FetchType.LAZY
	 * 
	 * override a default fetch type:
	 * @ManyToOne(FetchType=LAZY)
	 * 
	 * when you load the data is only retrieved on demand
	 * however this required on open the hibernate session
	 * need an connection to database to retrieved data
	 * 
	 * what happend when hibetnate session is close
	 * -----> and you attempt to retrieve lazy data
	 * hibernate will throw an exception
	 * 
	 * 
	 * retrieve lazy data using
	 * 1-session.get(...) and call appropriate getter method
	 * 2-Hibernate query with HQL
	 * 3-etc
	 */
	
	//reference by course class
	//EAGER - Everything is loaded: Instructor, course, InstructorDetails at a time
	//not need to hit the database thanks to eager loading
	//best practice lazy loading is a good practice
//	@OneToMany(fetch = FetchType.EAGER, 
//			   mappedBy = "instructor", 
//			   cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}) 
//	private List<Course> courses;
	
	//loading on demand
	@OneToMany(fetch = FetchType.LAZY, 
			   mappedBy = "instructor", 
			   cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}) 
	private List<Course> courses;
	
	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public Instructor() {
	}

	public Instructor(String firstName, String lastname, String email) {
		this.firstName = firstName;
		this.lastname = lastname;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public InstructorDetail getInstructorDetail() {
		return instructorDetail;
	}

	public void setInstructorDetail(InstructorDetail instructorDetail) {
		this.instructorDetail = instructorDetail;
	}

	@Override
	public String toString() {
		return "Instructor [id=" + id + ", firstName=" + firstName + ", lastname=" + lastname + ", email=" + email
				+ ", instructorDetail=" + instructorDetail + ", courses=" + courses + "]";
	}
	
	//add convenience method for Bi-Directional relationship
	public void add(Course tempCourse)
	{
		if(courses  == null)
		{
			courses = new ArrayList<>();
		}
		
		courses.add(tempCourse);
		
		tempCourse.setInstructor(this);
	}

}
