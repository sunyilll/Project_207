package interface_adapter.edit_profile;

import interface_adapter.ViewManagerModel;
import use_case.edit_profile.EditProfileOutputBoundary;
import use_case.edit_profile.EditProfileOutputData;
import use_case.go_to_personal_profile.GoToPersonalProfileOutputBoundary;
import use_case.go_to_personal_profile.GoToPersonalProfileOutputData;

public class EditProfilePresenter implements EditProfileOutputBoundary {
    private final EditProfileViewModel viewModel;
    private final ViewManagerModel viewManager;

    public EditProfilePresenter(EditProfileViewModel viewModel, ViewManagerModel viewManager) {
        this.viewModel = viewModel;
        this.viewManager = viewManager;
    }
    @Override
    public void prepareSuccessView(EditProfileOutputData response) {
        EditProfileState state = viewModel.getState();
        state.setUserid(response.getUserid());
        state.setNickname(response.getNickname());
        state.setDescription(response.getDescription());
        state.setPronouns(response.getPronouns());
        state.setPersonalityTags(response.getPersonalityTags());
        state.setTutorAvailability(response.getTutorAvailability());
        state.setCoursesToLearn(response.getCoursesToLearn());
        state.setCoursesToTeach(response.getCoursesToTeach());
        state.setExpectedPrice(response.getExpectedPrice());
        state.setExpectedWage(response.getExpectedWage());
        state.setPreferredModeOfLearning(response.getPreferredModeOfLearning());
        state.setPreferredModeOfTeaching(response.getPreferredModeOfTeaching());

        this.viewModel.setState(state);
        viewModel.firePropertyChanged();

        viewManager.setActiveView(viewModel.getViewName());
        viewManager.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        System.out.println("Failed to prepare Personal Profile View");
        // TODO: implement this
    }
}
