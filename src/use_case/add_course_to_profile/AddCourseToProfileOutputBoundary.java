package use_case.add_course_to_profile;


public interface AddCourseToProfileOutputBoundary {
    public void prepareSuccessView();
    public void prepareFailView(String errorMessage);
}
