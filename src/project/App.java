package project;

import project.views.*;

import javax.swing.*;

public class App {

    private static JFrame frame = new JFrame("test");

    public static void main(String[] args) {

        frame.setContentPane(new Parameter().jpanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static void goToQueue() {
        frame.setContentPane(new Queue().jpanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
