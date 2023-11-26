package main.java.use_case.search_course;

public class SearchCourseInputData {
    final private String courseCode;
    final private boolean searchForTutor;
    public SearchCourseInputData(String courseCode, boolean searchForTutor){
        this.courseCode = courseCode;
        this.searchForTutor = searchForTutor;
    }
    String getCourseCode(){return courseCode;}
    boolean searchTutor(){return searchForTutor;}
}
