package algorithmn;

import entity.Student;
import entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        list.sort(Map.Entry.comparingByValue());
        return list;
    }
}
