package main.java.data_access;

import main.java.entity.User;
import main.java.entity.UserFactory;
import main.java.use_case.login.LoginUserDataAccessInterface;
import main.java.use_case.signup.SignupUserDataAccessInterface;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class FileUserDataAccessObject implements SignupUserDataAccessInterface, LoginUserDataAccessInterface {

    private final File csvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<String, User> accounts = new HashMap<>();

    private UserFactory userFactory;
    private List<String> usersDeleted = new ArrayList<>();

    public FileUserDataAccessObject(String csvPath, UserFactory userFactory) throws IOException {
        this.userFactory = userFactory;

        csvFile = new File(csvPath);
        headers.put("userid", 0);
        headers.put("nickname", 1);
        headers.put("password", 2);

        if (csvFile.length() == 0) {
            save();
        } else {

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();

                // For later: clean this up by creating a new Exception subclass and handling it in the UI.
                assert header.equals("userid,nickname,password");

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    if (col.length >= 3) {
                        String userid = String.valueOf(col[headers.get("userid")]);
                        String nickname = String.valueOf(col[headers.get("nickname")]);
                        String password = String.valueOf(col[headers.get("password")]);
                        User user = userFactory.create(userid, nickname, password);
                        accounts.put(userid, user);
                } else {
                    // Handle the error for rows that do not have enough columns
                    System.err.println("Invalid row in CSV: " + row);
                }
                }
            }
        }
    }

    @Override
    public void save(User user) {
        accounts.put(user.getNickname(), user);
        this.save();
    }

    @Override
    public User get(String username) {
        return accounts.get(username);
    }

    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (User user : accounts.values()) {
                String line = String.format("%s,%s",
                        user.getUserID(), user.getNickname());
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public boolean existsByName(String identifier) {
        return accounts.containsKey(identifier);
    }

}
