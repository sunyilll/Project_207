package entity;

import java.util.List;
import java.util.Map;

public interface Student {

    public Map<String, Course> getCoursesToLearn();
    public boolean addCourseToLearn(Course course);

    public boolean deleteCourseToLearn(Course course);

    public float getStudentRating();

    public boolean updateStudentRating(Integer rating);


    public Map<String, Integer> getExpectedPrice();
    public Integer getExpectedPrice(Course course);
    public boolean setExpectedPrice(Course course, Integer price);

    public List<String> preferredModeOfLearning();
    public String getUserID();

}
