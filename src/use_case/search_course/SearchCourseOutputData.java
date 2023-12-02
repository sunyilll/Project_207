package use_case.search_course;

import entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SearchCourseOutputData {
    private String courseCode;
    boolean searchForTutor;
    List<String> sortedUserIds = new ArrayList<>();
    Map<String, Map<String, String>> users = new HashMap<>();
    Map<String, List<String>> users_tags = new HashMap<>();
    public SearchCourseOutputData(List<Map.Entry<User, Float>> sortedCandidates, boolean searchForTutors, String courseCode){
        this.courseCode = courseCode;
        this.searchForTutor = searchForTutors;
        for (Map.Entry<User, Float> entry: sortedCandidates){
            User user = entry.getKey();
            sortedUserIds.add(user.getUserID());
            HashMap<String, String> userInfo = new HashMap<>();
            userInfo.put("name", user.getNickname());
            if (searchForTutors){userInfo.put("rating", String.valueOf(user.getTutorRating()));}
            else {userInfo.put("rating", String.valueOf(user.getStudentRating()));}
            userInfo.put("profileURL", user.getProfileURL());
            //todo: add profile picture
            this.users_tags.put(user.getUserID(), user.getPersonalityTags());
            this.users.put(user.getUserID(), userInfo);
        }
    }
    public List<String> getSortedUserIds(){return sortedUserIds;}
    public Map<String, Map<String, String>> getUsersInfo(){
        return users;
    }
    public Map<String, List<String>> getUsers_tags(){return users_tags;}
    public String getCourseCode() {
        return courseCode;
    }
    public boolean getSearchForTutor(){return searchForTutor;}
}
