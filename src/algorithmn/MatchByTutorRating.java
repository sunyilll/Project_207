package algorithmn;

import entity.Tutor;
import entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MatchByTutorRating implements MatchTutorAlgorithm{
    public MatchByTutorRating(){}
    @Override
    public List<Map.Entry<User, Float>> matchTutor(User me, List<Tutor> tutors) {
        Map<User, Float> map = new HashMap();
        for (Tutor t: tutors){
            // convert tutor to user + rate tutor
            map.put((User) t, t.getTutorRating());
        }
        List<Map.Entry<User, Float>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());
        return list;
    }
}
