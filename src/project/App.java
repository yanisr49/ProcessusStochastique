package project;

import project.views.*;

import javax.swing.*;
import java.awt.*;

public class App {

    private static JFrame frame = new JFrame("Processus Stochastique");

    public static void main(String[] args) {

        frame.setContentPane(new Queue().mainJPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        refreshJFrame();
    }

    public static void refreshJFrame() {
        frame.pack();
        frame.setVisible(true);
    }
}
