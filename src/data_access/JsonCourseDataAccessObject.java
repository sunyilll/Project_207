package data_access;

import entity.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import use_case.add_course_to_profile.AddCourseToProfileDataAccessInterface;
import use_case.search_course.SearchCourseDataAccessInterface;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonCourseDataAccessObject implements SearchCourseDataAccessInterface, AddCourseToProfileDataAccessInterface {
    String file_path;
    final CourseFactory courseFactory;
    JSONObject courseFile = new JSONObject();
    Map<String, Course> courses = new HashMap<>();
    public JsonCourseDataAccessObject(String file_path,
                                      CourseFactory courseFactory){
        this.file_path = file_path;
        this.courseFactory = courseFactory;
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
        jObject.put("courseCode", c.getCourseCode());
        jObject.put("courseName", c.getCourseName());
        JSONArray tutors = new JSONArray(c.getTutorsIds());
        jObject.put("tutors", tutors);
        JSONArray students = new JSONArray(c.getStudentsIds());
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

    private void loadToCourses(JSONObject obj){
        courses.put(obj.getString("courseCode"), courseFactory.create(obj.getString("courseCode"),
                convertToList(obj.getJSONArray("tutors")),
                convertToList(obj.getJSONArray("students"))));
    }

    private static List<String> convertToList(JSONArray jsonArray){
        List<String> lst = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            lst.add(jsonArray.getString(i));
        }
        return lst;
    }

    public static void main(String[] args) {
        JsonUserDataAccessObject juser = new JsonUserDataAccessObject("./users.json");
        JsonCourseDataAccessObject j = new JsonCourseDataAccessObject("./courses.json", new CourseFactory(juser));
        // j.save(new Course("csc207", "Learning"));
        User kevin2 = new User("bob", "Bob", "123");
        User jack2 = new User("nicle", "Nicle", "123");
        User ross2 = new User("dan", "Dan", "1243");
        User lucy2 = new User("kitty", "Kitty", "124");


        juser.save(kevin2);
        juser.save(jack2);
        juser.save(lucy2);
        juser.save(ross2);

        j.addTutor(ross2, "csc207");
        j.addTutor(kevin2, "csc207");
        j.addStudent(jack2, "csc207");
        j.addTutor(lucy2, "csc207");

    }

    @Override
    public List<Tutor> getTutorOfCourse(String courseCode) {
        if (courses.containsKey(courseCode)){
            return courses.get(courseCode).getTutors();
        } else if (!hasCourse(courseCode)){return null;}
        else{
            JSONObject obj = courseFile.getJSONObject(courseCode);
            this.loadToCourses(obj);  // add course to courses
            return courses.get(courseCode).getTutors();
        }
    }

    @Override
    public List<Student> getStudentOfCourse(String courseCode) {
        if (courses.containsKey(courseCode)){
            return courses.get(courseCode).getStudents();
        } else if (!hasCourse(courseCode)){return null;}
        else{
            JSONObject obj = courseFile.getJSONObject(courseCode);
            this.loadToCourses(obj);  // add course to courses
            return courses.get(courseCode).getStudents();
        }
    }
    @Override
    public boolean hasCourse(String courseCode){
        if (courses.containsKey(courseCode)){
            return true;
        } else {
            return courseFile.has(courseCode);
        }
    }

    public void addStudent(User user, String courseCode){
        if (hasCourse(courseCode)){
            if (courses.containsKey(courseCode)){
                Course c = courses.get(courseCode);
                if (!c.containStudent(user.getUserID())){
                    c.addStudent(user);
                    this.save(c);
                }
            } else {  // in courses.json but not courses
                JSONObject j = courseFile.getJSONObject(courseCode);
                loadToCourses(j);
                Course c = courses.get(courseCode);
                if (!c.containStudent(user.getUserID())){
                    c.addStudent(user);
                    this.save(c);
                }
            }
        } else { //initalize course
            List<String> s = new ArrayList<>();
            s.add(user.getUserID());
            Course newCourse = courseFactory.create(courseCode, new ArrayList<>(), s);
            courses.put(courseCode, newCourse);
        }
    }

    public void addTutor(User user, String courseCode) {
        if (hasCourse(courseCode)) {
            if (courses.containsKey(courseCode)) {
                Course c = courses.get(courseCode);
                if (!c.containTutor(user.getUserID())){
                    c.addTutor(user);
                    this.save(c);
                }
            } else {  // in courses.json but not courses
                JSONObject j = courseFile.getJSONObject(courseCode);
                loadToCourses(j);
                Course c = courses.get(courseCode);
                if (!c.containTutor(user.getUserID())){
                    c.addTutor(user);
                    this.save(c);
                }
            }
        } else { //initalize course
            List<String> s = new ArrayList<>();
            s.add(user.getUserID());
            Course newCourse = courseFactory.create(courseCode, s, new ArrayList<>());
            courses.put(courseCode, newCourse);
        }
    }

    public void deleteTutor(User user, String courseCode){
        System.out.println(user.getNickname()+ "is deleted");
        //todo: implement
    }
    public void deleteStudent(User user, String courseCode){
        System.out.println(user.getNickname()+ "is deleted");
        //todo: implement
    }

}
