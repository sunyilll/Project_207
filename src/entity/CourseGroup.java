package entity;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class CourseGroup {
    private Map<String, User> tutors = new HashMap<>();
    private Map<String, User> students = new HashMap<>();
    private String courseID;
    public CourseGroup(String courseID) {
        this.courseID = courseID;
    }
    public void addTutor(User tutor) {
        tutors.put(tutor.getUserID(), tutor);
    }
    public void addStudent(User student) {
        students.put(student.getUserID(), student);
    }
    public List<User> getMatchedTutors(User student, int numTutors) {
        // return a list of n = numTutors tutors that match the student's preferences
        // TODO: implement me
        return null;
    }

    public List<User> getMatchedStudents(User tutor, int numStudents) {
        // return a list of n = numStudents students that match the tutor's preferences
        // TODO: implement me
        return null;
    }
}
