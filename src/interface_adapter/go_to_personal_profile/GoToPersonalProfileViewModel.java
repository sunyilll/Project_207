package interface_adapter.go_to_personal_profile;

import interface_adapter.ViewModel;
import interface_adapter.go_to_profile.ProfileViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class GoToPersonalProfileViewModel extends ProfileViewModel {
    private GoToPersonalProfileState state = new GoToPersonalProfileState();

    public static final String EDIT_PROFILE_BUTTON_LABEL = "Edit Profile";
    public static final String SIGN_OUT_BUTTON_LABEL = "Sign Out";


    public static final String viewName = "personal profile";

    public GoToPersonalProfileViewModel() {
        super(viewName);
    }
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("personal profile", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public String getViewName() {
        return viewName;
    }

    public GoToPersonalProfileState getState() {
        return state;
    }

    public void setState(GoToPersonalProfileState state) {
        this.state = state;
        if (state.getUserid() == null) {
            return;
        }
        useridText = state.getUserid();
        nicknameText = state.getNickname();
        descriptionText = state.getDescription();
        pronounsText = state.getPronouns();
        personalityTagsText = listToString(state.getPersonalityTags());
        coursesToTeachText = listToString(state.getCoursesToTeach());
        tutorRatingText = Float.toString(state.getTutorRating());
        tutorAvailabilityText = state.getTutorAvailability();
        expectedWageText = mapToString(state.getExpectedWage());
        coursesToLearnText = listToString(state.getCoursesToLearn());
        studentRatingText = Float.toString(state.getStudentRating());
        expectedPriceText = mapToString(state.getExpectedPrice());
    }

}
