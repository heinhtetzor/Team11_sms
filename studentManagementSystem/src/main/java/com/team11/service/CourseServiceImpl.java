package com.team11.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.team11.dao.CourseRepository;
import com.team11.entity.Course;

@Service
public class CourseServiceImpl implements CourseService{
	
	private CourseRepository courseRepository;
	
	@Autowired
	public void setCourseRepository(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}
	
	@Override
	@Transactional
	public List<Course> getCourses() {
		return courseRepository.findAll();
	}

	@Override
	@Transactional
	public Course saveCourse(Course course) {
		courseRepository.save(course);
		return course;
	}

	@Override
	@Transactional
	public Course getCourse(int id) {
		return courseRepository.getOne(id);
	}

	@Override
	@Transactional
	public void deleteCourse(int id) {
		courseRepository.deleteById(id);
	}
	
	@Override
	@Transactional
	public List<Course> getAvailableCourses() {
		return courseRepository.getAvailableCourses();
	}

	@Override
	@Transactional
	public List<Course> getCourseByFacultyID(String id) {
		return courseRepository.getCoursesByFacultyID(id);
	}

	@Override
	@Transactional
	public List<Course> getActiveCourses(String id) {
		return courseRepository.getActiveCourses(id);
	}
	
	@Override
	@Transactional
	public Page<Course> searchAndPaginate(String search, Pageable pageable) {
		return courseRepository.searchAndPaginate(search, pageable);
	}

}
