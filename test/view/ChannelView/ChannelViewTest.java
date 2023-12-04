package view.ChannelView;

import algorithmn.MatchByStudentRating;
import algorithmn.MatchByTutorRating;
import algorithmn.MatchStudentAlgorithm;
import algorithmn.MatchTutorAlgorithm;
import app.*;
import data_access.JsonCourseDataAccessObject;
import data_access.JsonUserDataAccessObject;
import data_access.RefreshChatPageDataAccessObject;
import data_access.SendMessageDataAccessObject;
import entity.CourseFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.go_to_channel.GoToChannelState;
import interface_adapter.go_to_channel.GoToChannelViewModel;
import interface_adapter.go_to_chatl_list.GoToChatListState;
import interface_adapter.go_to_chatl_list.GoToChatListViewModel;
import interface_adapter.go_to_personal_profile.GoToPersonalProfileViewModel;
import interface_adapter.go_to_signup.ToSignupViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.refresh_chat_page.RefreshChatPageState;
import interface_adapter.refresh_chat_page.RefreshChatPageViewModel;
import interface_adapter.search_course.SearchCourseViewModel;
import interface_adapter.search_course_result.SearchCourseResultViewModel;
import interface_adapter.send_message.SendMessageState;
import interface_adapter.send_message.SendMessageViewModel;
import interface_adapter.signup.SignupViewModel;
import kotlin.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.*;
import view.ChatListView.ChatListView;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ChannelViewTest {

    ChannelView channelView;

    @BeforeEach
    void setUp() {
        GoToChatListState goToChatListState = new GoToChatListState();
        GoToChannelState goToChannelState = new GoToChannelState();
        SendMessageState testState = new SendMessageState();
        RefreshChatPageState refreshTestState = new RefreshChatPageState();

        // FIXME: instantiating a ViewModel shouldn't depend on inputs
        SendMessageViewModel sendMessageViewModel = new SendMessageViewModel();
        sendMessageViewModel.setState(testState);
        SendMessageDataAccessObject sendMessageDataAccessObject;

        RefreshChatPageViewModel refreshChatPageViewModel = new RefreshChatPageViewModel();
        refreshChatPageViewModel.setState(refreshTestState);
        RefreshChatPageDataAccessObject refreshChatPageDataAccessObject;

        GoToChatListViewModel goToChatListViewModel = new GoToChatListViewModel();
        goToChatListViewModel.setState(goToChatListState);

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
        ViewManagerModel viewManagerModel = new ViewManagerModel();

        ChannelView channelView = ChannelUseCasesFactory.create(viewManagerModel, sendMessageViewModel, sendMessageDataAccessObject, refreshChatPageViewModel, refreshChatPageDataAccessObject, goToChatListViewModel, jsonUserDataAccessObject, goToChannelViewModel);
        this.channelView = channelView;
    }

    @Test
    void setChatArea() {
        try {
            ArrayList messageUser = new ArrayList<>();
            messageUser.add(new Pair("a","a"));
            channelView.setChatArea(new ArrayList<>());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void actionPerformed() {
        try {
            channelView.actionPerformed(null);
        } catch (Exception e) {

        }
    }

    @Test
    void propertyChange() {
        try {
            PropertyChangeEvent propertyChangeEvent = new PropertyChangeEvent(this, "channel", null, null);
            channelView.propertyChange(propertyChangeEvent);
        } catch (Exception e) {

        }
    }
    @Test
    void clickSendButton() {
        try {
            channelView.getSendButton().doClick();
        } catch (Exception e) {

        }
    }
    @Test
    void clickBackButton() {
        try {
            channelView.getBackButton().doClick();
        } catch (Exception e) {

        }
    }
    @Test
    void clickRefreshButton() {
        try {
            channelView.getRefreshButton().doClick();
        } catch (Exception e) {

        }
    }
}