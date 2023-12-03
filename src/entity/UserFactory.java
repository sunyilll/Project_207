package entity;

import java.util.List;
import java.util.Map;

public class UserFactory {
    /**
     * Requires: password is valid.
     * @param userid
     * @param nickname
     * @param password
     * @return
     */

    public User create(String userid, String nickname, String password) {
        return new User(userid, nickname, password);
    }

    public User create(String userid, String nickname, String password, String pronouns, String description, String tutorAvailability, List<String> personalityTag, List<String> coursesToTeach, List<String> coursesToLearn, List<String> preferredModeOfLearning, List<String> preferredModeOfTeaching, Map<String, Integer> expectedWage, Map<String, Integer> expectedPrice) {

        User user = new User(userid, nickname, password);
        user.setDescription(description);
        user.setPronouns(pronouns);
        user.setTutorAvailability(tutorAvailability);
        for (String tag : personalityTag) {
            user.addPersonalityTag(tag);
        }
        for (String course : coursesToLearn) {
            user.addCourseToTeach(course);
        }
        for (String course : coursesToTeach) {
            user.addCourseToTeach(course);
        }
        for (String mode : preferredModeOfLearning) {
            user.addPreferredModeOfLearning(mode);
        }
        for (String mode : preferredModeOfTeaching) {
            user.addPreferredModeOfTeaching(mode);
        }
        for (String course : expectedWage.keySet()) {
            user.setExpectedWage(course, expectedWage.get(course));
        }
        for (String course : expectedPrice.keySet()) {
            user.setExpectedPrice(course, expectedPrice.get(course));
        }
        return user;
    }
}
