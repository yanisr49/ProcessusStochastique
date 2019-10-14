package project.views;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import project.App;

public class Parameter {
    private JButton button1;
    public JPanel jpanel;

    public Parameter() {
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                App.goToQueue();
            }
        });
    }
}
