package use_case.go_to_personal_profile;

import entity.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GoToPersonalProfileOutputDataTest {
    private final User testUser1 = new User("test1", "test1", "test1");
    private final GoToPersonalProfileOutputData testOutputData = new GoToPersonalProfileOutputData(testUser1);

    @Test
    void getUser() {
        assertEquals("test1", testOutputData.getUserid());
        assertEquals("test1", testOutputData.getNickname());
        assertNull(testOutputData.getPronouns());
        assertEquals(0, testOutputData.getPersonalityTags().size());
        assertNull(testOutputData.getDescription());
        assertEquals(0, testOutputData.getTutorRating());
        assertEquals(0, testOutputData.getCoursesToTeach().size());
        assertNull(testOutputData.getTutorAvailability());
        assertEquals(0, testOutputData.getExpectedWage().size());
        assertEquals(0, testOutputData.getPreferredModeOfTeaching().size());
        assertEquals(0, testOutputData.getStudentRating());
        assertEquals(0, testOutputData.getCoursesToLearn().size());
        assertEquals(0, testOutputData.getExpectedPrice().size());
        assertEquals(0, testOutputData.getPreferredModeOfLearning().size());


    }
}