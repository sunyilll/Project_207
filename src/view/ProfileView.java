package view;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

abstract class ProfileView extends JPanel implements ActionListener, PropertyChangeListener {
    public String viewName;

    JLabel label = new JLabel("Enter username:");

    public ProfileView() {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(label);
    }
}
