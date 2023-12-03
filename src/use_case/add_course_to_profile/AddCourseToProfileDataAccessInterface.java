package use_case.add_course_to_profile;
import entity.User;

public interface AddCourseToProfileDataAccessInterface {
    void addStudent(User user, String courseCode);
    void addTutor(User user, String courseCode);
}
