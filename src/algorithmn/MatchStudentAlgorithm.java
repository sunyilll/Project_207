package algorithmn;

import entity.Student;
import entity.User;

import java.util.List;
import java.util.Map;

public interface MatchStudentAlgorithm {
    List<Map.Entry<User, Float>> matchStudent(User me, List<Student> students);
}
