package entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {
    private final Course testCourse = new Course("CSC207", "test1");

    @Test
    void addTutor() {
        User testTutor = new User("test1", "test1", "test1");
        testCourse.addTutor(testTutor);
        assertTrue(testCourse.getTutors().contains(testTutor));
    }

    @Test
    void addStudent() {
        User testStudent = new User("test1", "test1", "test1");
        testCourse.addStudent(testStudent);
        assertTrue(testCourse.getStudents().contains(testStudent));
    }

    @Test
    void getCourseCode() {
        assertEquals("CSC207", testCourse.getCourseCode());
    }

    @Test
    void getTutors() {
        Course testCourse1 = new Course("CSC207", "test1");
        User testTutor1 = new User("test1", "test1", "test1");
        testCourse1.addTutor(testTutor1);
        assertTrue(testCourse1.getTutors().contains(testTutor1));
    }

    @Test
    void getStudents() {
        Course testCourse1 = new Course("CSC207", "test1");
        User testStudent1 = new User("test1", "test1", "test1");
        testCourse1.addStudent(testStudent1);
        assertTrue(testCourse1.getStudents().contains(testStudent1));
    }
}