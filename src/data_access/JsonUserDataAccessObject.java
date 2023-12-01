package data_access;

import entity.User;

import org.json.JSONObject;
import org.json.JSONTokener;
import use_case.GetUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

import java.io.*;

public class JsonUserDataAccessObject implements SignupUserDataAccessInterface, LoginUserDataAccessInterface,
        GetUserDataAccessInterface {
    String file_path;
    JSONObject userFile = new JSONObject();

    public JsonUserDataAccessObject(String file_path) {
        this.file_path = file_path;

        File file = new File(file_path);
        if (file.length() == 0) {
            this.save();
        } else {

            InputStream inputStream = JsonUserDataAccessObject.class.getResourceAsStream(file_path);

            JSONTokener tokener = new JSONTokener(inputStream);
            userFile = new JSONObject(tokener);
        }
    }

    public static void main(String[] args) {
        JsonUserDataAccessObject jsonUserDataAccessObject = new JsonUserDataAccessObject("data/users.json");
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

    @Override
    public User get(String username) {
        return null;
    }

    @Override
    public boolean existsByName(String identifier) {
        return false;
    }

    @Override
    public void save(User user) {

    }
}
