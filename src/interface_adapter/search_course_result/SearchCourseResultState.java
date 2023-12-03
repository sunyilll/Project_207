package interface_adapter.search_course_result;

import java.util.List;
import java.util.Map;

public class SearchCourseResultState {
    private Map<String, Map<String, String>> resultUsers;
    private Map<String, List<String>> resultUserTags;
    private List<String> sortedIds;
    private String courseCode;
    private boolean searchForTutor;
    private String error;
    public Integer numbersResults = 0;
    public SearchCourseResultState(){}
    //todo: implement me
    public void setSortedIds(List<String> ids){
        this.sortedIds = ids;
        this.numbersResults = ids.size();
        System.out.println("Number of Result:" + numbersResults);
    }
    public List<String> getSortedIds(){return this.sortedIds;}
    public void setResultUsers(Map<String, Map<String, String>> users){
        this.resultUsers = users;
    }
    public void setResultUserTags(Map<String, List<String>> tags){
        this.resultUserTags = tags;
        assert tags.size() == this.resultUsers.size();
    }
    public void setCourseCode(String code){
        this.courseCode = code;
    }
    public void setSearchForTutor(boolean bool){this.searchForTutor = bool;}
    public void setError(String error){this.error = error;}
    public String getError(){return error;}
    public boolean getSearchForTutor(){return searchForTutor;}
    public String getCourseCode(){return courseCode;}
    public Integer getNumbersResults(){return numbersResults;}
    public Map<String, Map<String, String>> getResultUsers(){return resultUsers;}

    public Map<String, List<String>> getResultUserTags() {
        return resultUserTags;
    }
}
