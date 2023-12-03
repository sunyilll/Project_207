package use_case.search_course;

import algorithmn.MatchStudentAlgorithm;
import algorithmn.MatchTutorAlgorithm;
import entity.Student;
import entity.Tutor;
import entity.User;
// import org.junit.Test;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import use_case.GetUserDataAccessInterface;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class SearchCourseInteractorTest {
    User user1 = new User("yanxi", "Olivia Y", "123" );
    User student2 = new User("oxox", "Oxxx", "321");
    User tutor3 = new User("danny", "Dann", "111");
    SearchCourseDataAccessInterface dao;
    GetUserDataAccessInterface getUserDAO;
    MatchTutorAlgorithm matchTutorAlgorithm;
    MatchStudentAlgorithm matchStudentAlgorithm;

    @BeforeEach
    void setUp() {
        dao = new SearchCourseDataAccessInterface() {
            @Override
            public List<Tutor> getTutorOfCourse(String courseCode) {
                if (courseCode == "csc207"){
                    List<Tutor> a = new ArrayList<>();
                    a.add(tutor3);
                    return a;
                }
                return null;
            }

            @Override
            public List<Student> getStudentOfCourse(String courseCode) {
                if (courseCode == "csc207"){
                    List<Student> a = new ArrayList<>();
                    a.add(student2);
                    return a;
                }
                return null;
            }

            @Override
            public boolean hasCourse(String couseCode) {
                return couseCode == "csc207";
            }
        };

        getUserDAO = new GetUserDataAccessInterface() {
            @Override
            public User get(String userid) {
                if (userid == "yanxi6") {return user1;}
                return null;
            }
        };
        matchTutorAlgorithm = new MatchTutorAlgorithm() {
            @Override
            public List<Map.Entry<User, Float>> matchTutor(User me, List<Tutor> tutors) {
                Map<User, Float> map = new HashMap<>();
                map.put(tutor3, (float) 1.2);
                List<Map.Entry<User, Float>> a = new ArrayList<>(map.entrySet());
                return a;
            }
        };
        matchStudentAlgorithm = new MatchStudentAlgorithm() {
            @Override
            public List<Map.Entry<User, Float>> matchStudent(User me, List<Student> students) {
                Map<User, Float> map = new HashMap<>();
                map.put(student2, (float) 1.2);
                List<Map.Entry<User, Float>> a = new ArrayList<>(map.entrySet());
                return a;
            }
        };

    }
    @Test
    public void successTest1(){
        SearchCourseInputData input = new SearchCourseInputData("csc207", true, "yanxi6");
        SearchCourseOutputBoundary presenter = new SearchCourseOutputBoundary() {
            @Override
            public void prepareSuccessView(SearchCourseOutputData users) {
                assertTrue(users.getSearchForTutor());
                assertEquals("csc207", users.getCourseCode());
                assertEquals(Arrays.asList("danny"), users.getSortedUserIds());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Fail unexpected");
            }
        };
        SearchCourseInteractor interactor = new SearchCourseInteractor(dao, presenter, getUserDAO, matchTutorAlgorithm, matchStudentAlgorithm);
        interactor.execute(input);
    }
    @Test
    public void successTest2(){
        SearchCourseInputData input = new SearchCourseInputData("csc207", false, "yanxi6");
        SearchCourseOutputBoundary presenter = new SearchCourseOutputBoundary() {
            @Override
            public void prepareSuccessView(SearchCourseOutputData users) {
                assertFalse(users.getSearchForTutor());
                assertEquals("csc207", users.getCourseCode());
                assertEquals(Arrays.asList("oxox"), users.getSortedUserIds());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Fail unexpected");
            }
        };
        SearchCourseInteractor interactor = new SearchCourseInteractor(dao, presenter, getUserDAO, matchTutorAlgorithm, matchStudentAlgorithm);
        interactor.execute(input);
    }

    @Test
    public void failTest(){
        SearchCourseInputData input = new SearchCourseInputData("csc311", false, "yanxi6");
        SearchCourseOutputBoundary presenter = new SearchCourseOutputBoundary() {
            @Override
            public void prepareSuccessView(SearchCourseOutputData users) {}
            @Override
            public void prepareFailView(String error) {
                assertEquals("No Such Course", error);
            }
        };
        SearchCourseInteractor interactor = new SearchCourseInteractor(dao, presenter, getUserDAO, matchTutorAlgorithm, matchStudentAlgorithm);
        interactor.execute(input);
    }
}