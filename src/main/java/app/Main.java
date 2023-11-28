package main.java.app;

import main.java.data_access.FileUserDataAccessObject;
import main.java.entity.UserFactory;
import main.java.entity.User;
import main.java.interface_adapter.To_signup.ToSignupViewModel;
import main.java.interface_adapter.ViewManagerModel;
import main.java.interface_adapter.login.LoginViewModel;
import main.java.interface_adapter.search.SearchState;
import main.java.interface_adapter.search.SearchViewModel;
import main.java.interface_adapter.signup.SignupViewModel;
import main.java.view.*;


import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.time.LocalDateTime;


public class Main {

    public static void main(String[] args) {
//        JFrame application = new JFrame("Send Message example");
//        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//
//        CardLayout cardLayout = new CardLayout();
//        JPanel views = new JPanel(cardLayout);
//        application.add(views);
//
//        ViewManagerModel viewManagerModel = new ViewManagerModel();
//        new ViewManager(views, cardLayout, viewManagerModel);
//
//
//        /*
//        * 这里手动initialize一个currentuser和chatchannel, state
//        * */
//        User testUser1 = new User("test1", "test1", "test1", "male");
//        User testUser2 = new User("test1", "test1", "test1", "male");
//
//        Map<String, User> testMap = new HashMap<>();
//        testMap.put("test1", testUser1);
//        testMap.put("test2", testUser2);
//        LocalDateTime currentDateTime = LocalDateTime.now();
//
//
//        ChatChannel channel = new ChatChannel(testMap, currentDateTime, "sendbird_group_channel_12586989_cbf2eb24180c0399084a22b8acb6519571db23f7");
//
//        SendMessageState testState = new SendMessageState(testUser1, channel);
//        /*
//        * 这里结束手动initialize一个currentuser和chatchannel
//        * */
//
//        SendMessageViewModel sendMessageViewModel = new SendMessageViewModel(testUser1, channel, testState);


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


        LoginViewModel loginViewModel = new LoginViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        ToSignupViewModel toSignupViewModel = new ToSignupViewModel();

        SearchState initialState = new SearchState();
        SearchViewModel searchViewModel = new SearchViewModel(initialState);

//        SendMessageDataAccessObject sendMessageDataAccessObject;
//        try {
//            sendMessageDataAccessObject = new SendMessageDataAccessObject(
//                    "https://api-1F4C3D4F-01DB-4A99-8704-BE4CB1FE3AE5.sendbird.com/v3",
//                    "1F4C3D4F-01DB-4A99-8704-BE4CB1FE3AE5"
//
//            );
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        FileUserDataAccessObject userDataAccessObject;
        try {
            userDataAccessObject = new FileUserDataAccessObject("./users.csv", new UserFactory() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//        ChannelView channelView = ChannelUseCasesFactory.create(viewManagerModel, sendMessageViewModel, sendMessageDataAccessObject);
//        views.add(channelView, channelView.viewName);
//
//        viewManagerModel.setActiveView(channelView.viewName);
//        viewManagerModel.firePropertyChanged();


        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel, userDataAccessObject);
        views.add(signupView, signupView.viewName);

        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, toSignupViewModel, signupViewModel,searchViewModel,userDataAccessObject, userDataAccessObject);
        views.add(loginView, loginView.viewName);

//        SearchView searchView = SearchUseCaseFactory.create(viewManagerModel, loginViewModel, toSignupViewModel, signupViewModel,searchViewModel,userDataAccessObject, userDataAccessObject);
//        views.add(searchView, searchView.viewName);

        viewManagerModel.setActiveView(loginView.viewName);
        viewManagerModel.firePropertyChanged();


        // application.pack();
        application.setVisible(true);



    }

}
