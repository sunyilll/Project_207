package interface_adapter.edit_profile;

import interface_adapter.go_to_profile.ProfileViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class EditProfileViewModel extends ProfileViewModel {
    private EditProfileState state = new EditProfileState();

    public static final String BACK_BUTTON_LABEL = "Back";
    public static final String SAVE_BUTTON_LABEL = "Save";


    public static final String viewName = "edit profile";

    public EditProfileViewModel() {
        super(viewName);
    }
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("edit profile", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public String getViewName() {
        return viewName;
    }

    public EditProfileState getState() {
        return state;
    }

    public void setState(EditProfileState state) {
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
        tutorAvailabilityText = state.getTutorAvailability();
        expectedWageText = mapToString(state.getExpectedWage());
        coursesToLearnText = listToString(state.getCoursesToLearn());
        expectedPriceText = mapToString(state.getExpectedPrice());
    }

}
