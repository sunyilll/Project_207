package entity;
import use_case.GetUserDataAccessInterface;

import java.util.List;

public class CourseFactory {
    private final GetUserDataAccessInterface jsonUserDataAccessObject;
    public CourseFactory(GetUserDataAccessInterface jsonUserDataAccessObject){
        this.jsonUserDataAccessObject = jsonUserDataAccessObject;
    }
    public Course create(String courseCode, List<String> tutorids, List<String> studentids){
        Course c = new Course(courseCode, "CourseNameNotImplemented in CourseFactory.java");
        for (String id: tutorids){
            User u = jsonUserDataAccessObject.get(id);
            c.addTutor(u);
        }
        for (String id: studentids){
            User u = jsonUserDataAccessObject.get(id);
            c.addStudent(u);
        }
        return c;
    }
}
