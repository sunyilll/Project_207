package use_case.search_course;

import algorithmn.MatchStudentAlgorithm;
import algorithmn.MatchTutorAlgorithm;
import entity.Student;
import entity.Tutor;
import entity.User;
import use_case.GetUserDataAccessInterface;

import java.util.List;
import java.util.Map;

public class SearchCourseInteractor implements SearchCourseInputBoundary{
    final SearchCourseDataAccessInterface courserDataAccessObject;
    final SearchCourseOutputBoundary searchCoursePresenter;
    final GetUserDataAccessInterface getUserDataAccessInterface;
    final MatchTutorAlgorithm matchTutorAlgorithm;
    final MatchStudentAlgorithm matchStudentAlgorithm;
    public SearchCourseInteractor(SearchCourseDataAccessInterface courserDataAccessObject,
                                  SearchCourseOutputBoundary searchCourseOutputBoundary,
                                  GetUserDataAccessInterface getUserDataAccessInterface,
                                  MatchTutorAlgorithm matchTutorAlgorithm,
                                  MatchStudentAlgorithm matchStudentAlgorithm){
        this.courserDataAccessObject = courserDataAccessObject;
        this.searchCoursePresenter = searchCourseOutputBoundary;
        this.getUserDataAccessInterface = getUserDataAccessInterface;
        this.matchStudentAlgorithm = matchStudentAlgorithm;
        this.matchTutorAlgorithm = matchTutorAlgorithm;
    }


    @Override
    public void execute(SearchCourseInputData searchCourseInputData) {
        String courseCode = searchCourseInputData.getCourseCode();
        boolean searchForTutor = searchCourseInputData.searchTutor();
        User me = getUserDataAccessInterface.get(searchCourseInputData.getUserID());
        if (courserDataAccessObject.hasCourse(courseCode) == false){
            searchCoursePresenter.prepareFailView("No Such Course");
            return;
        }
        if (searchForTutor == true){
            List<Tutor> candidates = courserDataAccessObject.getTutorOfCourse(courseCode);
            List<Map.Entry<User, Float>> sortedCandidates = matchTutorAlgorithm.matchTutor(me, candidates);
            SearchCourseOutputData tutors = new SearchCourseOutputData(sortedCandidates, searchForTutor, courseCode);
            searchCoursePresenter.prepareSuccessView(tutors);
        } else if (searchForTutor == false) {
            List<Student> candidates = courserDataAccessObject.getStudentOfCourse(courseCode);
            List<Map.Entry<User, Float>> sortedCandidates = matchStudentAlgorithm.matchStudent(me, candidates);
            SearchCourseOutputData students = new SearchCourseOutputData(sortedCandidates, searchForTutor, courseCode);
            searchCoursePresenter.prepareSuccessView(students);
        } else {
            searchCoursePresenter.prepareFailView("IDK you wanna Search For Tutor or Student");
        }
    }
}
