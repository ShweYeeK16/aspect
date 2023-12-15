package com.aop.aspect.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aop.aspect.domain.Courses;
import com.aop.aspect.dtos.CourseCreateDTO;
import com.aop.aspect.dtos.CourseUpdateDTO;
import com.aop.aspect.repo.CourseRepo;

@Service
public class CourseService {

	@Autowired
	private CourseRepo courseRepo;

	public Courses createCourse(CourseCreateDTO courseDTO) {

		Courses course = new Courses();
		course.setName(courseDTO.getName());
		course.setPrice(courseDTO.getPrice());
		course.setDescription(courseDTO.getDescription());
		Courses result = this.courseRepo.save(course);

		return result;
	}

	public Courses updateCourse(CourseUpdateDTO courseUpdateDTO) {

		Optional<Courses> course = this.courseRepo.findById(courseUpdateDTO.getId());
		course.get().setId(courseUpdateDTO.getId());
		course.get().setName(courseUpdateDTO.getName());
		course.get().setPrice(courseUpdateDTO.getPrice());
		course.get().setDescription(courseUpdateDTO.getDescription());
		Courses result = this.courseRepo.save(course.get());

		return result;
	}

	public Integer deleteById(Integer id) {

		this.courseRepo.deleteById(id);
		return id;

	}

	public Courses findById(Integer id) {

		Optional<Courses> course = this.courseRepo.findById(id);
		return course.get();
	}

	public List<Courses> findAll() {

		List<Courses> courses = this.courseRepo.findAll();
		return courses;
	}

}
