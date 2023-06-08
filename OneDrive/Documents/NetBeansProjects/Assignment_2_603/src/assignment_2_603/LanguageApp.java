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
import java.awt.*;
import java.awt.event.*;

public class LanguageApp extends JFrame {
    private JComboBox<String> languageSpeakBox;
    private JComboBox<String> languageLearnBox;
    private JTextArea outputArea;

    public LanguageApp() {
        setTitle("Polyglot");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 300));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Welcome to Polyglot");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel selectionPanel = new JPanel();
        selectionPanel.setLayout(new FlowLayout());
        selectionPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel languageSpeakLabel = new JLabel("I can already speak:");
        languageSpeakBox = new JComboBox<>(Languages.languages);

        JLabel languageLearnLabel = new JLabel("I want to learn:");
        languageLearnBox = new JComboBox<>(Languages.languages);

        selectionPanel.add(languageSpeakLabel);
        selectionPanel.add(languageSpeakBox);
        selectionPanel.add(languageLearnLabel);
        selectionPanel.add(languageLearnBox);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton showWordsButton = new JButton("Show Words");
        JButton startTestButton = new JButton("Start Test");

        buttonPanel.add(showWordsButton);
        buttonPanel.add(startTestButton);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);
        outputArea.setAlignmentX(Component.CENTER_ALIGNMENT);

        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(selectionPanel);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(buttonPanel);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(scrollPane);
        mainPanel.add(Box.createVerticalStrut(20));

        add(mainPanel);
        pack();
        setLocationRelativeTo(null); // Center the window
        setVisible(true);

        showWordsButton.addActionListener(new ShowWordsAction());
        startTestButton.addActionListener(new StartTestAction());
    }

    private class ShowWordsAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String languageSpeak = (String) languageSpeakBox.getSelectedItem();
            String languageLearn = (String) languageLearnBox.getSelectedItem();

            int speakIndex = Languages.getLanguageIndex(languageSpeak);
            int learnIndex = Languages.getLanguageIndex(languageLearn);

            String[] columnNames = {"Word", "Translation"};
            String[][] data = new String[LevelOne.levelone[0].length][2];

            for (int i = 0; i < LevelOne.levelone[0].length; i++) {
                data[i][0] = LevelOne.levelone[0][i][speakIndex];
                data[i][1] = LevelOne.levelone[0][i][learnIndex];
            }

            JTable table = new JTable(data, columnNames);
            table.setFont(new Font("Arial", Font.PLAIN, 12));
            table.setRowHeight(20);
            table.setEnabled(false);

            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setPreferredSize(new Dimension(300, 150));

            JOptionPane.showMessageDialog(null, scrollPane, "Level 1 Words", JOptionPane.PLAIN_MESSAGE);
        }
    }

private class StartTestAction implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        String languageSpeak = (String) languageSpeakBox.getSelectedItem();
        String languageLearn = (String) languageLearnBox.getSelectedItem();
        new LanguageTests(languageSpeak, languageLearn);
    }
}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LanguageApp();
        });
    }
}