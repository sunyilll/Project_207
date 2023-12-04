package interface_adapter.save_profile;

import use_case.save_profile.SaveProfileInputBoundary;
import use_case.save_profile.SaveProfileInputData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SaveProfileController {
    final private SaveProfileInputBoundary saveProfileInteractor;

    public SaveProfileController(SaveProfileInputBoundary saveProfileInteractor) {
        this.saveProfileInteractor = saveProfileInteractor;
    }

    public void execute(String nickname, String pronouns, String personalityTags, String description, String coursesToTeach, String tutorAvailability, String expectedWage, String preferredModeOfTeaching, String coursesToLearn, String expectedPrice, String preferredModeOfLearning) {
        SaveProfileInputData saveProfileInputData = new SaveProfileInputData( nickname, pronouns, stringToList(personalityTags), description, stringToList(coursesToTeach), tutorAvailability, stringToMap(expectedWage), stringToList(preferredModeOfTeaching), stringToList(coursesToLearn), stringToMap(expectedPrice), stringToList(preferredModeOfLearning));
        saveProfileInteractor.execute(saveProfileInputData);
    }
    private static List<String> stringToList(String string) {
        List<String> stringArray = new ArrayList<>();
        if (string == null) {
            return stringArray;
        }
        if (string.isEmpty()) {
            return stringArray;
        }
        String[] strings = string.split(",");
        for (int i = 0; i < strings.length; i++) {
            stringArray.add(strings[i].trim());
        }
        return stringArray;
    }
    private static Map<String, Integer> stringToMap(String string) {
        if (string == null) {
            return new HashMap<>();
        }
        if (string.isEmpty()) {
            return new HashMap<>();
        }
        Map<String, Integer> stringMap = new HashMap<>();
        String[] stringArray = string.split(",");
        for (int i = 0; i < stringArray.length; i++) {
            String[] element = stringArray[i].split(":");
            stringMap.put(element[0].trim(), Integer.parseInt(element[1].trim()));
        }
        return stringMap;
    }

    public static void main(String[] args) {
        Map<String, Integer> mymap = stringToMap("a:1, b: 2");
        List<String> mylist = stringToList("a, b, c");
        System.out.println(mymap);
        System.out.println(mylist);
    }
}
