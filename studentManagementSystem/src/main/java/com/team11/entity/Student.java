package com.team11.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "student")
public class Student {
	
	// Fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	private String firstName;
	private String lastName;
	private String middleName;
	private String gender;
	private Date birthDate;
	private String degree;
	private String address;
	private String mobile;

	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "level")
	private Level level;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "semester")
	private Semester semester;
	
	@OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
	private tblGPA cgpa;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "status")
	private Status status;
	
	@OneToMany(mappedBy = "student")
	private List<StudentGrades> studentGrades;
	
	// Constructors
	public Student() {}
	
	public Student(String firstName, String lastName, String middleName, String gender, Date birthDate,
			String degree, String address, String mobile, Level level, Semester semester, tblGPA cgpa, Status status) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.gender = gender;
		this.birthDate = birthDate;
		this.degree = degree;
		this.address = address;
		this.mobile = mobile;
		this.level = level;
		this.semester = semester;
		this.cgpa = cgpa;
		this.status = status;
	}

	// Getters and Setters
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public Semester getSemester() {
		return semester;
	}

	public void setSemester(Semester semester) {
		this.semester = semester;
	}

	public tblGPA getCgpa() {
		return cgpa;
	}

	public void setCgpa(tblGPA cgpa) {
		this.cgpa = cgpa;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<StudentGrades> getStudentGrades() {
		return studentGrades;
	}

	public void setStudentGrades(List<StudentGrades> studentGrades) {
		this.studentGrades = studentGrades;
	}
	
}
