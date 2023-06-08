/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_2_603;

/**
 *
 * @author johnn
 */
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.awt.Dimension;
import java.awt.Component;

public class LanguageTests extends JFrame {
    private String languageSpeak;
    private String languageLearn;
    private int currentIndex;
    private String[][] testData;
    private JLabel wordLabel;
    private JTextField answerField;

    public LanguageTests(String languageSpeak, String languageLearn) {
        this.languageSpeak = languageSpeak;
        this.languageLearn = languageLearn;
        int speakIndex = Languages.getLanguageIndex(languageSpeak);
        int learnIndex = Languages.getLanguageIndex(languageLearn);

        testData = new String[LevelOne.levelone[0].length][2];
        for (int i = 0; i < LevelOne.levelone[0].length; i++) {
            testData[i][0] = LevelOne.levelone[0][i][speakIndex];
            testData[i][1] = LevelOne.levelone[0][i][learnIndex];
        }

        // Shuffle the data to randomize the test
        Collections.shuffle(Arrays.asList(testData));

        setTitle("Language Test");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(300, 200));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        wordLabel = new JLabel("Word: " + testData[currentIndex][0]);
        wordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(wordLabel);

        answerField = new JTextField();
        answerField.setMaximumSize(new Dimension(200, 30));
        mainPanel.add(answerField);

        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(new NextAction());
        mainPanel.add(nextButton);

        add(mainPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private class NextAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String userAnswer = answerField.getText();
            if (userAnswer.equalsIgnoreCase(testData[currentIndex][1])) {
                JOptionPane.showMessageDialog(null, "Correct!", "Result", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Incorrect! The correct answer is " + testData[currentIndex][1], "Result", JOptionPane.INFORMATION_MESSAGE);
            }

            currentIndex++;
            if (currentIndex < testData.length) {
                wordLabel.setText("Word: " + testData[currentIndex][0]);
                answerField.setText("");
            } else {
                dispose();
            }
        }
    }
}
