package main.java.view;

import main.java.interface_adapter.search.SearchViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import main.java.interface_adapter.search.*;

public class SearchView extends JPanel{
    private JPanel mainPanel;
    private JPanel homePanel;
    private JButton searchButton;
    private JButton profileButton;
    private JButton messageButton;
    private JPanel upPanel;
    private JLabel searchLabel;
    private JFormattedTextField searchField;
    private JPanel midPanel;
    private JButton isStudentButton;
    private JButton searchbutton;
    private JButton isTutorButton;
    SearchViewModel searchViewModel;

    SearchView(SearchViewModel searchViewModel){
        this.searchViewModel = searchViewModel;
        this.add(mainPanel);

        isTutorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchState s = searchViewModel.getState();
                s.setIsStudent(false);
                searchViewModel.setState(s);
                isStudentButton.disable();
            }
        });
        isStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchState s = searchViewModel.getState();
                s.setIsStudent(true);
                searchViewModel.setState(s);
                isTutorButton.disable();
            }
        });

    }

    public static void main(String[] args) {
        JFrame f = new FrameModel("SearchTest");
        f.add(new SearchView(new SearchViewModel(new SearchState())));
        f.setVisible(true);
    }
}
