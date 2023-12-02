package entity;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class User implements Student, Tutor{

    public final String userid;
    public String nickname;
    private String password;
    private String pronouns;
    private List<String> personalityTag = new ArrayList<>();
    private String profileURL = null;
    private String description = null;

    // Variables for Tutor
    private List<Integer> tutorRatings = new ArrayList<>();
    private float tutorRating; // the average of all ratings
    private List<String> coursesToTeach = new ArrayList<>();
    private String tutorAvailability;	// available hours
    private Map<String, Integer> expectedWage = new HashMap<>();  // mapping of course to expected wage
    private List<String> preferredModeOfTeaching = new ArrayList<>();

    // Variables for Student
    private List<Integer> studentRatings = new ArrayList<>();
    private float studentRating;
    private List<String> coursesToLearn = new ArrayList<>();
    private Map<String, Integer> expectedPrice = new HashMap<>();
    // how much the user would like to pay for each course
    private List<String> preferredModeOfLearning = new ArrayList<>();

    public User(String userid, String nickname, String password){
        this.userid = userid;
        this.nickname = nickname;
        this.password = password;
    }
    @Override
    public String getUserID() {
        return this.userid;
    }

    public String getNickname() {
        return this.nickname;
    }

    public String getPassword() {
        return this.password;
    }

    public boolean setNickname(String nickname) {
        this.nickname = nickname;
        return true;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public String getPronouns() {
        return this.pronouns;
    }

    public boolean setPronouns(String pronouns) {
        this.pronouns = pronouns;
        return true;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean setDescription(String description) {
        this.description = description;
        return true;
    }

    public List<String> getPersonalityTags() {
        return this.personalityTag;
    }

    public boolean addPersonalityTag(String tag) {
        this.personalityTag.add(tag);
        return true;
    }

    // Tutor Methods
    // Courses to teach
    public List<String> getCoursesToTeach(){
        return this.coursesToTeach;
    }

    public boolean addCourseToTeach(String courseCode){
        this.coursesToTeach.add(courseCode);
        return true;
    }

    public boolean deleteCourseToTeach(String courseCode){
        this.coursesToTeach.remove(courseCode);
        return true;
    }

    public boolean setTutorAvailability(String availability){
        this.tutorAvailability = availability;
        return true;
    }
    public String getTutorAvailability(){
        return this.tutorAvailability;
    }

    // Tutor rating
    public float getTutorRating(){
        return this.tutorRating;
    }

    public boolean updateTutorRating(Integer rating){
        // check if rating is valid: 0<= rating <= 5
        if (rating < 0 || rating > 5) {
            return false;
        }
        this.tutorRatings.add(rating);
        this.tutorRating = (float) tutorRatings.stream().mapToInt(Integer::intValue).sum() / tutorRatings.size();
        return true;
    }

    // Tutor wage
    public Map<String, Integer> getExpectedWage(){
        return this.expectedWage;
    }

    public Integer getExpectedWage(String courseCode){
        return this.expectedWage.get(courseCode);
    }

    public boolean setExpectedWage(String courseCode, Integer wage){
        this.expectedWage.put(courseCode, wage);
        return true;
    }

    public List<String> getPreferredModeOfTeaching(){
        return this.preferredModeOfTeaching;
    }

    @Override
    public boolean setPreferredModeOfTeaching(String mode) {
        preferredModeOfTeaching.add(mode);
        return true;
    }

    // Student Methods
    // Courses to learn
    public List<String> getCoursesToLearn(){
        return this.coursesToLearn;
    }

    public boolean addCourseToLearn(String courseCode){
        this.coursesToLearn.add(courseCode);
        return true;
    }

    public boolean deleteCourseToLearn(String courseCode){
        this.coursesToLearn.remove(courseCode);
        return true;
    }

    // Student rating
    public float getStudentRating(){
        return this.studentRating;
    }

    public boolean updateStudentRating(Integer rating){
        // check if rating is valid: 0<= rating <= 5
        if (rating < 0 || rating > 5) {
            return false;
        }
        this.studentRatings.add(rating);
        this.studentRating = (float) studentRatings.stream().mapToInt(Integer::intValue).sum() / studentRatings.size();
        return true;
    }

    // Student price
    public Map<String, Integer> getExpectedPrice(){
        return this.expectedPrice;
    }
    public Integer getExpectedPrice(String courseCode){
        return this.expectedPrice.get(courseCode);
    }
    public boolean setExpectedPrice(String courseCode, Integer price){
        this.expectedPrice.put(courseCode, price);
        return true;
    }

    @Override
    public List<String> getPreferredModeOfLearning() {
        return preferredModeOfLearning;
    }

    @Override
    public boolean setPreferredModeOfLearning(String mode) {
        preferredModeOfLearning.add(mode);
        return true;
    }
    public String getProfileURL(){return this.profileURL;}
}
