package use_case.search_course;

import entity.MatchingAlgorithm;
import entity.Student;
import entity.Tutor;
import entity.User;
import use_case.GetUserDataAccessInterface;

import java.util.ArrayList;
import java.util.List;

public class SearchCourseInteractor implements SearchCourseInputBoundary{
    final SearchCourseDataAccessInterface courserDataAccessObject;
    final SearchCourseOutputBoundary searchCoursePresenter;
    final GetUserDataAccessInterface getUserDataAccessInterface;
    public SearchCourseInteractor(SearchCourseDataAccessInterface courserDataAccessObject,
                                  SearchCourseOutputBoundary searchCourseOutputBoundary,
                                  GetUserDataAccessInterface getUserDataAccessInterface){
        this.courserDataAccessObject = courserDataAccessObject;
        this.searchCoursePresenter = searchCourseOutputBoundary;
        this.getUserDataAccessInterface = getUserDataAccessInterface;
    }


    @Override
    public void execute(SearchCourseInputData searchCourseInputData) {
        String courseCode = searchCourseInputData.getCourseCode();
        boolean searchForTutor = searchCourseInputData.searchTutor();
        User me = getUserDataAccessInterface.get(searchCourseInputData.getUserID());
        MatchingAlgorithm a = new MatchingAlgorithm();
        if (courserDataAccessObject.hasCourse(courseCode) == false){
            System.out.println("NO SUCH COURSE");
            searchCoursePresenter.prepareFailView("No Such Course");
            return;
        }
        if (searchForTutor == true){
            List<Tutor> candidates = courserDataAccessObject.getTutorOfCourse(courseCode);
            List<Tutor> sortedCandidates = a.matchTutor(me, candidates);
            List<User> sortedUsers = new ArrayList<>();
            for (Tutor t: sortedCandidates){
                User user = (User) t;
                sortedUsers.add(user);
            }
            SearchCourseOutputData tutors = new SearchCourseOutputData(sortedUsers, searchForTutor, courseCode);
            searchCoursePresenter.prepareSuccessView(tutors);
        } else if (searchForTutor == false) {
            List<Student> candidates = courserDataAccessObject.getStudentOfCourse(courseCode);
            List<Student> sortedCandidates = a.matchStudent(me, candidates);
            List<User> sortedUsers = new ArrayList<>();
            for (Student t: sortedCandidates){
                User user = (User) t;
                sortedUsers.add(user);
            }
            SearchCourseOutputData students = new SearchCourseOutputData(sortedUsers, searchForTutor, courseCode);
            searchCoursePresenter.prepareSuccessView(students);
        } else {
            searchCoursePresenter.prepareFailView("IDK you wanna Search For Tutor or Student");
        }


    }
}
