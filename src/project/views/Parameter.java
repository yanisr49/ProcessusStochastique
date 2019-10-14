package project.views;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import project.App;

public class Parameter {
    private JButton mm1Button;
    public JPanel jPanel;
    private JButton mmkButton;
    private JButton mm1kButton;

    public Parameter() {
        mm1Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                App.goToQueue(App.QueueType.MM1);
            }
        });
    }
}
