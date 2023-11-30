package main.java.view;

import main.java.interface_adapter.go_to_chatl_list.GoToChatListController;
import main.java.interface_adapter.go_to_chatl_list.GoToChatListViewModel;
import main.java.interface_adapter.go_to_personal_profile.GoToPersonalProfileController;
import main.java.interface_adapter.go_to_personal_profile.GoToPersonalProfileViewModel;
import main.java.interface_adapter.go_to_search.GoToSearchController;
import main.java.interface_adapter.search_course.SearchCourseViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import main.java.interface_adapter.search_course.*;

public class SearchCourseView extends JPanel implements PropertyChangeListener {
    private JPanel mainPanel;
    private JPanel upPanel;
    private JLabel searchLabel;
    private JPanel midPanel;
    private JButton isStudentButton;
    private JButton isTutorButton;
    private JPanel searchPanel;
    private JPanel homeBar;
    private JTextField searchField;
    private JButton searchButton;
    private final SearchCourseViewModel searchCourseViewModel;
    public String viewName;

    public SearchCourseView(SearchCourseViewModel searchCourseViewModel,
                            SearchCourseController searchCourseController,
                            GoToPersonalProfileViewModel goToPersonalProfileViewModel,
                            GoToPersonalProfileController goToPersonalProfileController,
                            GoToChatListViewModel goToChatListViewModel,
                            GoToChatListController goToChatListController,
                            GoToSearchController goToSearchController){
        this.searchCourseViewModel = searchCourseViewModel;
        this.searchCourseViewModel.addPropertyChangeListener(this);

        homeBar = new HomeBar(goToPersonalProfileViewModel, goToPersonalProfileController, goToChatListViewModel,
                goToChatListController, goToSearchController);
        this.setLayout(new BorderLayout());
        this.add(homeBar, BorderLayout.SOUTH);
        this.add(mainPanel, BorderLayout.PAGE_START);
        this.viewName = searchCourseViewModel.getViewName();
        searchField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                SearchCourseState currState = searchCourseViewModel.getState();
                currState.setCourseCode(searchField.getText() + e.getKeyChar());
                searchCourseViewModel.setState(currState);
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        isTutorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchCourseState s = searchCourseViewModel.getState();
                s.setSearchForTutor(false);
                searchCourseViewModel.setState(s);
                isStudentButton.setEnabled(false);  //todo: should add a reset button?
            }
        });
        isStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchCourseState s = searchCourseViewModel.getState();
                s.setSearchForTutor(true);
                searchCourseViewModel.setState(s);
                isTutorButton.setEnabled(false);
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchCourseState s = searchCourseViewModel.getState();
                searchCourseController.execute(s.getCourseCode(), s.getSearchForTutor(), s.getUserID());
            }
        });

    }

    public static void main(String[] args) {
        JFrame f = new FrameModel("SearchTest");
        //todo: f.add(...);
        f.setVisible(true);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // todo: what to do when view model changed
        SearchCourseState state = (SearchCourseState) evt.getNewValue();
        System.out.println("CHANGE MADE");
        if (state.getError() != null){
            JOptionPane.showMessageDialog(this, state.getError());
        } else {
            searchField.setText(state.getCourseCode());
            System.out.println("Property change in Search View");
        }
    }
}
