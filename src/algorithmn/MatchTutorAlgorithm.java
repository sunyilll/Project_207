package algorithmn;

import entity.Tutor;
import entity.User;

import java.util.List;
import java.util.Map;

public interface MatchTutorAlgorithm {
    List<Map.Entry<User, Float>> matchTutor(User me, List<Tutor> tutors);
}
