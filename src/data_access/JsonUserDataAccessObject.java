package data_access;

import api.SendBirdAPI;
import entity.ChatChannel;
import entity.User;

import entity.UserBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import use_case.GetUserDataAccessInterface;
import use_case.edit_profile.EditProfileDataAccessInterface;
import use_case.go_to_channel.GoToChannelDataAccessInterface;
import use_case.go_to_chat_list.GoToChatListDataAccessInterface;
import use_case.go_to_personal_profile.GoToPersonalProfileDataAccessInterface;
import use_case.go_to_public_profile.GoToPublicProfileDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.save_profile.SaveProfileDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

import java.io.*;
import java.nio.channels.Channel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class JsonUserDataAccessObject implements SignupUserDataAccessInterface,
        LoginUserDataAccessInterface,
        GetUserDataAccessInterface, GoToPersonalProfileDataAccessInterface,
        GoToPublicProfileDataAccessInterface, EditProfileDataAccessInterface,
        SaveProfileDataAccessInterface,
        GoToChatListDataAccessInterface, GoToChannelDataAccessInterface {
    String file_path;
    String current_userid;
    JSONObject userFile = new JSONObject();
    Map<String, User> users = new HashMap<>();

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
//         User testUser = new User("test", "test", "test");
//         jsonUserDataAccessObject.save(testUser);
        // System.out.println(jsonUserDataAccessObject.toString());
        System.out.println(jsonUserDataAccessObject.existsById("test"));
        System.out.println(jsonUserDataAccessObject.get("test").getPersonalityTags());
    }

    // TODO: Refactor code so that we are searching for the user by ID instead of nickname
    @Override
    public User get(String userid) {
        // if user is in users, return user
        if (users.containsKey(userid)) {
            return users.get(userid);
        } else if (!existsById(userid)) {
            return null;
        } else {
            UserBuilder userBuilder = new UserBuilder();
            JSONObject userJSON = userFile.getJSONObject(userid);
            String nickname = userJSON.getString("nickname");
            String password = userJSON.getString("password");
            userBuilder.create(userid, nickname, password);
            try {
                String pronouns = userJSON.getString("pronouns");
                userBuilder.setPronouns(pronouns);
                }
            catch (Exception e) {
                // do nothing
            }
            try {
                String description = userJSON.getString("description");
                userBuilder.setDescription(description);
            } catch (Exception e) {
                // do nothing
            }
            try {
                String tutorAvailability = userJSON.getString("tutor_availability");
                userBuilder.setTutorAvailability(tutorAvailability);
            } catch (Exception e) {
                // do nothing
            }
            try {
                JSONArray personalityTags = userJSON.getJSONArray("personality_tags");
                for (int i = 0; i < personalityTags.length(); i++) {
                    userBuilder.addPersonalityTag(personalityTags.getString(i));
                }
            } catch (Exception e) {
                // do nothing
            }
            try {
                JSONArray coursesToLearn = userJSON.getJSONArray("courses_to_learn");
                for (int i = 0; i < coursesToLearn.length(); i++) {
                    userBuilder.addCoursesToLearn(coursesToLearn.getString(i));
                }
            } catch (Exception e) {
                // do nothing
            }
            try {
                JSONArray coursesToTeach = userJSON.getJSONArray("courses_to_teach");
                for (int i = 0; i < coursesToTeach.length(); i++) {
                    userBuilder.addCoursesToTeach(coursesToTeach.getString(i));
                }
            } catch (Exception e) {
                // do nothing
            }
            try {
                JSONArray modeOfLearning = userJSON.getJSONArray("mode_of_learning");
                for (int i = 0; i < modeOfLearning.length(); i++) {
                    userBuilder.addPreferredModeOfLearning(modeOfLearning.getString(i));
                }
            } catch (Exception e) {
                // do nothing
            }
            try {
                JSONArray modeOfTeaching = userJSON.getJSONArray("mode_of_teaching");
                for (int i = 0; i < modeOfTeaching.length(); i++) {
                    userBuilder.addPreferredModeOfTeaching(modeOfTeaching.getString(i));
                }
            } catch (Exception e) {
                // do nothing
            }
            try {
                JSONArray studentRatings = userJSON.getJSONArray("student_ratings");
                for (int i = 0; i < studentRatings.length(); i++) {
                    userBuilder.addStudentRating(studentRatings.getInt(i));
                }
            } catch (Exception e) {
                // do nothing
            }
            try {
                JSONArray tutorRatings = userJSON.getJSONArray("tutor_ratings");
                for (int i = 0; i < tutorRatings.length(); i++) {
                    userBuilder.addTutorRating(tutorRatings.getInt(i));
                }
            } catch (Exception e) {
                // do nothing
            }
            try {
                JSONObject expectedWage = userJSON.getJSONObject("expected_wage");
                for (String course : expectedWage.keySet()) {
                    userBuilder.setExpectedWage(course, expectedWage.getInt(course));
                }
            } catch (Exception e) {
                // do nothing
            }
            try {
                JSONObject expectedPrice = userJSON.getJSONObject("expected_price");
                for (String course : expectedPrice.keySet()) {
                    userBuilder.setExpectedPrice(course, expectedPrice.getInt(course));
                }
            } catch (Exception e) {
                // do nothing
            }
            users.put(userid, userBuilder.getUser());
            return userBuilder.getUser();
        }
    }

    @Override
    public void saveCurrentUser(String userid) {
        this.current_userid = userid;
    }

    @Override
    public boolean existsById(String userid) {
        return userFile.has(userid);
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
    public void createUserinAPI(String userID, String nickname) {
        SendBirdAPI sendBirdAPIObject = new SendBirdAPI("https://api-1F4C3D4F-01DB-4A99-8704-BE4CB1FE3AE5.sendbird.com/v3",
                "1F4C3D4F-01DB-4A99-8704-BE4CB1FE3AE5",
                "0ecfef313ab2989479b70e30e3ee37a1d105b770");
        try {
            sendBirdAPIObject.setUser(userID, nickname);
        } catch (RuntimeException e) {
            System.out.println(e);
        }
    }

    @Override
    public void save(User user) {
        users.put(user.getUserID(), user);

        JSONObject userJson = new JSONObject();
        userJson.put("nickname", user.getNickname());
        userJson.put("password", user.getPassword());
        userJson.put("pronouns", user.getPronouns());
        userJson.put("description", user.getDescription());
        userJson.put("tutor availability", user.getTutorAvailability());
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
        // store user.expectedWage as a JSONObject
        JSONObject expectedWage = new JSONObject();
        for (String course : user.getExpectedWage().keySet()) {
            expectedWage.put(course, user.getExpectedWage().get(course));
        }
        userJson.put("expected_wage", expectedWage);
        // store user.expectedPrice as a JSONObject
        JSONObject expectedPrice = new JSONObject();
        for (String course : user.getExpectedPrice().keySet()) {
            expectedPrice.put(course, user.getExpectedPrice().get(course));
        }
        userJson.put("expected_price", expectedPrice);

        userFile.put(user.getUserID(), userJson);
        this.save();
        this.createUserinAPI(user.getUserID(), user.getNickname());
    }

    @Override
    public User getCurrentUser() {
        if (current_userid != null) {
            return this.get(current_userid);
        } else {
            return null;
        }
    }

    @Override
    public ArrayList<ChatChannel> getAllChatChannels() throws RuntimeException {
        SendBirdAPI sendBirdAPIObject = new SendBirdAPI("https://api-1F4C3D4F-01DB-4A99-8704-BE4CB1FE3AE5.sendbird.com/v3",
                    "1F4C3D4F-01DB-4A99-8704-BE4CB1FE3AE5",
                    "0ecfef313ab2989479b70e30e3ee37a1d105b770");
        try {
            ArrayList<ChatChannel> channels = sendBirdAPIObject.getAllChatChannels(this.get(current_userid).getUserID());
            if (channels == null || channels.isEmpty()) {
                throw new RuntimeException("Failed to get all chat channels");
            }
            return channels;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Channel getChannel(String currentUserid, String targetUserid) {
        return null;
    }
}
