package use_case.search_course;

import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class SearchCourseOutputDataTest {
    List<Map.Entry<User, Float>> tutors;

    @BeforeEach
    void setUp() {
        Map<User, Float> tutorsMap = new HashMap<>();
        for (int i =0; i<3; i++){
            User u = new User("tutor"+i, "Name"+i, "123");
            u.addPersonalityTag("t"+i);
            u.updateTutorRating(i);
            tutorsMap.put(u, (float) i);
        }
        List<Map.Entry<User, Float>> tutors = new ArrayList<>(tutorsMap.entrySet());
        tutors.sort(Map.Entry.comparingByValue());
        this.tutors = tutors;

    }
    @Test
    void testGetSortedUserIds() {
        SearchCourseOutputData s = new SearchCourseOutputData(tutors, true, "csc207");
        assertEquals(Arrays.asList("tutor0", "tutor1", "tutor2"), s.getSortedUserIds());
        assertEquals("csc207", s.getCourseCode());
        assertTrue(s.getSearchForTutor());
        Map<String, List<String>> tags = new HashMap<>();
        tags.put("tutor0", Arrays.asList("t0"));
        tags.put("tutor1", Arrays.asList("t1"));
        tags.put("tutor2", Arrays.asList("t2"));
        assertEquals(tags, s.getUsers_tags());
    }
    @Test
    void testGetUserInfo(){
        SearchCourseOutputData s = new SearchCourseOutputData(tutors, true, "csc207");
        Map<String, Map<String, String>> res = new HashMap<>();

        Map<String, String> map0 = new HashMap<>();
        map0.put("name", "Name0");
        map0.put("rating", String.valueOf((float) 0));
        map0.put("profileURL", null);
        res.put("tutor0", map0);

        Map<String, String> map1 = new HashMap<>();
        map1.put("name", "Name1");
        map1.put("rating", String.valueOf((float) 1));
        map1.put("profileURL", null);
        res.put("tutor1", map1);

        Map<String, String> map2 = new HashMap<>();
        map2.put("name", "Name2");
        map2.put("rating", String.valueOf((float) 2));
        map2.put("profileURL", null);
        res.put("tutor2", map2);
        assertEquals(res, s.getUsersInfo());
    }
}