package com.example.schoolmanagementsoftware.Service;

import com.example.schoolmanagementsoftware.Api.ApiException;
import com.example.schoolmanagementsoftware.Model.Course;
import com.example.schoolmanagementsoftware.Model.Teacher;
import com.example.schoolmanagementsoftware.Repository.CourseRepository;
import com.example.schoolmanagementsoftware.Repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;

    //CRUD
    //1.GET
    public List<Course> getAllCourses(){
        return courseRepository.findAll();
    }

    //2.POST
    public void addCourse(Integer teacher_id, Course course){
        Teacher teacher = teacherRepository.findTeacherById(teacher_id);
        if(teacher==null){
            throw new ApiException("Teacher Not Found.");
        }
        course.setTeacher(teacher);
        courseRepository.save(course);
    }

    //3.UPDATE
    public void updateCourse(Integer id, Course course){
        Course oldCourse = courseRepository.findCourseById(id);
        if (oldCourse==null){
            throw new ApiException("Course Not Found.");
        }
        oldCourse.setName(course.getName());
        courseRepository.save(oldCourse);
    }

    //4.DELETE
    public void deleteCourse(Integer id){
        Course oldCourse = courseRepository.findCourseById(id);
        if (oldCourse==null){
            throw new ApiException("Course Not Found.");
        }
        courseRepository.delete(oldCourse);
    }

    //Extra Endpoint
    //Create endpoint that take course id and return the teacher name for that class
    public String getTeacherName(Integer course_id){
        Course course = courseRepository.findCourseById(course_id);
        if (course==null) {
            throw new ApiException("Course Not Found.");
        }
        return course.getTeacher().getName();
    }
}