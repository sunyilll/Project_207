package entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private final User user = new User("userid", "nickname", "123456");
    private final User follower = new User("follower_id", "follower", "123456");
    private final Course course1 = new Course("CSC207", "Software Design");
    private final Course course2 = new Course("CSC209", "Software Tools");

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
    void follow() {
        assertTrue(follower.follow(user));
        assertTrue(follower.isFollwing(user));
        assertTrue(user.isFollowedBy(follower));
    }

    @Test
    void editCourseToTeach() {
        Course course1 = new Course("CSC207", "Software Design");
        Course course2 = new Course("CSC209", "Software Tools");

        assertTrue(user.addCourseToTeach(course1));
        assertTrue(user.addCourseToTeach(course2));
        assertEquals(2, user.getCoursesToTeach().size());
        assertTrue(user.getCoursesToTeach().containsValue(course1));
        assertTrue(user.getCoursesToTeach().containsValue(course2));

        assertTrue(user.deleteCourseToTeach(course1));
        assertEquals(1, user.getCoursesToTeach().size());
        assertTrue(user.getCoursesToTeach().containsValue(course2));
    }

    @Test
    void editCourseToLearn() {
        assertTrue(user.addCourseToLearn(course1));
        assertTrue(user.addCourseToLearn(course2));
        assertEquals(2, user.getCoursesToLearn().size());
        assertTrue(user.getCoursesToLearn().containsValue(course1));
        assertTrue(user.getCoursesToLearn().containsValue(course2));

        assertTrue(user.deleteCourseToLearn(course1));
        assertEquals(1, user.getCoursesToLearn().size());
        assertTrue(user.getCoursesToLearn().containsValue(course2));
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
        assertTrue(user.setExpectedWage(course1, 20));
        assertTrue(user.setExpectedWage(course2, 30));
        assertEquals(2, user.getExpectedWage().size());
        assertEquals(20, user.getExpectedWage(course1));
        assertEquals(30, user.getExpectedWage(course2));
    }
    @Test
    void setAndGetExpectedPrice() {
        assertTrue(user.setExpectedPrice(course1, 20));
        assertTrue(user.setExpectedPrice(course2, 30));
        assertEquals(2, user.getExpectedPrice().size());
        assertEquals(20, user.getExpectedPrice(course1));
        assertEquals(30, user.getExpectedPrice(course2));
    }
    }
