package main.java.entity;

import main.java.data_access.FileCourseDataAccessObject;

public class CourseFactory {
    private final FileUserDataAccessObject fileUserDataAccessObject;
    public CourseFactory(FileUserDataAccessObject fileUserDataAccessObject){
        this.fileUserDataAccessObject = fileUserDataAccessObject;
    }
    public Course create(String courseCode, String[] tutorids, String[] studentids){
        Course c = new Course(courseCode, "CourseNameNotImplemented");
        //todo: courseName not implemented
    }
}
