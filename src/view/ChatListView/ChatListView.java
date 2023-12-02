package view.ChatListView;

import entity.ChatChannel;
import interface_adapter.ViewManagerModel;
import interface_adapter.go_to_channel.GoToChannelController;
import interface_adapter.go_to_channel.GoToChannelViewModel;
import interface_adapter.go_to_chatl_list.GoToChatListController;
import interface_adapter.go_to_chatl_list.GoToChatListViewModel;
import interface_adapter.go_to_personal_profile.GoToPersonalProfileController;
import interface_adapter.go_to_personal_profile.GoToPersonalProfileViewModel;
import interface_adapter.go_to_search.GoToSearchController;
import view.HomeBar;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class ChatListView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "chatList";
    private final GoToChatListViewModel goToChatListViewModel1;
    private final GoToChatListController goToChatListController1;
    private final GoToChannelViewModel goToChannelViewModel1;
    private final GoToChannelController goToChannelController1;
    private final GoToPersonalProfileViewModel goToPersonalProfileViewModel1;
    private final GoToPersonalProfileController goToPersonalProfileController1;
    private final ViewManagerModel viewManagerModel1;
    private final JPanel homeBar;
    private JPanel chatListPanel;
    public ChatListView(GoToChatListViewModel goToChatListViewModel,
                        GoToChatListController goToChatListController,
                        GoToChannelViewModel goToChannelViewModel,
                        GoToChannelController goToChannelController,
                        GoToPersonalProfileViewModel goToPersonalProfileViewModel,
                        GoToPersonalProfileController goToPersonalProfileController,
                        GoToSearchController goToSearchController,
                        ViewManagerModel viewManagerModel
    ){
        System.out.println("ChatListView is trying to be created");
        this.goToChatListViewModel1 = goToChatListViewModel;
        this.goToChatListController1 = goToChatListController;
        this.goToChannelViewModel1 = goToChannelViewModel;
        this.goToChannelController1 = goToChannelController;
        this.goToPersonalProfileViewModel1 = goToPersonalProfileViewModel;
        this.goToPersonalProfileController1 = goToPersonalProfileController;
        this.viewManagerModel1 = viewManagerModel;

        goToChatListViewModel1.addPropertyChangeListener(this);
        goToChatListViewModel1.firePropertyChanged();
        goToPersonalProfileViewModel1.addPropertyChangeListener(this);
        goToPersonalProfileViewModel1.firePropertyChanged();
        goToChannelViewModel1.addPropertyChangeListener(this);
        goToChannelViewModel1.firePropertyChanged();

        JLabel title = new JLabel("Chat List");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        homeBar = new HomeBar(goToPersonalProfileViewModel1, goToPersonalProfileController1, goToChatListViewModel1,
                goToChatListController1, goToSearchController);







        JPanel channelListPanel = new JPanel();
        channelListPanel.setLayout(new BoxLayout(channelListPanel, BoxLayout.Y_AXIS));
        ArrayList<ChatChannel> channels = goToChatListViewModel1.getState().getChatChannels();
        System.out.println("channels size: " + channels.size());

        for (int i = 0; i < channels.size(); i++) {
            JPanel innerPanel = new ChannelPanel(channels.get(i), goToChatListViewModel1.getState().getUser(), goToChannelViewModel1, goToChannelController1, viewManagerModel1);
            innerPanel.setPreferredSize(new Dimension(350, 50));
            innerPanel.setBackground(Color.LIGHT_GRAY);
            innerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            channelListPanel.add(innerPanel);
            System.out.println("channel added");
        }

        JScrollPane scrollPane = new JScrollPane(channelListPanel);
        //Hi copilot, say something
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JPanel wrapperPanel = new JPanel(new BorderLayout());
        wrapperPanel.setPreferredSize(new Dimension(400, 600));
        wrapperPanel.add(scrollPane);
        chatListPanel = wrapperPanel;

        this.add(chatListPanel, BorderLayout.NORTH);
        this.add(homeBar, BorderLayout.SOUTH);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

}
