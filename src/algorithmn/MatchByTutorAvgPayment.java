package algorithmn;

import entity.Tutor;
import entity.User;

import java.util.*;

public class MatchByTutorAvgPayment implements MatchTutorAlgorithm{
    MatchByTutorAvgPayment(){}
    @Override
    public List<Map.Entry<User, Float>> matchTutor(User me, List<Tutor> tutors) {
        Map<User, Float> map = new HashMap<>();
        for (Tutor t: tutors){
            Map<String, Integer> wages = t.getExpectedWage();
            map.put((User) t, calculateAvgWage(wages));
        }
        List<Map.Entry<User, Float>> lst = new ArrayList<>(map.entrySet());
        lst.sort(Map.Entry.comparingByValue());
        return lst;
    }

    public static Float calculateAvgWage(Map<String, Integer> wages){
        Float avg = (float) 0;
        for (Integer wage: wages.values()){
            avg += wage;
        }
        return avg / wages.size();
    }

    public static void main(String[] args) {
        User u1 = new User("dafd","df","11");
        User t1 = new User("aaa","df","11");
        t1.setExpectedWage("csc207", 10);
        User t2 = new User("ddd","df","11");
        t2.setExpectedWage("csc207", 19);
        MatchTutorAlgorithm m = new MatchByTutorAvgPayment();
        List<Map.Entry<User, Float>> lst = m.matchTutor(u1, new ArrayList<>(Arrays.asList(t1, t2)));
        System.out.println(lst.get(0));
    }
}
