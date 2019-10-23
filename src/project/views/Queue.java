package project.views;

import project.App;
import project.ProcStach;

import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.sound.midi.SysexMessage;
import javax.swing.*;

public class Queue {

    public enum QueueType { MM1, MMS, MM1K};

    public JPanel mainJPanel;
    private JTextField lambdaJTextField;
    private JTextField muJTextField;
    private JTextField rhoJTextField;
    private JTextField nbServerJTextField;
    private JTextField nbMaxQueueJTextField;
    private JPanel mm1JPanel;
    private JLabel lambdaJLabel;
    private JLabel muJLabel;
    private JLabel nbServerJLabel;
    private JLabel nbMaxQueueJLabel;
    private JLabel q0JLabel;
    private JLabel qiJLabel;
    private JLabel lJLabel;
    private JLabel lqJLabel;
    private JLabel wJLabel;
    private JLabel wqJLabel;
    private JTextField qiJTextField;
    private JTextField wqJTextField;
    private JTextField lqJTextField;
    private JComboBox typeComboBox;
    private JLabel errorMessage;
    private JLabel rhoJLabel;
    private JPanel mm1kJPanel;
    private JPanel mmsJPanel;

    private ProcStach procStach;

    public Queue() {
        procStach = new ProcStach();

        setContent(getType());

        lambdaJTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                try {
                    procStach.setLambda(Double.valueOf(lambdaJTextField.getText()));
                } catch (NumberFormatException ee) {
                    System.out.println(ee);
                }
                setResults();
            }
        });
        muJTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                try {
                    procStach.setMu(Double.valueOf(muJTextField.getText()));
                } catch (NumberFormatException ee) {
                    System.out.println(ee);
                }
                setResults();
            }
        });
        nbServerJTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                try {
                    procStach.setNbServer(Double.valueOf(nbServerJTextField.getText()));
                } catch (NumberFormatException ee) {
                    System.out.println(ee);
                }
                setResults();
            }
        });
        nbMaxQueueJTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                try {
                    procStach.setMaxQueue(Double.valueOf(nbMaxQueueJTextField.getText()));
                } catch (NumberFormatException ee) {
                    System.out.println(ee);
                }
                setResults();
            }
        });
        qiJTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                try {
                    procStach.setQ_i(Double.valueOf(qiJTextField.getText()));
                } catch (NumberFormatException ee) {
                    System.out.println(ee);
                }
                setResults();
            }
        });
        typeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setContent(getType());
            }
        });
    }

    private QueueType getType() {
        switch ((String) typeComboBox.getSelectedItem()) {
            case "MM1":
                return QueueType.MM1;
            case "MMS":
                return QueueType.MMS;
            case "MM1K":
                return QueueType.MM1K;
        }
        return QueueType.MM1;
    }

    private void setContent(QueueType type)
    {
        procStach.setType(type);

        switch(type){
            case MM1:
                mm1JPanel.setVisible(true);
                nbServerJLabel.setVisible(false);
                nbServerJTextField.setVisible(false);
                nbMaxQueueJLabel.setVisible(false);
                nbMaxQueueJTextField.setVisible(false);
                wJLabel.setVisible(true);
                wqJLabel.setVisible(true);
                break;
            case MMS:
                mm1JPanel.setVisible(true);
                nbServerJLabel.setVisible(true);
                nbServerJTextField.setVisible(true);
                nbMaxQueueJLabel.setVisible(false);
                nbMaxQueueJTextField.setVisible(false);
                wJLabel.setVisible(true);
                wqJLabel.setVisible(true);
                break;
            case MM1K:
                mm1JPanel.setVisible(true);
                nbServerJLabel.setVisible(false);
                nbServerJTextField.setVisible(false);
                nbMaxQueueJLabel.setVisible(true);
                nbMaxQueueJTextField.setVisible(true);
                wJLabel.setVisible(false);
                wqJLabel.setVisible(false);
                break;
        }

        Initialization();

        App.refreshJFrame();
    }

    private void Initialization()
    {
        try {
            procStach.setLambda(Double.valueOf(lambdaJTextField.getText()));
        } catch (NumberFormatException e) {
            System.out.println(e);
        } ;

        try {
            procStach.setMu(Double.valueOf(muJTextField.getText()));
        } catch (NumberFormatException e) {
            System.out.println(e);
        } ;

        try {
            procStach.setNbServer(Double.valueOf(nbServerJTextField.getText()));
        } catch (NumberFormatException e) {
            System.out.println(e);
        } ;

        try {
            procStach.setMaxQueue(Double.valueOf(nbMaxQueueJTextField.getText()));
        } catch (NumberFormatException e) {
            System.out.println(e);
        } ;

        try {
            procStach.setQ_i(Double.valueOf(qiJTextField.getText()));
        } catch (NumberFormatException e) {
            System.out.println(e);
        } ;

        setResults();
    }

    private void setResults() {
        checkRho();

        try {
            if(procStach.rho() != -1 && !(procStach.rho() != procStach.rho()))
                rhoJLabel.setText("ρ : " + String.valueOf(procStach.rho()));
            else
                rhoJLabel.setText("ρ : Error");
        } catch (Error e) {
            rhoJLabel.setText("ρ : Error");
        }
        try {
            if(procStach.q0() != -1 && !(procStach.q0() != procStach.q0()))
                q0JLabel.setText("q_(0) : " + String.valueOf(procStach.q0()));
            else
                q0JLabel.setText("q_(0) : Error");
        } catch (Error e) {
            q0JLabel.setText("q_(0) : Error");
        }
        try {
            if(procStach.qi() != -1 && !(procStach.qi() != procStach.qi()))
                qiJLabel.setText("q_(" + procStach.getQ_i() + ") : " + String.valueOf(procStach.qi()));
            else
                qiJLabel.setText("q_(\" + procStach.getQ_i() + \") : Error");
        } catch (Error e) {
            qiJLabel.setText("q_(\" + procStach.getQ_i() + \") : Error");
        }
        try {
            if(procStach.L() != -1 && !(procStach.L() != procStach.L()))
                lJLabel.setText("L : " + String.valueOf(procStach.L()));
            else
                lJLabel.setText("L : Error");
        } catch (Error e) {
            lJLabel.setText("L : Error");
        }
        try {
            if(procStach.Lq() != -1 && !(procStach.Lq() != procStach.Lq()))
                lqJLabel.setText("L_q : " + String.valueOf(procStach.Lq()));
            else
                lqJLabel.setText("L_q : Error");
        } catch (Error e) {
            lqJLabel.setText("L_q : Error");
        }
        try {
            if(procStach.W() != -1 && !(procStach.W() != procStach.W()))
                wJLabel.setText("W : " + String.valueOf(procStach.W()));
            else
                wJLabel.setText("W : Error");
        } catch (Error e) {
            wJLabel.setText("W : Error");
        }
        try {
            if(procStach.Wq() != -1 && !(procStach.Wq() != procStach.Wq()))
                wqJLabel.setText("L_q : " + String.valueOf(procStach.Wq()));
            else
                wqJLabel.setText("L_q : Error");
        } catch (Error e) {
            wqJLabel.setText("L_q : Error");
        }

        App.refreshJFrame();
    }

    private void checkRho() {
        if(procStach.rho() >= 1 && getType() == QueueType.MM1)
            errorMessage.setText(" /!\\ Rho doit être strictement inférieur à 1 /!\\ ");
        else
            errorMessage.setText(" ");
    }
}
