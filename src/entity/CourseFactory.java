package entity;
import data_access.JsonUserDataAccessObject;

import java.util.List;

public class CourseFactory {
    private final JsonUserDataAccessObject jsonUserDataAccessObject;
    public CourseFactory(JsonUserDataAccessObject jsonUserDataAccessObject){
        this.jsonUserDataAccessObject = jsonUserDataAccessObject;
    }
    public Course create(String courseCode, List<String> tutorids, List<String> studentids){
        Course c = new Course(courseCode, "CourseNameNotImplemented");
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
