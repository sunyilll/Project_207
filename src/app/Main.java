package app;

import algorithmn.MatchByStudentRating;
import algorithmn.MatchByTutorRating;
import algorithmn.MatchStudentAlgorithm;
import algorithmn.MatchTutorAlgorithm;
import data_access.*;
import entity.*;
import interface_adapter.go_to_channel.GoToChannelState;
import interface_adapter.go_to_channel.GoToChannelViewModel;
import interface_adapter.go_to_chatl_list.GoToChatListState;
import interface_adapter.go_to_chatl_list.GoToChatListViewModel;
import interface_adapter.go_to_personal_profile.GoToPersonalProfileViewModel;
import interface_adapter.refresh_chat_page.RefreshChatPageState;
import interface_adapter.refresh_chat_page.RefreshChatPageViewModel;
import interface_adapter.search_course.SearchCourseViewModel;
import interface_adapter.search_course_result.SearchCourseResultViewModel;
import interface_adapter.send_message.SendMessageState;
import interface_adapter.send_message.SendMessageViewModel;
import view.*;

import interface_adapter.ViewManagerModel;
import interface_adapter.go_to_signup.ToSignupViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;
import view.ChannelView.ChannelView;
import view.ChatListView.ChatListView;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Main {

    public static void main(String[] args) {
        // Login & Signup
        JFrame application = new FrameModel("DYP");

        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);

        application.add(views);

        // This keeps track of and manages which view is currently showing.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        // This is a temporary solution to the problem of not having a user to pass to the home bar.
        // FIXME: Will be removed.
        User testUser1 = new User("test1", "test1", "test1");
        User testUser2 = new User("test2", "test2", "test2");
        LocalDateTime currentDateTime = LocalDateTime.now();
        Map<String, User> testMap = new HashMap<>();
        testMap.put("test1", testUser1);
        testMap.put("test2", testUser2);
        ChatChannel channel = new ChatChannel(testMap, currentDateTime, "sendbird_group_channel_12586989_cbf2eb24180c0399084a22b8acb6519571db23f7");
        ArrayList<ChatChannel> channels = new ArrayList<>();
        channels.add(channel);

        SendMessageState testState = new SendMessageState(testUser1, channel);
        RefreshChatPageState refreshTestState = new RefreshChatPageState(testUser1, channel);
//        GoToChatListState goToChatListState = new GoToChatListState(testUser1, channels);
        GoToChannelState goToChannelState = new GoToChannelState(testUser1, channel);
        GoToChatListState goToChatListState = new GoToChatListState();

        LoginViewModel loginViewModel = new LoginViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        ToSignupViewModel toSignupViewModel = new ToSignupViewModel();
        SearchCourseViewModel searchCourseViewModel = new SearchCourseViewModel();
        SearchCourseResultViewModel searchCourseResultViewModel = new SearchCourseResultViewModel();
        GoToPersonalProfileViewModel goToPersonalProfileViewModel = new GoToPersonalProfileViewModel();

        // FIXME: instantiating a ViewModel shouldn't depend on inputs
        SendMessageViewModel sendMessageViewModel = new SendMessageViewModel();
        sendMessageViewModel.setState(testState);
        SendMessageDataAccessObject sendMessageDataAccessObject;

        RefreshChatPageViewModel refreshChatPageViewModel = new RefreshChatPageViewModel();
        refreshChatPageViewModel.setState(refreshTestState);
        RefreshChatPageDataAccessObject refreshChatPageDataAccessObject;

        GoToChatListViewModel goToChatListViewModel = new GoToChatListViewModel();
        goToChatListViewModel.setState(goToChatListState);
//        GoToChatListDataAccessObject goToChatListDataAccessObject;

        GoToChannelViewModel goToChannelViewModel = new GoToChannelViewModel();
        goToChannelViewModel.setState(goToChannelState);


        sendMessageDataAccessObject = new SendMessageDataAccessObject(
                "https://api-1F4C3D4F-01DB-4A99-8704-BE4CB1FE3AE5.sendbird.com/v3",
                "1F4C3D4F-01DB-4A99-8704-BE4CB1FE3AE5"

        );
        refreshChatPageDataAccessObject = new RefreshChatPageDataAccessObject();
//        goToChatListDataAccessObject = new GoToChatListDataAccessObject();

        JsonUserDataAccessObject jsonUserDataAccessObject;
        jsonUserDataAccessObject = new JsonUserDataAccessObject("./users.json");

        JsonCourseDataAccessObject jsonCourseDataAccessObject;
        jsonCourseDataAccessObject = new JsonCourseDataAccessObject("./courses.json", new CourseFactory(jsonUserDataAccessObject));
        MatchTutorAlgorithm matchTutorAlgorithm = new MatchByTutorRating();
        MatchStudentAlgorithm matchStudentAlgorithm = new MatchByStudentRating();

        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel,
                jsonUserDataAccessObject);
        views.add(signupView, signupView.viewName);

        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, toSignupViewModel, signupViewModel,searchCourseViewModel,jsonUserDataAccessObject, jsonUserDataAccessObject);
        views.add(loginView, loginView.viewName);

        SearchCourseView searchCourseView = SearchCourseUseCaseFactory.create(viewManagerModel, searchCourseViewModel,
                searchCourseResultViewModel, jsonCourseDataAccessObject, jsonUserDataAccessObject, goToPersonalProfileViewModel, jsonUserDataAccessObject, goToChatListViewModel, jsonUserDataAccessObject, matchTutorAlgorithm, matchStudentAlgorithm);

        views.add(searchCourseView, searchCourseView.viewName);

        SearchCourseResultView searchCourseResultView = SearchCourseResultUseCaseFactory.create(viewManagerModel, searchCourseViewModel,
                searchCourseResultViewModel, goToPersonalProfileViewModel, jsonUserDataAccessObject, goToChatListViewModel, jsonUserDataAccessObject);
        views.add(searchCourseResultView, searchCourseResultView.viewName);
      
        PersonalProfileView personalProfileView = ToPersonalProfileUseCaseFactory.create(viewManagerModel,
                goToPersonalProfileViewModel, jsonUserDataAccessObject, goToChatListViewModel, jsonUserDataAccessObject, searchCourseViewModel);
        views.add(personalProfileView, personalProfileView.viewName);

        ChannelView channelView = ChannelUseCasesFactory.create(viewManagerModel, sendMessageViewModel, sendMessageDataAccessObject, refreshChatPageViewModel, refreshChatPageDataAccessObject, goToChatListViewModel, jsonUserDataAccessObject);
        views.add(channelView, channelView.viewName);

        ChatListView chatListView = ChatListUsesCaseFactory.create(viewManagerModel, goToChatListViewModel, jsonUserDataAccessObject, goToPersonalProfileViewModel, jsonUserDataAccessObject, goToChannelViewModel, searchCourseViewModel);
        views.add(chatListView, chatListView.viewName);

        // This is for testing purposes. Please delete this for final submission
//        HomeBar homeBar = HomeBarUseCaseFactory.create(viewManagerModel, goToPersonalProfileViewModel,
//                goToChatListViewModel, goToChatListDataAccessObject);
//        views.add(homeBar, homeBar.viewName);

        // This is for testing purposes. Please change the View name to the one you want to test.
//        viewManagerModel.setActiveView(homeBar.viewName);
        viewManagerModel.setActiveView(loginView.viewName);

        viewManagerModel.firePropertyChanged();

        application.setVisible(true);
    }
}
