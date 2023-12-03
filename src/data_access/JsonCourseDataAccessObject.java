package data_access;

import entity.Course;
import entity.Student;
import entity.Tutor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import use_case.search_course.SearchCourseDataAccessInterface;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonCourseDataAccessObject implements SearchCourseDataAccessInterface {
    String file_path;
    JSONObject courseFile = new JSONObject();
    Map<String, Course> courses = new HashMap<>();
    public JsonCourseDataAccessObject(String file_path){
        this.file_path = file_path;
        File file = new File(file_path);
        try {
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())));
            courseFile = new JSONObject(content);
        } catch (IOException e) {
            // if no such file exist, create a new file
           this.save();
        }
    }

    public void save(Course c){
        courses.put(c.getCourseCode(), c);
        JSONObject jObject = new JSONObject();
        jObject.put("courseName", c.getCourseName());
        JSONArray tutors = new JSONArray(c.getTutors());
        jObject.put("tutors", tutors);
        JSONArray students = new JSONArray(c.getStudents());
        jObject.put("students", students);
        courseFile.put(c.getCourseCode(), jObject);
        this.save();
    }

    public void save(){
        // store courseFile to course.json
        BufferedWriter writer;
        try{
            writer = new BufferedWriter(new FileWriter(file_path));
            writer.write(JSONObject.valueToString(courseFile));
            writer.close();
        } catch (IOException d){
            throw new RuntimeException(d);
        }
    }

    public boolean existCourseCode(String courseCode){
        if (courses.containsKey(courseCode)){
            return true;
        } else {
            return courseFile.has(courseCode);
        }
    }

    public static void main(String[] args) {
        JsonCourseDataAccessObject j = new JsonCourseDataAccessObject("data/course.json");
        // j.save(new Course("csc411", "Learning"));
        System.out.println(j.existCourseCode("csc411"));
    }

    @Override
    public List<Tutor> getTutorOfCourse(String courseCode) {
//        if (courses.containsKey(courseCode)){
//            return courses.get(courseCode).getTutors();
//        }
        return null;
    }

    @Override
    public List<Student> getStudentOfCourse(String courseCode) {
        return null;
    }

    @Override
    public boolean hasCourse(String couseCode) {
        return false;
    }
}
