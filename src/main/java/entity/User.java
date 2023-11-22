package main.java.entity;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class User implements Student, Tutor{

    private final String userid;
    private String nickname;
    private String password;
    private String gender;
    private Map<String, User> following;
    private Map<String, User> follower;
    private List<String> personalityTag = new ArrayList<>();
    private String profileURL = null;

    // Variables for Tutor
    public List<Integer> tutorRatings = new ArrayList<>();
    public float tutorRating; // the average of all ratings
    public Map<String, Course> coursesToTeach = new HashMap<>();
    public List<String> tutorAvailability = new ArrayList<>();	// available hours
    public Map<String, Integer> expectedWage = new HashMap<>();  // mapping of course to expected wage
    public List<String> preferredModeOfTeaching = new ArrayList<>();

    // Variables for Student
    public List<Integer> studentRatings = new ArrayList<>();
    public float studentRating;
    public Map<String, Course> coursesToLearn = new HashMap<>();
    public Map<String, Integer> expectedPrice = new HashMap<>();
    // how much the user would like to pay for each course
    public List<String> preferredModeOfLearning = new ArrayList<>();

    public User(String userid, String nickname, String password){
        this.userid = userid;
        this.nickname = nickname;
        this.password = password;
    }

    public String getUserID() {
        return this.userid;
    }

    public String getNickname() {
        return this.nickname;
    }

    public boolean setNickname(String nickname) {
        this.nickname = nickname;
        return true;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public boolean setPassword(String password) {
        this.password = password;
        return true;
    }

    public String getGender() {
        return this.gender;
    }

    public boolean setGender(String gender) {
        this.gender = gender;
        return true;
    }

    public boolean isFollwing(User user) {
        return this.following.containsKey(user.getUserID());
    }

    public boolean isFollowedBy(User user) {
        return this.follower.containsKey(user.getUserID());
    }

    public boolean follow(User user) {
        this.following.put(user.getUserID(), user);
        user.follower.put(this.getUserID(), this);
        // TODO check if following is allowed?
        return true;
    }

    // Tutor Methods
    // Courses to teach
    public Map<String, Course> getCoursesToTeach(){
        return this.coursesToTeach;
    }

    public boolean addCourseToTeach(Course course){
        this.coursesToTeach.put(course.courseCode, course);
        return true;
    }

    public boolean deleteCourseToTeach(Course course){
        this.coursesToTeach.remove(course.courseCode);
        return true;
    }

    public List<String> getTutorAvailability(){
        return this.tutorAvailability;
    }

    // Tutor rating
    public float getTutorRating(){
        return this.tutorRating;
    }

    public boolean updateTutorRating(Integer rating){
        // TODO check if rating is valid
        this.tutorRatings.add(rating);
        this.tutorRating = (float) tutorRatings.stream().mapToInt(Integer::intValue).sum() / tutorRatings.size();
        return true;
    }

    // Tutor wage
    public Map<String, Integer> getExpectedWage(){
        return this.expectedWage;
    }

    public Integer getExpectedWage(Course course){
        return this.expectedWage.get(course.courseCode);
    }

    public boolean setExpectedWage(Course course, Integer wage){
        this.expectedWage.put(course.courseCode, wage);
        return true;
    }

    public List<String> getPreferredModeOfTeaching(){
        return this.preferredModeOfTeaching;
    }

    // Student Methods
    // Courses to learn
    public Map<String, Course> getCoursesToLearn(){
        return this.coursesToLearn;
    }

    public boolean addCourseToLearn(Course course){
        this.coursesToLearn.put(course.courseCode, course);
        return true;
    }

    public boolean deleteCourseToLearn(Course course){
        this.coursesToLearn.remove(course.courseCode);
        return true;
    }

    // Student rating
    public float getStudentRating(){
        return this.studentRating;
    }

    public boolean updateStudentRating(Integer rating){
        // TODO check if rating is valid
        this.studentRatings.add(rating);
        this.studentRating = (float) studentRatings.stream().mapToInt(Integer::intValue).sum() / studentRatings.size();
        return true;
    }

    // Student price
    public Map<String, Integer> getExpectedPrice(){
        return this.expectedPrice;
    }
    public Integer getExpectedPrice(Course course){
        return this.expectedPrice.get(course.courseCode);
    }
    public boolean setExpectedPrice(Course course, Integer price){
        this.expectedPrice.put(course.courseCode, price);
        return true;
    }

    public List<String> preferredModeOfLearning(){
        return this.preferredModeOfLearning;
    }

}
