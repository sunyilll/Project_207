package entity;

import data_access.FileUserDataAccessObject;

public class CourseFactory {
    private final FileUserDataAccessObject fileUserDataAccessObject;
    public CourseFactory(FileUserDataAccessObject fileUserDataAccessObject){
        this.fileUserDataAccessObject = fileUserDataAccessObject;
    }
    public Course create(String courseCode, String[] tutorids, String[] studentids){
        Course c = new Course(courseCode, "CourseNameNotImplemented");
        for (String id: tutorids){
            User u = fileUserDataAccessObject.get(id);
            c.addTutor(u);
        }
        for (String id: studentids){
            User u = fileUserDataAccessObject.get(id);
            c.addStudent(u);
        }
        return c;
    }
}
