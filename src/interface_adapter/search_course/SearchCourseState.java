package interface_adapter.search_course;

public class SearchCourseState {
    private String userID;
    private boolean searchForTutor = true;
    private String courseCode;
    private String error = null;
    public SearchCourseState(){}
    public void setSearchForTutor(boolean bool){this.searchForTutor = bool;}
    public void setCourseCode(String courseCode){this.courseCode = courseCode;}
    public void setUserID(String userID){this.userID =userID;}

    public void setError(String courseCodeError){this.error = courseCodeError;}
    public String getError(){return this.error;}
    public String getCourseCode(){return this.courseCode;}
    public boolean getSearchForTutor(){return this.searchForTutor;}

    public String getUserID() { return this.userID;}
}
