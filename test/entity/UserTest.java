package entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private final User user = new User("userid", "nickname", "123456");
    private final User follower = new User("follower_id", "follower", "123456");
    @Test
    void getUserID() {
        assertEquals("userid", user.getUserID());
    }

    @Test
    void getNickname() {
        assertEquals("nickname", user.getNickname());
    }

    @Test
    void getPassword() {
        assertEquals("123456", user.getPassword());
    }

    @Test
    void setNickname() {
        assertTrue(user.setNickname("new_nickname"));
        assertEquals("new_nickname", user.getNickname());
    }

    @Test
    void checkPassword() {
        assertTrue(user.checkPassword("123456"));
        assertFalse(user.checkPassword("1234567"));
    }

    @Test
    void setAndGetPronouns() {
        assertTrue(user.setPronouns("they/them"));
        assertEquals("they/them", user.getPronouns());
    }

    @Test
    void setAndGetDescription() {
        assertTrue(user.setDescription("Hello World!"));
        assertEquals("Hello World!", user.getDescription());
    }

    @Test
    void addAndGetPersonalityTag() {
        assertTrue(user.addPersonalityTag("Friendly"));
        assertTrue(user.addPersonalityTag("Funny"));
        assertTrue(user.addPersonalityTag("ENFP"));
        assertEquals(3, user.getPersonalityTags().size());
        assertTrue(user.getPersonalityTags().contains("Friendly"));
        assertTrue(user.getPersonalityTags().contains("Funny"));
        assertTrue(user.getPersonalityTags().contains("ENFP"));
    }
    
    @Test
    void editCourseToTeach() {
        assertTrue(user.addCourseToTeach("CSC207"));
        assertTrue(user.addCourseToTeach("MAT237"));
        assertEquals(2, user.getCoursesToTeach().size());
        assertTrue(user.getCoursesToTeach().contains("CSC207"));
        assertTrue(user.getCoursesToTeach().contains("MAT237"));

        assertTrue(user.deleteCourseToTeach("CSC207"));
        assertEquals(1, user.getCoursesToTeach().size());
        assertTrue(user.getCoursesToTeach().contains("MAT237"));
    }

    @Test
    void editCourseToLearn() {
        assertTrue(user.addCourseToLearn("CSC207"));
        assertTrue(user.addCourseToLearn("MAT237"));
        assertEquals(2, user.getCoursesToLearn().size());
        assertTrue(user.getCoursesToLearn().contains("CSC207"));
        assertTrue(user.getCoursesToLearn().contains("MAT237"));

        assertTrue(user.deleteCourseToLearn("CSC207"));
        assertEquals(1, user.getCoursesToLearn().size());
        assertTrue(user.getCoursesToLearn().contains("MAT237"));
    }

    @Test
    void editTutorAvailability() {
        assertTrue(user.setTutorAvailability("Monday 10:00 - 12:00"));
        assertEquals("Monday 10:00 - 12:00", user.getTutorAvailability());
    }

    @Test
    void updateTutorRating() {
        assertEquals(0, user.getTutorRating());
        assertTrue(user.updateTutorRating(5));
        assertEquals(5, user.getTutorRating());
        assertTrue(user.updateTutorRating(4));
        assertEquals(4.5, user.getTutorRating());
    }
    @Test
    void updateTutorRatingIllegalInput() {
        assertFalse(user.updateTutorRating(-1));
        assertFalse(user.updateTutorRating(6));
    }

    @Test
    void updateStudentRating() {
        assertEquals(0, user.getStudentRating());
        assertTrue(user.updateStudentRating(5));
        assertEquals(5, user.getStudentRating());
        assertTrue(user.updateStudentRating(4));
        assertEquals(4.5, user.getStudentRating());
    }

    @Test
    void updateStudentRatingIllegalInput() {
        assertFalse(user.updateStudentRating(-1));
        assertFalse(user.updateStudentRating(6));
    }

    @Test
    void editPreferredModeOfTeaching() {
        assertTrue(user.setPreferredModeOfTeaching("Online"));
        assertTrue(user.setPreferredModeOfTeaching("In Person"));
        assertEquals(2, user.getPreferredModeOfTeaching().size());
        assertTrue(user.getPreferredModeOfTeaching().contains("Online"));
        assertTrue(user.getPreferredModeOfTeaching().contains("In Person"));
    }

    @Test
    void editPreferredModeOfLearning() {
        assertTrue(user.setPreferredModeOfLearning("Online"));
        assertTrue(user.setPreferredModeOfLearning("In Person"));
        assertEquals(2, user.getPreferredModeOfLearning().size());
        assertTrue(user.getPreferredModeOfLearning().contains("Online"));
        assertTrue(user.getPreferredModeOfLearning().contains("In Person"));
    }
    @Test
    void setAndGetExpectedWage() {
        assertTrue(user.setExpectedWage("CSC207", 20));
        assertTrue(user.setExpectedWage("MAT237", 30));
        assertEquals(2, user.getExpectedWage().size());
        assertEquals(20, user.getExpectedWage("CSC207"));
        assertEquals(30, user.getExpectedWage("MAT237"));
    }
    @Test
    void setAndGetExpectedPrice() {
        assertTrue(user.setExpectedPrice("CSC207", 20));
        assertTrue(user.setExpectedPrice("MAT237", 30));
        assertEquals(2, user.getExpectedPrice().size());
        assertEquals(20, user.getExpectedPrice("CSC207"));
        assertEquals(30, user.getExpectedPrice("MAT237"));
    }
    }
