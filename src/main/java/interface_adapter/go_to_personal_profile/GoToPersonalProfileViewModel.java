package main.java.interface_adapter.go_to_personal_profile;

import main.java.interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class GoToPersonalProfileViewModel extends ViewModel {
    private GoToPersonalProfileState state = new GoToPersonalProfileState();
    public final String TITLE_LABEL = "My Profile View";
    public final String DESCRIPTION_LABEL = "About Me";
    public final String PERSONALITY_TAGS_LABEL = "Personality Tags";
    public final String COURSES_TO_TEACH_LABEL = "Courses to Teach";
    public final String TUTOR_RATING_LABEL = "Tutor Rating";
    public final String COURSES_TO_LEARN_LABEL = "Courses to Learn";
    public final String STUDENT_RATING_LABEL = "Student Rating";
    public final String EDIT_PROFILE_BUTTON_LABEL = "Edit Profile";
    public final String SIGN_OUT_BUTTON_LABEL = "Sign Out";
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

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public GoToPersonalProfileViewModel() {
        super("personal profile");
    }

    public GoToPersonalProfileViewModel(String viewName) {
        super(viewName);
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
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
        nicknameText = state.getUser().getNickname();
        descriptionText = state.getUser().getDescription();
        pronounsText = state.getUser().getPronouns();
        personalityTagsText = state.getUser().getPersonalityTags().toString();
        coursesToTeachText = state.getUser().getCoursesToTeach().toString();
        tutorRatingText = String.valueOf(state.getUser().getTutorRating());
        coursesToLearnText = state.getUser().getCoursesToLearn().toString();
        studentRatingText = String.valueOf(state.getUser().getStudentRating());
    }
}
