package entity;

import java.util.List;
import java.util.Map;

public class UserBuilder {
    User user;
    public UserBuilder(String userID, String nickname, String password)
    {
        user = new User(userID, nickname, password);
    }
    public User getUser() {
        return user;
    }
    public void setDescription(String description) {
        user.setDescription(description);
    }
    public void setPronouns(String pronouns) {
        user.setPronouns(pronouns);
    }
    public void setTutorAvailability(String tutorAvailability) {
        user.setTutorAvailability(tutorAvailability);
    }
    public void addPersonalityTag(List<String> personalityTag) {
        for (String tag : personalityTag) {
            user.addPersonalityTag(tag);
        }
    }
    public void addCoursesToLearn(List<String> coursesToLearn) {
        for (String course : coursesToLearn) {
            user.addCourseToTeach(course);
        }
    }
    public void addCoursesToTeach(List<String> coursesToTeach) {
        for (String course : coursesToTeach) {
            user.addCourseToTeach(course);
        }
    }
    public void addPreferredModeOfLearning(List<String> preferredModeOfLearning) {
        for (String mode : preferredModeOfLearning) {
            user.addPreferredModeOfLearning(mode);
        }
    }
    public void addPreferredModeOfTeaching(List<String> preferredModeOfTeaching) {
        for (String mode : preferredModeOfTeaching) {
            user.addPreferredModeOfTeaching(mode);
        }
    }
    public void setExpectedWage(Map<String, Integer> expectedWage) {
        for (String course : expectedWage.keySet()) {
            user.setExpectedWage(course, expectedWage.get(course));
        }
    }
    public void setExpectedPrice(Map<String, Integer> expectedPrice) {
        for (String course : expectedPrice.keySet()) {
            user.setExpectedPrice(course, expectedPrice.get(course));
        }
    }
}
