package main.java.entity;

import java.util.List;
import java.util.Map;

public interface Tutor {
    public Map<String, Course> getCoursesToTeach();
    public boolean addCourseToTeach(Course course);

    public boolean deleteCourseToTeach(Course course);

    public List<String> getTutorAvailability();  // return available hours

    public float getTutorRating();
    public boolean updateTutorRating(Integer rating);

    public Map<String, Integer> getExpectedWage();
    public Integer getExpectedWage(Course course);
    public boolean setExpectedWage(Course course, Integer wage);

    public List<String> getPreferredModeOfTeaching();
}
