package main.java.interface_adapter.search_course;

public class SearchCourseState {
    private boolean searchForTutor;
    private String courseCode;
    private String courseCodeError = null;
    public SearchCourseState(){}
    public void setSearchForTutor(boolean bool){this.searchForTutor = bool;}
    public void setCourseCode(String courseCode){this.courseCode = courseCode;}

    public void setCourseCodeError(String courseCodeError){this.courseCodeError = courseCodeError;}
    public String getCourseCodeError(){return this.courseCodeError;}
    public String getCourseCode(){return this.courseCode;}
    public boolean getSearchForTutor(){return this.searchForTutor;}
}
