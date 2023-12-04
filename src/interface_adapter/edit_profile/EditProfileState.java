package interface_adapter.edit_profile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EditProfileState {
    private String userid = null;
    private String nickname = null;
    private String pronouns = null;
    private List<String> personalityTags = new ArrayList<>();
    private String description = null;
    private List<String> coursesToTeach = new ArrayList<>();
    private String tutorAvailability;
    private Map<String, Integer> expectedWage = new HashMap<>();
    private List<String> preferredModeOfTeaching = new ArrayList<>();
    private List<String> coursesToLearn = new ArrayList<>();
    private Map<String, Integer> expectedPrice = new HashMap<>();
    private List<String> preferredModeOfLearning = new ArrayList<>();

    public EditProfileState(EditProfileState copy) {
        this.userid = copy.userid;
        this.nickname = copy.nickname;
        this.pronouns = copy.pronouns;
        this.personalityTags = copy.personalityTags;
        this.description = copy.description;
        this.coursesToTeach = copy.coursesToTeach;
        this.tutorAvailability = copy.tutorAvailability;
        this.expectedWage = copy.expectedWage;
        this.preferredModeOfTeaching = copy.preferredModeOfTeaching;
        this.coursesToLearn = copy.coursesToLearn;
        this.expectedPrice = copy.expectedPrice;
        this.preferredModeOfLearning = copy.preferredModeOfLearning;
    }
    public EditProfileState() {}

    public String getUserid() {
        return userid;
    }
    public String getNickname() {
        return nickname;
    }
    public String getPronouns() {
        return pronouns;
    }
    public List<String> getPersonalityTags() {
        return personalityTags;
    }
    public String getDescription() {
        return description;
    }
    public List<String> getCoursesToTeach() {
        return coursesToTeach;
    }
    public String getTutorAvailability() {
        return tutorAvailability;
    }
    public Map<String, Integer> getExpectedWage() {
        return expectedWage;
    }
    public List<String> getPreferredModeOfTeaching() {
        return preferredModeOfTeaching;
    }
    public List<String> getCoursesToLearn() {
        return coursesToLearn;
    }
    public Map<String, Integer> getExpectedPrice() {
        return expectedPrice;
    }
    public List<String> getPreferredModeOfLearning() {
        return preferredModeOfLearning;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public void setPronouns(String pronouns) {
        this.pronouns = pronouns;
    }
    public void setPersonalityTags(List<String> personalityTags) {
        this.personalityTags = personalityTags;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setCoursesToTeach(List<String> coursesToTeach) {
        this.coursesToTeach = coursesToTeach;
    }
    public void setTutorAvailability(String tutorAvailability) {
        this.tutorAvailability = tutorAvailability;
    }
    public void setExpectedWage(Map<String, Integer> expectedWage) {
        this.expectedWage = expectedWage;
    }
    public void setPreferredModeOfTeaching(List<String> preferredModeOfTeaching) {
        this.preferredModeOfTeaching = preferredModeOfTeaching;
    }
    public void setCoursesToLearn(List<String> coursesToLearn) {
        this.coursesToLearn = coursesToLearn;
    }
    public void setExpectedPrice(Map<String, Integer> expectedPrice) {
        this.expectedPrice = expectedPrice;
    }
    public void setPreferredModeOfLearning(List<String> preferredModeOfLearning) {
        this.preferredModeOfLearning = preferredModeOfLearning;
    }
}
