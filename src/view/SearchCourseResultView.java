package view;

import interface_adapter.add_course_to_profile.AddCourseToProfileController;
import interface_adapter.go_to_chat_list.GoToChatListController;
import interface_adapter.go_to_chat_list.GoToChatListViewModel;
import interface_adapter.go_to_personal_profile.GoToPersonalProfileController;
import interface_adapter.go_to_personal_profile.GoToPersonalProfileViewModel;
import interface_adapter.go_to_public_profile.GoToPublicProfileController;
import interface_adapter.go_to_search.GoToSearchController;
import interface_adapter.search_course_result.SearchCourseResultState;
import interface_adapter.search_course_result.SearchCourseResultViewModel;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchCourseResultView extends JPanel implements PropertyChangeListener {
    private JPanel mainPanel;
    private JPanel homeBar;
    private JPanel upPanel;
    private JScrollPane usersPane;
    private JButton backToSearch;
    private JLabel courseCode;
    private JButton addCourseToProfile;
    private JLabel resultLabel;
    private JList listUsers;
    private JPanel midTopPanel;
    private JPanel scrollPanel;
    private JButton selectButton;
    private final SearchCourseResultViewModel searchCourseResultViewModel;
    public String viewName;

    public SearchCourseResultView(SearchCourseResultViewModel searchCourseResultViewModel,
                                  GoToSearchController goToSearchController,
                                  GoToChatListController goToChatListController,
                                  GoToChatListViewModel goToChatListViewModel,
                                  GoToPersonalProfileController goToPersonalProfileController,
                                  GoToPersonalProfileViewModel goToPersonalProfileViewModel,
                                  AddCourseToProfileController addCourseToProfileController,
                                  GoToPublicProfileController goToPublicProfileController
                                  ){
        homeBar = new HomeBar(goToPersonalProfileViewModel, goToPersonalProfileController, goToChatListViewModel,
                goToChatListController, goToSearchController);
        this.setLayout(new BorderLayout());
        this.add(homeBar, BorderLayout.SOUTH);
        this.add(mainPanel, BorderLayout.PAGE_START);
        this.searchCourseResultViewModel = searchCourseResultViewModel;
        this.searchCourseResultViewModel.addPropertyChangeListener(this);
        SearchCourseResultState s = searchCourseResultViewModel.getState();
        this.viewName = searchCourseResultViewModel.getViewName();

        ListCellRenderer renderer = new UserListCellRenderer();
        listUsers.setCellRenderer(renderer);

        this.add(mainPanel);
        courseCode.setText(searchCourseResultViewModel.getState().getCourseCode());
        backToSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goToSearchController.execute();
            }
        });

        addCourseToProfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchCourseResultState s = searchCourseResultViewModel.getState();
                String courseCode = s.getCourseCode();
                if (s.getSearchForTutor()){
                    addCourseToProfileController.execute(false, courseCode);
                } else {
                    addCourseToProfileController.execute(true, courseCode);
                }

            }
        });

        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserInfo a = (UserInfo) listUsers.getSelectedValue();
                goToPublicProfileController.execute(a.id);
            }
        });

    }

    public void updateJListUsers(List<String> sortedIds, Map<String, Map<String, String>> resultUsers, Map<String, List<String>> resultUserTags){
        DefaultListModel<UserInfo> model = new DefaultListModel<UserInfo>();
        for (String id: sortedIds){
            UserInfo userInfo = new UserInfo(resultUsers.get(id), resultUserTags.get(id), id);
            model.addElement(userInfo);
        }
        listUsers.setModel(model);
    }

    private class UserListCellRenderer implements ListCellRenderer {
        JPanel main = new JPanel();
        JPanel right = new JPanel();
        JPanel left = new JPanel();
        JLabel picture = new JLabel();
        ImageIcon pictureIcon;

        UserListCellRenderer(){
            System.out.println("New !");
        }
        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            UserInfo userInfo = (UserInfo) value;
            main = new JPanel(new GridLayout(1, 2));
            main.setPreferredSize(new Dimension(330, 100));
            main.setMinimumSize(new Dimension(-1, -1));
            main.setBorder(new LineBorder(Color.BLACK, 3, true));
            left = new JPanel();
            left.setPreferredSize(new Dimension(100, main.getPreferredSize().height));
            left.setMaximumSize(new Dimension(100, main.getPreferredSize().height));
            pictureIcon = new ImageIcon(userInfo.profileURL);
            Image image = pictureIcon.getImage();
            int scaledSize = (int) Math.floor(0.7* left.getPreferredSize().width);
            Image scaledImage = image.getScaledInstance( scaledSize, scaledSize, java.awt.Image.SCALE_SMOOTH);
            picture.setIcon(new ImageIcon(scaledImage));
            left.add(picture);
            main.add(left);

            right = new JPanel();
            right.setLayout(new BoxLayout(right, BoxLayout.PAGE_AXIS));

            JLabel name = new JLabel(userInfo.name);
            name.setFont(new Font("Arial", Font.PLAIN, 20));
            name.setAlignmentX(Component.LEFT_ALIGNMENT);
            name.setBackground(Color.LIGHT_GRAY);
            right.add(name);
            right.add(new JLabel(userInfo.rating));

            JPanel tagPanel = new JPanel();
            tagPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
            for (String tag: userInfo.tags){
                JLabel t = new JLabel(tag);
                t.setFont(new Font("Arial", Font.PLAIN, 15));
                t.setOpaque(true);
                t.setBackground(Color.lightGray);
                t.setBorder(new LineBorder(Color.DARK_GRAY, 2, true));
                t.setAlignmentX(Component.LEFT_ALIGNMENT);
                tagPanel.add(t);
            }
            tagPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
            right.add(tagPanel);
            main.add(right);
            if (isSelected){
                left.setBackground(Color.PINK);
            }
            return main;
        }
    }

    public static void main(String[] args) {
        JFrame f = new FrameModel("SearchResultView");
        SearchCourseResultViewModel viewModel = new SearchCourseResultViewModel();
        SearchCourseResultState state = viewModel.getState();

        Map<String, Map<String, String>> map = new HashMap<>();
        Map<String, List<String>> tags = new HashMap<>();
        for (int i = 1; i<= 10; i++){
            Map<String, String> inner_map = new HashMap<>();
            inner_map.put("name", "Name"+i);
            inner_map.put("description", "BRO, Iam Name"+i);
            map.put("userID"+i, inner_map);
            List<String> tag = new ArrayList<>();
            tag.add("nerd");
            tag.add("single");
            tags.put("userID"+i, tag);
        }
        state.setResultUsers(map);
        state.setResultUserTags(tags);
        viewModel.setState(state);
       // SearchCourseResultView vieww = new SearchCourseResultView(viewModel);
        //f.add(vieww);
        f.setVisible(true);
    }

    public void propertyChange(PropertyChangeEvent evt) {
        SearchCourseResultState state = (SearchCourseResultState) evt.getNewValue();
        if (state.getError() != null){
            JOptionPane.showMessageDialog(this, state.getError());
        } else {
            updateJListUsers(state.getSortedIds(), state.getResultUsers(), state.getResultUserTags());
            if (state.getSearchForTutor()){
                addCourseToProfile.setText("Add this Course To Learn");
                resultLabel.setText(state.getNumbersResults() + " Tutors for\n" + state.getCourseCode());
            }
            else {
                addCourseToProfile.setText("Add this Course To Tutor");
                resultLabel.setText(state.getNumbersResults() + " Students for\n" + state.getCourseCode());
            }
            if (state.getCourseAddedToProfile()){JOptionPane.showMessageDialog(this, "Course Added Successfully");}
        }
    }

    private class UserInfo{
        String name;
        String rating;
        String profileURL = "src/view/other_icons/default_profilePhoto.png";  // default profile picture
        String id;
        List<String> tags;

        UserInfo(Map<String, String> infos, List<String> tags, String id){
            this.name = infos.get("name");
            this.id = id;
            this.rating = infos.get("rating");
            if (infos.get("profileURL") != null){
                this.profileURL = infos.get("profileURL");
            }
            this.tags = tags;
        }
    }
}
