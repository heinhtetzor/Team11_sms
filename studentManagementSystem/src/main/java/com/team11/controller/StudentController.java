package com.team11.controller;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.team11.entity.Student;
import com.team11.service.StudentService;
import com.team11.service.StudentServiceImpl;
import com.team11.service.UserService;
import com.team11.service.UserServiceImpl;

@Controller
@RequestMapping("/student")
public class StudentController {
	
	// Inject student service
	private StudentService studentService;
	@Autowired
	public void setStudentService(StudentServiceImpl studentService) {
		this.studentService = studentService;
	}
	
	// Inject user Service
	private UserService userService;
	@Autowired
	public void setUserService(UserServiceImpl userService) {
		this.userService = userService;
	}
	
// *** ADMIN ROLE *** //
	
	//show paginated list of students
	@GetMapping("/admin/list")
	public String search(Model model) {
		
		model.addAttribute("students", studentService.getStudents());
		
		return "admin/student-list";
	}
	
	//For all users to save student details
	@PostMapping("/admin/save")
	public String add(@Valid Student student, BindingResult result, Model model) {
		
		//Don't allow user to add student if there are any form validation errors
		if(result.hasErrors()) {
			model.addAttribute("levels", studentService.getLevels());
			model.addAttribute("semesters", studentService.getSemesters());
			return "admin/student-form";
		}
		
		// save the student using our service
		studentService.saveStudent(student);
		
		return "redirect:/student/admin/list";
	}	
	
	
	
	// For admin to update student details, level, semester & status
	@GetMapping("/admin/update/{id}")
	public String update(@PathVariable("id") String theId, Model model) {
		// get the student from our service
		Student theStudent = studentService.getStudent(theId);
		
		if(theStudent == null) {
			return "redirect:/student/admin/list";
		}
		
		model.addAttribute("student", theStudent);
		model.addAttribute("levels", studentService.getLevels());
		model.addAttribute("semesters", studentService.getSemesters());
		
		return "admin/student-form";
	}
	
	// For admin to remove students
	@GetMapping("/admin/delete/{id}")
	public String delete(@PathVariable("id") String theId, RedirectAttributes redirectAttributes) {
		try {
			studentService.deleteStudent(theId);
			userService.deactivateUser(theId);
			return "redirect:/student/admin/list";
		} catch (DataIntegrityViolationException e) {
			redirectAttributes.addFlashAttribute("error", "Cannot delete student [" + e.getClass().getSimpleName() + "]");
			return "redirect:/student/admin/list";
		}
	}
	
}

