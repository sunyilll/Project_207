package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.go_to_channel.GoToChannelController;
import interface_adapter.go_to_channel.GoToChannelViewModel;
import interface_adapter.go_to_chat_list.GoToChatListController;
import interface_adapter.go_to_chat_list.GoToChatListState;
import interface_adapter.go_to_chat_list.GoToChatListViewModel;
import interface_adapter.go_to_personal_profile.GoToPersonalProfileController;
import interface_adapter.go_to_personal_profile.GoToPersonalProfileState;
import interface_adapter.go_to_personal_profile.GoToPersonalProfileViewModel;
import interface_adapter.go_to_public_profile.GoToPublicProfileController;
import interface_adapter.go_to_public_profile.GoToPublicProfileState;
import interface_adapter.go_to_public_profile.GoToPublicProfileViewModel;
import interface_adapter.go_to_search.GoToSearchController;
import entity.User;
import entity.ChatChannel;
import use_case.GetUserDataAccessInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class PublicProfileView extends ProfileView implements ActionListener, PropertyChangeListener {
    public static final String viewName = "public profile";
    private final GoToPublicProfileViewModel goToPublicProfileViewModel;
    private final GoToChannelController goToChannelController;
    private final GoToChannelViewModel goToChannelViewModel;
    private final ViewManagerModel viewManagerModel;
    private final JButton message;
    private final JButton back;
    private final GetUserDataAccessInterface userDAO;

    public PublicProfileView(GoToPublicProfileViewModel goToPublicProfileViewModel,
                             GoToChannelViewModel goToChannelViewModel,
                             GoToChannelController goToChannelController,
                             ViewManagerModel viewManagerModel,
                             GetUserDataAccessInterface getUserDataAccessInterface) {
        super(goToPublicProfileViewModel);
        this.userDAO = getUserDataAccessInterface;
        this.goToPublicProfileViewModel = goToPublicProfileViewModel;
        this.goToChannelViewModel = goToChannelViewModel;
        this.goToChannelController = goToChannelController;
        this.viewManagerModel = viewManagerModel;
        goToPublicProfileViewModel.addPropertyChangeListener(this);
        goToChannelViewModel.addPropertyChangeListener(this);

        JPanel backButtonPanel = new JPanel();
        back = new JButton(GoToPublicProfileViewModel.BACK_BUTTON_LABEL);
        backButtonPanel.add(back);
        backButtonPanel.setLayout(new GridLayout(1, 1));

        JPanel buttons = new JPanel();
        message = new JButton(GoToPublicProfileViewModel.MESSAGE_BUTTON_LABEL);
        buttons.add(message);
        buttons.setLayout(new GridLayout(1, 1));
        mainPanel.add(buttons);

        this.setLayout(new BorderLayout());
        this.add(mainPanel, BorderLayout.CENTER);
        this.add(backButtonPanel, BorderLayout.NORTH);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(back)){
                    viewManagerModel.setActiveView("search course result");
                    viewManagerModel.firePropertyChanged();
                    System.out.println("back button pressed");
                    }
                }
            }
        );
        message.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(message)){
                    // TODO: Implement me
                    GoToPublicProfileState s = goToPublicProfileViewModel.getState();
                    System.out.println(s.getUserid());
                    User me = userDAO.getCurrentUser();
                    Map<String, User> testMap = new HashMap<>();
                    testMap.put(me.getUserID(), me);
                    testMap.put(s.getUserid(), userDAO.get(s.getUserid()));
                    LocalDateTime currentDateTime = LocalDateTime.now();
                    ChatChannel channel = new ChatChannel(testMap, currentDateTime, "DNE");
                    goToChannelController.execute(me, channel);
                    }
                }
            }
        );
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("public profile")) {
            GoToPublicProfileState state = (GoToPublicProfileState) evt.getNewValue();

            goToPublicProfileViewModel.setState(state);
            updateMainPanel();
        }
    }
}
