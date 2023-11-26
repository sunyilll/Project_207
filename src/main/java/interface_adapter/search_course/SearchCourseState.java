package main.java.interface_adapter.search_course;

public class SearchCourseState {
    private boolean isStudent;
    private String courseCode;
    private String courseCodeError = null;
    public SearchCourseState(){}
    public void setIsStudent(boolean bool){this.isStudent = bool;}
    public void setCourseCode(String courseCode){this.courseCode = courseCode;}

    public void setCourseCodeError(String courseCodeError){this.courseCodeError = courseCodeError;}
    public String getCourseCodeError(){return this.courseCodeError;}
    public String getCourseCode(){return this.courseCode;}
}
