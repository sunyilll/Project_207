package interface_adapter.go_to_public_profile;

import interface_adapter.ViewModel;
import interface_adapter.go_to_personal_profile.GoToPersonalProfileState;
import interface_adapter.go_to_profile.ProfileViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class GoToPublicProfileViewModel extends ProfileViewModel {
    private GoToPublicProfileState state = new GoToPublicProfileState();

    public static final String MESSAGE_BUTTON_LABEL = "Send Message";

    public static final String viewName = "public profile";

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public GoToPublicProfileViewModel() {
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

    public GoToPublicProfileState getState() {
        return state;
    }

    public void setState(GoToPublicProfileState state) {
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
