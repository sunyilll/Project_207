package interface_adapter.go_to_profile;

import interface_adapter.ViewModel;

import java.util.List;
import java.util.Map;

public abstract class ProfileViewModel extends ViewModel {
    public static final String USERID_LABLE = "User ID";
    public static final String NICKNAME_LABEL = "Nickname";
    public static final String PRONOUNS_LABEL = "Pronouns";
    public static final String DESCRIPTION_LABEL = "About Me";
    public static final String PERSONALITY_TAGS_LABEL = "Personality Tags";

    public static final String TUTOR_TITLE_LABLE = "TUTOR INFORMATION";
    public static final String TUTOR_RATING_LABEL = "Tutor Rating";
    public static final String COURSES_TO_TEACH_LABEL = "Courses to Teach";
    public static final String TUTOR_AVAILABILITY_LABEL = "Tutor Availability";
    public static final String EXPECTED_WAGE_LABEL = "Expected Hourly Payment";

    public static final String STUDENT_TITLE_LABLE = "STUDENT INFORMATION";
    public static final String COURSES_TO_LEARN_LABEL = "Courses to Learn";
    public static final String STUDENT_RATING_LABEL = "Student Rating";
    public static final String EXPECTED_PRICE_LABEL = "Expected Hourly Price";

    public String useridText;
    public String nicknameText;
    public String pronounsText;
    public String descriptionText;
    public String personalityTagsText;
    public String tutorRatingText;
    public String coursesToTeachText;
    public String tutorAvailabilityText;
    public String expectedWageText;
    public String coursesToLearnText;
    public String studentRatingText;
    public String expectedPriceText;

    public ProfileViewModel(String viewName) {
        super(viewName);
    }

    protected static String listToString(List<String> list) {
        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            sb.append(s);
            sb.append(", ");
        }
        return removeLastChar(sb);
    }
    protected static String mapToString(Map<String, Integer> map) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append(": ");
            sb.append(entry.getValue());
            sb.append(", ");
        }
        return removeLastChar(sb);
    }
    private static String removeLastChar(StringBuilder sb) {
        String str = sb.toString().trim();
        if (!str.isEmpty() && str.charAt(str.length() - 1) == ',') {
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }

}
