package main.java.app;

import main.java.data_access.SendMessageDataAccessObject;
import main.java.interface_adapter.ViewManagerModel;
import main.java.interface_adapter.send_message.SendMessageViewModel;
import main.java.view.ChannelView;
import main.java.view.ViewManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        JFrame application = new JFrame("Send Message example");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);


        SendMessageViewModel sendMessageViewModel = new SendMessageViewModel();
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

        application.pack();
        application.setVisible(true);

    }
}
