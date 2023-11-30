package main.java.view.ChatListView;

import main.java.entity.ChatChannel;
import main.java.entity.User;
import main.java.interface_adapter.go_to_chat.GoToChannelController;
import main.java.interface_adapter.go_to_chat.GoToChannelViewModel;
import main.java.interface_adapter.go_to_chatl_list.GoToChatListController;
import main.java.interface_adapter.go_to_chatl_list.GoToChatListState;
import main.java.interface_adapter.go_to_chatl_list.GoToChatListViewModel;
import main.java.interface_adapter.go_to_personal_profile.GoToPersonalProfileController;
import main.java.interface_adapter.go_to_personal_profile.GoToPersonalProfileViewModel;
import main.java.interface_adapter.refresh_chat_page.RefreshChatPageState;
import main.java.interface_adapter.send_message.SendMessageState;
import main.java.view.HomeBar;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChatListView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "chatList";
    private final GoToChatListViewModel goToChatListViewModel1;
    private final GoToChatListController goToChatListController1;
    private final GoToChannelViewModel goToChannelViewModel1;
    private final GoToChannelController goToChannelController1;
    private final GoToPersonalProfileViewModel goToPersonalProfileViewModel1;
    private final GoToPersonalProfileController goToPersonalProfileController1;
    private final JPanel homeBar;
    private JPanel chatListPanel;
    public ChatListView(GoToChatListViewModel goToChatListViewModel, GoToChatListController goToChatListController,
                        GoToChannelViewModel goToChannelViewModel, GoToChannelController goToChannelController,
                        GoToPersonalProfileViewModel goToPersonalProfileViewModel, GoToPersonalProfileController goToPersonalProfileController
    ){
        System.out.println("ChatListView is trying to be created");
        this.goToChatListViewModel1 = goToChatListViewModel;
        this.goToChatListController1 = goToChatListController;
        this.goToChannelViewModel1 = goToChannelViewModel;
        this.goToChannelController1 = goToChannelController;
        this.goToPersonalProfileViewModel1 = goToPersonalProfileViewModel;
        this.goToPersonalProfileController1 = goToPersonalProfileController;

        goToChatListViewModel1.addPropertyChangeListener(this);
        goToChatListViewModel1.firePropertyChanged();
        goToPersonalProfileViewModel1.addPropertyChangeListener(this);
        goToPersonalProfileViewModel1.firePropertyChanged();
        goToChannelViewModel1.addPropertyChangeListener(this);
        goToChannelViewModel1.firePropertyChanged();

        JLabel title = new JLabel("Chat List");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        homeBar = new HomeBar(goToPersonalProfileViewModel1, goToPersonalProfileController1, goToChatListViewModel1,
                goToChatListController1);







        JPanel channelListPanel = new JPanel();
        channelListPanel.setLayout(new BoxLayout(channelListPanel, BoxLayout.Y_AXIS));
        ArrayList<ChatChannel> channels = goToChatListViewModel1.getChatChannels();
        System.out.println("channels size: " + channels.size());

        for (int i = 0; i < channels.size(); i++) {
            JPanel innerPanel = new ChannelPanel(channels.get(i), goToChatListViewModel1.getCurrentUser(), goToChannelViewModel1, goToChannelController1);
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

//    public static void main(String[] args) {
//        JFrame frame = new JFrame();
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(400, 600);
//
//        User testUser1 = new User("test1", "test1", "test1");
//        User testUser2 = new User("test1", "test1", "test1");
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
//        RefreshChatPageState refreshTestState = new RefreshChatPageState(testUser1, channel);
//
//        ArrayList<ChatChannel> channels = new ArrayList<>();
//        channels.add(channel);
//        GoToChatListState goToChatListState = new GoToChatListState(testUser1);
//
//        frame.add(new ChatListView(new GoToChatListViewModel(testUser1, channels, goToChatListState), new GoToChatListController(), new GoToPersonalProfileViewModel(), new GoToPersonalProfileController()));
//        frame.setVisible(true);
//    }
}
