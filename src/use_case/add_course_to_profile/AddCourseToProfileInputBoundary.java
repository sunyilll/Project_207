package use_case.add_course_to_profile;

public interface AddCourseToProfileInputBoundary {
    void execute(boolean addToTutor, String courseCode);
}
