package view;

import interface_adapter.go_to_chatl_list.GoToChatListController;
import interface_adapter.go_to_chatl_list.GoToChatListState;
import interface_adapter.go_to_chatl_list.GoToChatListViewModel;
import interface_adapter.go_to_personal_profile.GoToPersonalProfileController;
import interface_adapter.go_to_personal_profile.GoToPersonalProfileState;
import interface_adapter.go_to_personal_profile.GoToPersonalProfileViewModel;
import interface_adapter.go_to_search.GoToSearchController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

// All Views that use the home bar should extend this class, and add relevant JPanel to mainPanel.
// The home bar will be added to the bottom of the view.

// TODO: Add "GoToSearchCourse" use case related objects

public class HomeBar extends JPanel implements PropertyChangeListener, ActionListener {
    public String viewName = "HomeBarView";
    private final GoToChatListViewModel goToChatListViewModel1;
    private final GoToChatListController goToChatListController1;
    private final GoToPersonalProfileViewModel goToPersonalProfileViewModel;
    private final GoToPersonalProfileController goToPersonalProfileController;

    public HomeBar(GoToPersonalProfileViewModel goToPersonalProfileViewModel,
                   GoToPersonalProfileController personalProfileController,
                   GoToChatListViewModel goToChatListViewModel,
                   GoToChatListController goToChatListController,
                   GoToSearchController goToSearchController) {

        this.goToChatListViewModel1 = goToChatListViewModel;
        this.goToChatListViewModel1.addPropertyChangeListener(this);
        this.goToChatListController1 = goToChatListController;
        this.goToPersonalProfileViewModel = goToPersonalProfileViewModel;
        this.goToPersonalProfileViewModel.addPropertyChangeListener(this);
        this.goToPersonalProfileController = personalProfileController;

        JButton messageButton = new JButton();
        messageButton.setIcon(new ImageIcon("src/view/home_bar_icons/message.png"));
        this.add(messageButton);
        JButton searchButton = new JButton();
        searchButton.setIcon(new ImageIcon("src/view/home_bar_icons/search.png"));
        this.add(searchButton);
        JButton profileButton = new JButton();
        profileButton.setIcon(new ImageIcon("src/view/home_bar_icons/profile.png"));
        this.add(profileButton);

        this.setLayout(new GridLayout(1, 3));
        this.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Use these code in your *view* to add the home bar to the bottom of the view.
//        this.setLayout(new BorderLayout());
//        this.add(homeBar, BorderLayout.SOUTH);

        messageButton.addActionListener(
                new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(messageButton)) {
                    GoToChatListState currState = goToChatListViewModel1.getState();

                    goToChatListController1.execute();

                    System.out.println("Chat list button pressed");
                }
            }
        });
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(searchButton)) {

                    // TODO: add GoToSearchCourse Controller
                    goToSearchController.execute();
                    System.out.println("Search button pressed");
                }
            }
        });
        profileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(profileButton)) {
                    goToPersonalProfileController.execute();
                }
            }
        });

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
