package data_access;

import entity.User;

import org.json.JSONArray;
import org.json.JSONObject;

import use_case.GetUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonUserDataAccessObject implements SignupUserDataAccessInterface, LoginUserDataAccessInterface,
        GetUserDataAccessInterface {
    String file_path;
    JSONObject userFile = new JSONObject();

    public JsonUserDataAccessObject(String file_path) {
        this.file_path = file_path;

        File file = new File(file_path);
        try {
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())));
            userFile = new JSONObject(content);
        } catch (IOException e) {
            this.save();
        }
    }

    public static void main(String[] args) {
        JsonUserDataAccessObject jsonUserDataAccessObject = new JsonUserDataAccessObject("data/users.json");
//        User testUser = new User("test", "test", "test");
//        jsonUserDataAccessObject.save(testUser);
        System.out.println(jsonUserDataAccessObject.userFile);
    }

    public void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(file_path));
            writer.write(JSONObject.valueToString(userFile));
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // TODO: Refactor code so that we are searching for the user by ID instead of nickname
    @Override
    public User get(String userID) {
        return null;
    }

    @Override
    public boolean existsByName(String userID) {
        return userFile.has(userID);
    }

    @Override
    public void save(User user) {
        JSONObject userJson = new JSONObject();
        userJson.put("nickname", user.getNickname());
        userJson.put("password", user.getPassword());
        userJson.put("pronouns", user.getPronouns());
        userJson.put("description", user.getDescription());
        JSONArray personalityTags = new JSONArray(user.getPersonalityTags());
        userJson.put("personality_tags", personalityTags);
        JSONArray coursesToLearn = new JSONArray(user.getCoursesToLearn());
        userJson.put("courses_to_learn", coursesToLearn);
        JSONArray coursesToTeach = new JSONArray(user.getCoursesToTeach());
        userJson.put("courses_to_teach", coursesToTeach);
        JSONArray studentRatings = new JSONArray(user.getStudentRatings());
        userJson.put("student_ratings", studentRatings);
        JSONArray tutorRatings = new JSONArray(user.getTutorRatings());
        userJson.put("tutor_ratings", tutorRatings);
        JSONArray modeOfLearning = new JSONArray(user.getPreferredModeOfLearning());
        userJson.put("mode_of_learning", modeOfLearning);
        JSONArray modeOfTeaching = new JSONArray(user.getPreferredModeOfTeaching());
        userJson.put("mode_of_teaching", modeOfTeaching);


        userFile.put(user.getUserID(), userJson);
        this.save();
    }
}
