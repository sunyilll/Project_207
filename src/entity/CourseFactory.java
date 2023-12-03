package entity;

import data_access.JsonUserDataAccessObject;

public class CourseFactory {
    private final JsonUserDataAccessObject userDataAccessObject;
    public CourseFactory(JsonUserDataAccessObject userDataAccessObject){
        this.userDataAccessObject = userDataAccessObject;
    }
    public Course create(String courseCode, String[] tutorids, String[] studentids){
        Course c = new Course(courseCode, "CourseNameNotImplemented");
        for (String id: tutorids){
            User u = userDataAccessObject.get(id);
            c.addTutor(u);
        }
        for (String id: studentids){
            User u = userDataAccessObject.get(id);
            c.addStudent(u);
        }
        return c;
    }
}
