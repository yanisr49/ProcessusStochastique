package project;

import project.views.*;

import javax.swing.*;

public class App {

    public enum QueueType { MM1, MMK, MM1K};

    private static JFrame frame = new JFrame("test");

    public static void main(String[] args) {

        frame.setContentPane(new Parameter().jPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static void goToQueue(QueueType type) {
        frame.setContentPane(new Queue().jpanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
