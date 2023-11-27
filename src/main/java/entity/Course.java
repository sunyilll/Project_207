package main.java.entity;
import java.util.ArrayList;
import java.util.List;

public class Course {

    public String courseCode;
    public String courseName;
    // ChatChannel courseChannel;
    List<Tutor> tutors = new ArrayList<>();
    List<Student> students = new ArrayList<>();

    public Course(String courseCode, String courseName){
        this.courseCode = courseCode;
        this.courseName = courseName;
    }
    public void addTutor(Tutor tutor){
        this.tutors.add(tutor);
    }
    public void addStudent(Student student){
        this.students.add(student);
    }
    public String getCourseCode(){return courseCode;}
    public List<Tutor> getTutors(){return tutors;}
    public List<Student> getStudents(){return students;}
}
