package entity;

import java.util.List;
import java.util.Map;

public interface Tutor {
    public List<String> getCoursesToTeach();
    public boolean addCourseToTeach(String courseCode);

    public boolean deleteCourseToTeach(String courseCode);

    public String getTutorAvailability();  // return available hours

    public float getTutorRating();
    public boolean updateTutorRating(Integer rating);

    public Map<String, Integer> getExpectedWage();
    public Integer getExpectedWage(String courseCode);
    public boolean setExpectedWage(String courseCode, Integer wage);

    public List<String> getPreferredModeOfTeaching();
    public boolean setPreferredModeOfTeaching(String mode);
    public String getUserID();
}
