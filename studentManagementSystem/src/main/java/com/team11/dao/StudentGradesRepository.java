package com.team11.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.team11.entity.StudentGrades;

@Repository
public interface StudentGradesRepository extends JpaRepository<StudentGrades, Integer>{
	
	@Query("select sg from StudentGrades sg where sg.studentID = :studentId")
	public ArrayList<StudentGrades> findStudentGradesByStudentID(String studentId);

	@Query("select sum(c.courseUnit) from StudentGrades sg join sg.course c where sg.studentID = :studentId and sg.grade != 'N/A'")
	public float getTotalUnits(String studentId);	
	
	@Query("select sg from StudentGrades sg where sg.courseID = :courseId")
	public List<StudentGrades> getStudentGradesByCourseID(int courseId);
	
	@Query("select sg from StudentGrades sg where sg.studentID = :studentId and sg.courseID = :courseId")
	public StudentGrades findStudentGradesByStudentAndCourseID(String studentId, int courseId);
	
	@Modifying
	@Query("update StudentGrades sg set sg.grade = :grade where sg.id = :id")
	public void updateGrade(String grade, int id);
	
	@Query("select sg FROM StudentGrades sg WHERE sg.courseID = :courseId "
			+ "and concat(sg.student.firstName, ' ', sg.student.lastName) like %:search% order by concat(sg.student.firstName, ' ', sg.student.lastName)")
	public Page<StudentGrades> searchAndPaginate(String search, Pageable pageable, int courseId);
}