package main.java.app;

import main.java.data_access.SendMessageDataAccessObject;
import main.java.entity.ChatChannel;
import main.java.entity.User;
import main.java.interface_adapter.ViewManagerModel;
import main.java.interface_adapter.send_message.SendMessageState;
import main.java.interface_adapter.send_message.SendMessageViewModel;
import main.java.view.ChannelView;
import main.java.view.FrameModel;
import main.java.view.ViewManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        JFrame application = new FrameModel("Tutoring APP");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();
        JPanel views = new JPanel(cardLayout);  // 所有view加入这个views
        application.add(views);

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);


        /*
        * 这里手动initialize一个currentuser和chatchannel, state
        * */
        User testUser1 = new User("test1", "test1", "test1", "male");
        User testUser2 = new User("test1", "test1", "test1", "male");

        Map<String, User> testMap = new HashMap<>();
        testMap.put("test1", testUser1);
        testMap.put("test2", testUser2);
        LocalDateTime currentDateTime = LocalDateTime.now();


        ChatChannel channel = new ChatChannel(testMap, currentDateTime, "sendbird_group_channel_12586989_cbf2eb24180c0399084a22b8acb6519571db23f7");

        SendMessageState testState = new SendMessageState(testUser1, channel);
        /*
        * 这里结束手动initialize一个currentuser和chatchannel
        * */

        SendMessageViewModel sendMessageViewModel = new SendMessageViewModel(testUser1, channel, testState);
        SendMessageDataAccessObject sendMessageDataAccessObject;
        try {
            sendMessageDataAccessObject = new SendMessageDataAccessObject(
                    "https://api-1F4C3D4F-01DB-4A99-8704-BE4CB1FE3AE5.sendbird.com/v3",
                    "1F4C3D4F-01DB-4A99-8704-BE4CB1FE3AE5"

            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ChannelView channelView = ChannelUseCasesFactory.create(viewManagerModel, sendMessageViewModel, sendMessageDataAccessObject);
        views.add(channelView, channelView.viewName);

        viewManagerModel.setActiveView(channelView.viewName);
        viewManagerModel.firePropertyChanged();

//        application.pack();
        application.setVisible(true);

    }
}
