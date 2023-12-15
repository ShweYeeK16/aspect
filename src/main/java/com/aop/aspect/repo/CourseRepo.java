package com.aop.aspect.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aop.aspect.domain.Courses;

@Repository
public interface CourseRepo extends JpaRepository<Courses, Integer> {

}
