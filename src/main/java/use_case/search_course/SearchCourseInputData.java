package main.java.use_case.search_course;

public class SearchCourseInputData {
    final private String courseCode;
    final private boolean searchForTutor;
    final private String userID;
    public SearchCourseInputData(String courseCode, boolean searchForTutor, String userID){
        this.courseCode = courseCode;
        this.searchForTutor = searchForTutor;
        this.userID = userID;
    }
    String getCourseCode(){return courseCode;}
    String getUserID(){return userID;}
    boolean searchTutor(){return searchForTutor;}
}
