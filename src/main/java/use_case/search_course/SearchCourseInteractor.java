package main.java.use_case.search_course;

import main.java.entity.MatchingAlgorithm;
import main.java.entity.Student;
import main.java.entity.Tutor;
import main.java.interface_adapter.search_course.SearchCoursePresenter;

import java.util.List;

public class SearchCourseInteractor implements SearchCourseInputBoundary{
    final SearchCourseDataAccessInterface courserDataAccessObject;
    final SearchCourseOutputBoundary searchCoursePresenter;
    public SearchCourseInteractor(SearchCourseDataAccessInterface courserDataAccessObject,
                                  SearchCourseOutputBoundary searchCourseOutputBoundary){
        this.courserDataAccessObject = courserDataAccessObject;
        this.searchCoursePresenter = searchCourseOutputBoundary;
    }


    @Override
    public void execute(SearchCourseInputData searchCourseInputData) {
        String courseCode = searchCourseInputData.getCourseCode();
        boolean searchForTutor = searchCourseInputData.searchTutor();
        if (searchForTutor){
            List<Tutor> candidates = courserDataAccessObject.getTutorOfCourse(courseCode);
        } else {
            List<Student> candidates = courserDataAccessObject.getStudentOfCourse(courseCode);
        }
        MatchingAlgorithm a = new MatchingAlgorithm();
        // a.match(... , ...);
    }
}
