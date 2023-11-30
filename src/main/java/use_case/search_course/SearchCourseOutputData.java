package main.java.use_case.search_course;

import main.java.entity.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SearchCourseOutputData {
    private String courseCode;
    Map<String, Map<String, String>> users = new HashMap<>();
    Map<String, List<String>> users_tags = new HashMap<>();
    public SearchCourseOutputData(List<User> users, boolean searchForTutors, String courseCode){
        this.courseCode = courseCode;
        for (User u: users){
            HashMap<String, String> userInfo = new HashMap<>();
            userInfo.put("name", u.getNickname());
            if (searchForTutors){userInfo.put("rating", String.valueOf(u.getTutorRating()));}
            else {userInfo.put("rating", String.valueOf(u.getStudentRating()));}
            //todo: add profile picture
            this.users_tags.put(u.getUserID(), u.getPersonalityTags());
            this.users.put(u.getUserID(), userInfo);
        }
    }
    public Map<String, Map<String, String>> getUsersInfo(){
        return users;
    }
    public Map<String, List<String>> getUsers_tags(){return users_tags;}

    public String getCourseCode() {
        return courseCode;
    }
}
