package entity;

public class UserBuilder {
    User user;
    public UserBuilder() {}
    public void create(String userID, String nickname, String password)
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
    public void addPersonalityTag(String personalityTag) {
        user.addPersonalityTag(personalityTag);
    }
    public void addCoursesToLearn(String course) {
        user.addCourseToLearn(course);
    }
    public void addCoursesToTeach(String course) {
        user.addCourseToTeach(course);
    }
    public void addPreferredModeOfLearning(String modeOfLearning) {
        user.addPreferredModeOfLearning(modeOfLearning);
    }
    public void addPreferredModeOfTeaching(String modeOfTeaching) {
        user.addPreferredModeOfTeaching(modeOfTeaching);
    }
    public void addStudentRating(int rating) {
        user.updateStudentRating(rating);
    }
    public void addTutorRating(int rating) {
        user.updateTutorRating(rating);
    }
    public void setExpectedWage(String course, int expectedWage) {
        user.setExpectedWage(course, expectedWage);
    }
    public void setExpectedPrice(String course, int expectedPrice) {
        user.setExpectedPrice(course, expectedPrice);
    }
}
