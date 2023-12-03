package interface_adapter.go_to_personal_profile;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class GoToPersonalProfileViewModel extends ViewModel {
    private GoToPersonalProfileState state = new GoToPersonalProfileState();

    public static final String DESCRIPTION_LABEL = "About Me";
    public static final String PERSONALITY_TAGS_LABEL = "Personality Tags";
    public static final String COURSES_TO_TEACH_LABEL = "Courses to Teach";
    public static final String TUTOR_RATING_LABEL = "Tutor Rating";
    public static final String COURSES_TO_LEARN_LABEL = "Courses to Learn";
    public static final String STUDENT_RATING_LABEL = "Student Rating";
    public static final String EDIT_PROFILE_BUTTON_LABEL = "Edit Profile";
    public static final String SIGN_OUT_BUTTON_LABEL = "Sign Out";
    public final String HOME_BUTTON_LABEL = "Home";
    public final String CHAT_LIST_BUTTON_LABEL = "Chat";
    public final String PRIVATE_PROFILE_BUTTON_LABEL = "My Profile";

    public String nicknameText = "NICKNAME";
    public String descriptionText = "HELLO WORLD!";
    public String pronounsText = "";
    public String personalityTagsText = "";
    public String coursesToTeachText = "None";
    public String tutorRatingText = "N/A";
    public String coursesToLearnText = "None";
    public String studentRatingText = "N/A";

    public static final String viewName = "personal profile";

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public GoToPersonalProfileViewModel() {
        super(viewName);
    }

    public String getViewName() {
        return viewName;
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("personal profile", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public GoToPersonalProfileState getState() {
        return state;
    }

    public void setState(GoToPersonalProfileState state) {
        this.state = state;
        if (state.getUserid() == null) {
            return;
        }
        nicknameText = state.getNickname();
        descriptionText = state.getDescription();
        pronounsText = state.getPronouns();
        personalityTagsText = state.getPersonalityTags().toString();
        coursesToTeachText = state.getCoursesToTeach().toString();
        tutorRatingText = String.valueOf(state.getTutorRating());
        coursesToLearnText = state.getCoursesToLearn().toString();
        studentRatingText = String.valueOf(state.getStudentRating());
    }
}
