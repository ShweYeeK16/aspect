package com.aop.aspect.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aop.aspect.domain.Courses;
import com.aop.aspect.dtos.CourseCreateDTO;
import com.aop.aspect.dtos.CourseUpdateDTO;
import com.aop.aspect.service.CourseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/courses")
@Api(tags = "Course Demo", value = "Course Demo Api")
public class CourseApiController {

	@Autowired
	private CourseService courseService;

	@CrossOrigin("*")
	@PostMapping("/create")
	@ApiOperation(value = "Create Course", notes = "Create course api")
	public ResponseEntity<Courses> save(@RequestBody CourseCreateDTO courseDTO) {

		Courses responseBody = this.courseService.createCourse(courseDTO);
		return ResponseEntity.ok(responseBody);
	}

	@CrossOrigin("*")
	@PostMapping("/update")
	@ApiOperation(value = "Update Course", notes = "Update course api")
	public ResponseEntity<Courses> update(@RequestBody CourseUpdateDTO courseUpdateDTO) {

		Courses responseBody = this.courseService.updateCourse(courseUpdateDTO);
		return ResponseEntity.ok(responseBody);
	}

	@CrossOrigin("*")
	@GetMapping("/{id}")
	@ApiOperation(value = "Retrieve Course By Id", notes = "Retrieve course By Id api")
	public ResponseEntity<Courses> getById(@PathVariable(name = "id") Integer id) {

		try {
			Courses responseBody = this.courseService.findById(id);
			return ResponseEntity.ok(responseBody);
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@CrossOrigin("*")
	@DeleteMapping("/delete")
	@ApiOperation(value = "Delete Course By Id", notes = "Delete Course by id api")
	public ResponseEntity<Integer> deleteById(Integer id) {

		this.courseService.deleteById(id);
		return ResponseEntity.ok(id);
	}

	@CrossOrigin("*")
	@GetMapping("/all")
	@ApiOperation(value = "Retrieve All Courses", notes = "Retrieve all courses")
	public ResponseEntity<List<Courses>> getAllCourses() {

		List<Courses> responseBody = this.courseService.findAll();
		return ResponseEntity.ok(responseBody);
	}

}
