package use_case.search_course;
import entity.Student;
import entity.Tutor;

import java.util.List;

public interface SearchCourseDataAccessInterface {
    List<Tutor> getTutorOfCourse(String courseCode);
    List<Student> getStudentOfCourse(String courseCode);
    boolean hasCourse(String couseCode);
}
