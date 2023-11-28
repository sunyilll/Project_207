package main.java.interface_adapter.search_course_result;

import main.java.interface_adapter.search_course.SearchCoursePresenter;

import java.util.List;
import java.util.Map;

public class SearchCourseResultState {
    private Map<String, Map<String, String>> resultUsers;
    private Map<String, List<String>> resultUserTags;
    private String courseCode;
    public SearchCourseResultState(){}
    //todo: implement me
    public void setResultUsers(Map<String, Map<String, String>> users){
        this.resultUsers = users;
    }
    public void setResultUserTags(Map<String, List<String>> tags){
        this.resultUserTags = tags;
    }
    public void setCourseCode(String code){
        this.courseCode = code;
    }


}
