package use_case.edit_profile;

import entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EditProfileOutputData {
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


    public EditProfileOutputData(User user) {
        if (user == null) {
            return;
        }
        this.userid = user.getUserID();
        this.nickname = user.getNickname();
        this.pronouns = user.getPronouns();
        this.personalityTags = user.getPersonalityTags();
        this.description = user.getDescription();
        this.coursesToTeach = user.getCoursesToTeach();
        this.tutorAvailability = user.getTutorAvailability();
        this.expectedWage = user.getExpectedWage();
        this.preferredModeOfTeaching = user.getPreferredModeOfTeaching();
        this.coursesToLearn = user.getCoursesToLearn();
        this.expectedPrice = user.getExpectedPrice();
        this.preferredModeOfLearning = user.getPreferredModeOfLearning();
    }

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
}
