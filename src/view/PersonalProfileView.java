package view;

import interface_adapter.go_to_chat_list.GoToChatListController;
import interface_adapter.go_to_chat_list.GoToChatListViewModel;
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

public class PersonalProfileView extends ProfileView implements ActionListener, PropertyChangeListener {
    public static final String viewName = "personal profile";
    private final GoToPersonalProfileViewModel goToPersonalProfileViewModel;
    private final JPanel homeBar;
    private final JButton editProfile;
    private final JButton signOut;

    public PersonalProfileView(GoToPersonalProfileViewModel goToPersonalProfileViewModel,
                               GoToPersonalProfileController personalProfileController,
                               GoToChatListViewModel goToChatListViewModel,
                               GoToChatListController goToChatListController,
                               GoToSearchController goToSearchController) {
        super(goToPersonalProfileViewModel);

        this.goToPersonalProfileViewModel = goToPersonalProfileViewModel;
        goToChatListViewModel.addPropertyChangeListener(this);
        goToPersonalProfileViewModel.addPropertyChangeListener(this);

        homeBar = new HomeBar(goToPersonalProfileViewModel, personalProfileController, goToChatListViewModel,
                goToChatListController, goToSearchController);

        JPanel buttons = new JPanel();
        editProfile = new JButton(GoToPersonalProfileViewModel.EDIT_PROFILE_BUTTON_LABEL);
        buttons.add(editProfile);
        signOut = new JButton(GoToPersonalProfileViewModel.SIGN_OUT_BUTTON_LABEL);
        buttons.add(signOut);
        buttons.setLayout(new GridLayout(1, 2));
        mainPanel.add(buttons);

        this.setLayout(new BorderLayout());
        this.add(homeBar, BorderLayout.SOUTH);
        this.add(mainPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == editProfile) {
//            goToPersonalProfileViewModel.setState(GoToPersonalProfileState.EDIT_PROFILE);
//        } else if (e.getSource() == signOut) {
//            goToPersonalProfileViewModel.setState(GoToPersonalProfileState.SIGN_OUT);
//        }

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("personal profile")) {
            GoToPersonalProfileState state = (GoToPersonalProfileState) evt.getNewValue();

            goToPersonalProfileViewModel.setState(state);
            updateMainPanel();

        }
    }
}
