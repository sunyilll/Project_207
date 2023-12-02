package entity;

import java.util.*;

public class MatchingAlgorithm {
    public MatchingAlgorithm(){};  // avoid initialization

    public static List<Map.Entry<User, Float>> matchTutor(User user, List<Tutor> tutors){
        Map<User, Float> map = new HashMap();
        for (Tutor t: tutors){
            // convert tutor to user + rate tutor
            map.put((User) t, rateTutor(user, t));
        }
        List<Map.Entry<User, Float>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());
        return list;
    }

    public static List<Map.Entry<User, Float>> matchStudent(User user, List<Student> students){
        Map<User, Float> map = new HashMap();
        for (Student s: students){
            // convert tutor to user + rate tutor
            map.put((User) s, rateStudent(user, s));
        }
        List<Map.Entry<User, Float>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());
        return list;
    }

    private static Float rateTutor(User me, Tutor tutor){
        return tutor.getTutorRating();
    }
    private static Float rateStudent(User me, Student student){
        return student.getStudentRating();
    }
}
