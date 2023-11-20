package main.java.interface_adapter.go_to_personal_profile;

import main.java.interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;

public class GoToPersonalProfileViewModel extends ViewModel {
    public final String TITLE_LABEL = "My Profile View";
    public final String NICKNAME_TEXT = "Nickname";
    public final String ABOUT_ME_LABEL = "About Me";
    public final String ABOUT_ME_TEXT = "HELLO WORLD";
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


    public GoToPersonalProfileViewModel() {
        super("personal profile");
    }

    public GoToPersonalProfileViewModel(String viewName) {
        super(viewName);
    }

    @Override
    public void firePropertyChanged() {
        // TODO Auto-generated method stub
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        // TODO Auto-generated method stub
    }
}
