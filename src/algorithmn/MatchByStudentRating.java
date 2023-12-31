package algorithmn;

import entity.Student;
import entity.User;

import java.util.*;

public class MatchByStudentRating implements MatchStudentAlgorithm{
    public MatchByStudentRating(){}
    @Override
    public List<Map.Entry<User, Float>> matchStudent(User me, List<Student> students) {
        Map<User, Float> map = new HashMap();
        for (Student s: students){
            // convert tutor to user + rate tutor
            map.put((User) s, s.getStudentRating());
        }
        List<Map.Entry<User, Float>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        return list;
    }
}
