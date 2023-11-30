package main.java.app;

import main.java.interface_adapter.ViewManagerModel;
import main.java.interface_adapter.go_to_chat.GoToChannelController;
import main.java.interface_adapter.go_to_chat.GoToChannelPresenter;
import main.java.interface_adapter.go_to_chat.GoToChannelViewModel;
import main.java.interface_adapter.go_to_chatl_list.GoToChatListController;
import main.java.interface_adapter.go_to_chatl_list.GoToChatListPresenter;
import main.java.interface_adapter.go_to_chatl_list.GoToChatListViewModel;
import main.java.interface_adapter.go_to_personal_profile.GoToPersonalProfileController;
import main.java.interface_adapter.go_to_personal_profile.GoToPersonalProfilePresenter;
import main.java.interface_adapter.go_to_personal_profile.GoToPersonalProfileViewModel;
import main.java.use_case.go_to_chat.GoToChannelInputBoundary;
import main.java.use_case.go_to_chat.GoToChannelInteractor;
import main.java.use_case.go_to_chat.GoToChannelOutputBoundary;
import main.java.use_case.go_to_chat_list.GoToChatListDataAccessInterface;
import main.java.use_case.go_to_chat_list.GoToChatListInteractor;
import main.java.use_case.go_to_personal_profile.GoToPersonalProfileInputBoundary;
import main.java.use_case.go_to_personal_profile.GoToPersonalProfileInteractor;
import main.java.use_case.go_to_personal_profile.GoToPersonalProfileOutputBoundary;
import main.java.view.ChatListView.ChatListView;

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
            GoToChannelViewModel goToChannelViewModel
    ) {
        try {
            GoToChatListController goToChatListController = createGoToChatListUseCase(viewManagerModel, goToChatListViewModel, goToChatListDataAccessObject);
            GoToChannelController goToChannelController = createGoToChannelUseCase(viewManagerModel, goToChannelViewModel);
            GoToPersonalProfileController goToPersonalProfileController = createGoToPersonalProfileUseCase(viewManagerModel, goToPersonalProfileViewModel);
            return new ChatListView(goToChatListViewModel, goToChatListController, goToChannelViewModel, goToChannelController,
                    goToPersonalProfileViewModel, goToPersonalProfileController
            );
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
            GoToPersonalProfileViewModel goToPersonalProfileViewModel) throws IOException {

        GoToPersonalProfileOutputBoundary personalProfilePresenter =
                new GoToPersonalProfilePresenter(goToPersonalProfileViewModel, viewManagerModel);

        GoToPersonalProfileInputBoundary personalProfileInteractor =
                new GoToPersonalProfileInteractor(personalProfilePresenter);

        return new GoToPersonalProfileController(personalProfileInteractor);
    }
}
