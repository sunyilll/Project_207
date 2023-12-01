package entity;

import java.util.List;
import java.util.Map;

public interface Student {

    public List<String> getCoursesToLearn();
    public boolean addCourseToLearn(String courseCode);

    public boolean deleteCourseToLearn(String courseCode);

    public float getStudentRating();

    public boolean updateStudentRating(Integer rating);

    public Map<String, Integer> getExpectedPrice();
    public Integer getExpectedPrice(String courseCode);
    public boolean setExpectedPrice(String courseCode, Integer price);
    public List<String> getPreferredModeOfLearning();
    public boolean setPreferredModeOfLearning(String mode);
    public String getUserID();

}
