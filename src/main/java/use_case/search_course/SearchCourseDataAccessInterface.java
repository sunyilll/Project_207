package main.java.use_case.search_course;
import main.java.entity.Student;
import main.java.entity.Tutor;

import java.util.List;

public interface SearchCourseDataAccessInterface {
    List<Tutor> getTutorOfCourse(String courseCode);
    List<Student> getStudentOfCourse(String courseCode);
    boolean hasCourse(String couseCode);
}
