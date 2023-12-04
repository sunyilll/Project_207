package entity;
import java.util.ArrayList;
import java.util.Arrays;
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
    public List<String> getTutorsIds(){
        List<String> lst = new ArrayList<>();
        for (Tutor t: tutors){lst.add(t.getUserID());}
        return lst;
    }
    public List<String> getStudentsIds(){
        List<String> lst = new ArrayList<>();
        for (Student s: students){lst.add(s.getUserID());}
        return lst;
    }
    public boolean containTutor(String id){
        List<String> lst = this.getTutorsIds();
        return lst.contains(id);
    }
    public boolean containStudent(String id){
        List<String> lst = this.getStudentsIds();
        return lst.contains(id);
    }
    public String getCourseName() {return courseName;}
}
