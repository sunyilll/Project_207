package algorithmn;

import entity.Student;
import entity.User;

import java.util.*;

public class MatchByStudentAvgPayment implements MatchStudentAlgorithm{
    MatchByStudentAvgPayment(){}
    @Override
    public List<Map.Entry<User, Float>> matchStudent(User me, List<Student> students) {
        Map<User, Float> map = new HashMap<>();
        for (Student t: students){
            Map<String, Integer> wages = t.getExpectedPrice();
            map.put((User) t, calculateAvgPayment(wages));
        }
        List<Map.Entry<User, Float>> lst = new ArrayList<>(map.entrySet());
        lst.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        return lst;
    }

    private static Float calculateAvgPayment(Map<String, Integer> wages) {
        Float avg = (float) 0;
        for (Integer wage: wages.values()){
            avg += wage;
        }
        return avg / wages.size();
    }
}
