package interface_adapter.search_course_result;

import java.util.List;
import java.util.Map;

public class SearchCourseResultState {
    private Map<String, Map<String, String>> resultUsers;
    private Map<String, List<String>> resultUserTags;
    private String courseCode;
    private boolean searchForTutor;
    public Integer numbersResults = 0;
    public SearchCourseResultState(){}
    //todo: implement me
    public void setResultUsers(Map<String, Map<String, String>> users){
        this.resultUsers = users;
        this.numbersResults = users.size();
    }
    public void setResultUserTags(Map<String, List<String>> tags){
        this.resultUserTags = tags;
        assert tags.size() == this.resultUsers.size();
    }
    public void setCourseCode(String code){
        this.courseCode = code;
    }
    public void setSearchForTutor(boolean bool){this.searchForTutor = bool;}
    public boolean getSearchForTutor(){return searchForTutor;}
    public String getCourseCode(){return courseCode;}
    public Integer getNumbersResults(){return numbersResults;}
    public Map<String, Map<String, String>> getResultUsers(){return resultUsers;}

    public Map<String, List<String>> getResultUserTags() {
        return resultUserTags;
    }
}
