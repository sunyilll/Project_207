package interface_adapter.go_to_public_profile;

import interface_adapter.ViewManagerModel;
import interface_adapter.go_to_public_profile.GoToPublicProfileViewModel;
import use_case.go_to_public_profile.GoToPublicProfileOutputBoundary;
import use_case.go_to_public_profile.GoToPublicProfileOutputData;

public class GoToPublicProfilePresenter implements GoToPublicProfileOutputBoundary {
    private final GoToPublicProfileViewModel viewModel;
    private final ViewManagerModel viewManager;

    public GoToPublicProfilePresenter(GoToPublicProfileViewModel viewModel, ViewManagerModel viewManager) {
        this.viewModel = viewModel;
        this.viewManager = viewManager;
    }
    @Override
    public void prepareSuccessView(GoToPublicProfileOutputData response) {
        GoToPublicProfileState state = viewModel.getState();
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
        state.setTutorRating(response.getTutorRating());
        state.setStudentRating(response.getStudentRating());
        state.setPreferredModeOfLearning(response.getPreferredModeOfLearning());
        state.setPreferredModeOfTeaching(response.getPreferredModeOfTeaching());

        this.viewModel.setState(state);
        viewModel.firePropertyChanged();

        viewManager.setActiveView(viewModel.getViewName());
        viewManager.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        System.out.println("Failed to prepare Public Profile View");
        // TODO: implement this
    }
}
