package use_case.save_profile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SaveProfileInputData {
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

    public SaveProfileInputData(String userid, String nickname, String pronouns, List<String> personalityTags, String description, List<String> coursesToTeach, String tutorAvailability, Map<String, Integer> expectedWage, List<String> preferredModeOfTeaching, List<String> coursesToLearn, Map<String, Integer> expectedPrice, List<String> preferredModeOfLearning) {
        this.nickname = nickname;
        this.pronouns = pronouns;
        this.personalityTags = personalityTags;
        this.description = description;
        this.coursesToTeach = coursesToTeach;
        this.tutorAvailability = tutorAvailability;
        this.expectedWage = expectedWage;
        this.preferredModeOfTeaching = preferredModeOfTeaching;
        this.coursesToLearn = coursesToLearn;
        this.expectedPrice = expectedPrice;
        this.preferredModeOfLearning = preferredModeOfLearning;
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
