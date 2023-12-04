package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.go_to_channel.GoToChannelController;
import interface_adapter.go_to_channel.GoToChannelPresenter;
import interface_adapter.go_to_channel.GoToChannelViewModel;
import interface_adapter.go_to_chat_list.GoToChatListController;
import interface_adapter.go_to_chat_list.GoToChatListPresenter;
import interface_adapter.go_to_chat_list.GoToChatListViewModel;
import interface_adapter.go_to_personal_profile.GoToPersonalProfileController;
import interface_adapter.go_to_personal_profile.GoToPersonalProfilePresenter;
import interface_adapter.go_to_personal_profile.GoToPersonalProfileViewModel;
import interface_adapter.go_to_search.GoToSearchController;
import interface_adapter.go_to_search.GoToSearchPresenter;
import interface_adapter.search_course.SearchCourseViewModel;
import use_case.go_to_channel.GoToChannelInputBoundary;
import use_case.go_to_channel.GoToChannelInteractor;
import use_case.go_to_channel.GoToChannelOutputBoundary;
import use_case.go_to_chat_list.GoToChatListDataAccessInterface;
import use_case.go_to_chat_list.GoToChatListInteractor;
import use_case.go_to_personal_profile.GoToPersonalProfileDataAccessInterface;
import use_case.go_to_personal_profile.GoToPersonalProfileInputBoundary;
import use_case.go_to_personal_profile.GoToPersonalProfileInteractor;
import use_case.go_to_personal_profile.GoToPersonalProfileOutputBoundary;
import use_case.go_to_search.GoToSearchInputBoundary;
import use_case.go_to_search.GoToSearchInteractor;
import view.ChatListView.ChatListView;

import javax.swing.*;
import java.io.IOException;

public class ChatListUsesCaseFactory {
    private ChatListUsesCaseFactory() {
    }

    public static ChatListView create(
            ViewManagerModel viewManagerModel,
            GoToChatListViewModel goToChatListViewModel,
            GoToChatListDataAccessInterface goToChatListDataAccessObject,
            GoToPersonalProfileViewModel goToPersonalProfileViewModel,
            GoToPersonalProfileDataAccessInterface goToPersonalProfileDataAccessObject,
            GoToChannelViewModel goToChannelViewModel,
            SearchCourseViewModel searchCourseViewModel
    ) {
        try {
            GoToChatListController goToChatListController =
                    createGoToChatListUseCase(viewManagerModel, goToChatListViewModel, goToChatListDataAccessObject);
            GoToChannelController goToChannelController =
                    createGoToChannelUseCase(viewManagerModel, goToChannelViewModel);
            GoToPersonalProfileController goToPersonalProfileController =
                    createGoToPersonalProfileUseCase(viewManagerModel, goToPersonalProfileViewModel, goToPersonalProfileDataAccessObject);
            GoToSearchController goToSearchController =
                    createGoToSearchUseCase(viewManagerModel, searchCourseViewModel);
            return new ChatListView(goToChatListViewModel, goToChatListController,
                    goToChannelViewModel, goToChannelController,
                    goToPersonalProfileViewModel, goToPersonalProfileController,
                    goToSearchController, viewManagerModel);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }

    private static GoToChatListController createGoToChatListUseCase(
            ViewManagerModel viewManagerModel,
            GoToChatListViewModel goToChatListViewModel,
            GoToChatListDataAccessInterface goToChatListDataAccessObject
    ) throws IOException {
        GoToChatListController goToChatListController = new GoToChatListController(
                new GoToChatListInteractor(
                        goToChatListDataAccessObject,
                        new GoToChatListPresenter(goToChatListViewModel, viewManagerModel)
                )
        );
        return goToChatListController;
    }
    private static GoToChannelController createGoToChannelUseCase(
            ViewManagerModel viewManagerModel,
            GoToChannelViewModel goToChannelViewModel) throws IOException {

        GoToChannelOutputBoundary channelPresenter = new GoToChannelPresenter(goToChannelViewModel, viewManagerModel);

        GoToChannelInputBoundary channelInteractor = new GoToChannelInteractor(channelPresenter);

        return new GoToChannelController(channelInteractor);
    }
    private static GoToPersonalProfileController createGoToPersonalProfileUseCase(
            ViewManagerModel viewManagerModel,
            GoToPersonalProfileViewModel goToPersonalProfileViewModel,
            GoToPersonalProfileDataAccessInterface goToPersonalProfileDataAccessObject) throws IOException {

        GoToPersonalProfileOutputBoundary personalProfilePresenter =
                new GoToPersonalProfilePresenter(goToPersonalProfileViewModel, viewManagerModel);

        GoToPersonalProfileInputBoundary personalProfileInteractor =
                new GoToPersonalProfileInteractor(personalProfilePresenter, goToPersonalProfileDataAccessObject);

        return new GoToPersonalProfileController(personalProfileInteractor);
    }
    private static GoToSearchController createGoToSearchUseCase(
            ViewManagerModel viewManagerModel,
            SearchCourseViewModel searchCourseViewModel
    ){
        GoToSearchPresenter presenter = new GoToSearchPresenter(searchCourseViewModel, viewManagerModel);
        GoToSearchInputBoundary interactor = new GoToSearchInteractor(presenter);
        return new GoToSearchController(interactor);
    }
}
