package use_case.search_course;

public interface SearchCourseOutputBoundary {
    void prepareSuccessView(SearchCourseOutputData users);
    void prepareFailView(String error);
}
