package data_access;

import entity.*;
import use_case.search_course.SearchCourseDataAccessInterface;
import java.io.*;
import java.util.*;

public class FileCourseDataAccessObject implements SearchCourseDataAccessInterface {
    private final File csvFile;
    private CourseFactory courseFactory;
    private final Map<String, Integer> headers = new LinkedHashMap<>();
    private final Map<String, Course> courses = new HashMap<>();
    public FileCourseDataAccessObject(String csvPath, CourseFactory courseFactory) throws IOException {
        this.courseFactory = courseFactory;
        csvFile = new File(csvPath);
        headers.put("courseCode", 0);
        headers.put("tutorsId", 1);
        headers.put("studentsId", 2);
        if (csvFile.length() == 0){
            save();
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();

                // For later: clean this up by creating a new Exception subclass and handling it in the UI.
                System.out.println(header);
                assert header.equals("courseCode,tutorsId,studentsId");

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String courseCode = String.valueOf(col[headers.get("courseCode")]);
                    String[] tutorids = convertToListIds(col[headers.get("tutorsId")]);
                    String[] studentids = convertToListIds(col[headers.get("studentsId")]);
                    Course course = courseFactory.create(courseCode, tutorids, studentids);
                    courses.put(course.getCourseCode(), course);
                }
            }
        }
    }
    public void saveTutor(Tutor tutor, String courseCode){
        courses.get(courseCode).addTutor(tutor);
        this.save();
    }
    public void saveStudent(Student student, String courseCode){
        courses.get(courseCode).addStudent(student);
        this.save();
    }
    public void deleteTutor(Tutor tutor, String courseCode){
        //todo: implement me
    }
    public void deleteStudent(Student student, String courseCode){
        //todo: implement me
    }
    private void save(){
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();
            for (Course course : courses.values()) {
                String line = String.format("%s,%s,%s",
                        course.getCourseCode(), convertToStringIdsTutor(course.getTutors()),convertToStringIdsStudent(course.getStudents()));
                writer.write(line);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String[] convertToListIds(String ids){
        return ids.split("/");
    }
    private static String convertToStringIdsTutor(List<Tutor> tutors){
        List<String> ids = new ArrayList<>();
        for (Tutor tutor : tutors){ids.add(tutor.getUserID());}
        return String.join(",", ids);  //todo: test join correctly
    }
    private static String convertToStringIdsStudent(List<Student> tutors){
        List<String> ids = new ArrayList<>();
        for (Student student : tutors){ids.add(student.getUserID());}
        return String.join(",", ids);
    }
    @Override
    public List<Tutor> getTutorOfCourse(String courseCode) {
        return courses.get(courseCode).getTutors();
    }

    @Override
    public List<Student> getStudentOfCourse(String courseCode) {
        return courses.get(courseCode).getStudents();
    }

    @Override
    public boolean hasCourse(String couseCode) {
        return (courses.containsKey(couseCode));
    }
}
