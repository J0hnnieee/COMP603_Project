/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_2_603;
/**
 *
 * @author johnn
 */
import java.util.Scanner;
import java.io.File;
import java.io.*;

// this class represents the main program 
public class LanguageTest { 
    private static Scanner input = new Scanner(System.in);

    // Level of the user
    private int level;
    // Index of the language the user already knows
    private int nativeLanguage;
    // Index of the language the user wants to learn
    private int studyLanguage;
    // The user's progress is stored in this file after each level test.
    File userProgressFile = new File(".userProgress.txt");
    // This file stores the language index numbers.
    File languageConfigFile = new File(".languageConfig.txt");
    LanguageData languageData = new LanguageData(languageConfigFile, userProgressFile);
    private String guiOutput = "";
    
    public String getGuiOutput() {
        return this.guiOutput;
    }
    
    // creates a new instance of the class
    public LanguageTest() {
        // Check if the files exist, and if they do, load user progress and language configuration
        if (userProgressFile.exists() && languageConfigFile.exists()) {
            languageData.loadConfig();
            languageData.loadProgress();
        // If the files do not exist, ask the user what they want to learn and set the level to 0.
        } else {
            level = 0;
        }
    }

    // this is the main program of the class. Here the user can select a prompt to 
    // choose a language, show words, start the test, and exit the program.
  public void run() {
    boolean languagesChosen = false;
    boolean wordsDisplayed = false;
    Scanner scanner = new Scanner(System.in);

    while (true) {
        System.out.println("\nSelect a prompt:");

        if (!languagesChosen) {
            System.out.println("[1] Choose a Language");
        }

        if (languagesChosen && !wordsDisplayed) {
            System.out.println("[1] Show Words");
        } else if (languagesChosen && wordsDisplayed) {
            System.out.println("[1] Start Test");
            System.out.println("[2] Show Words");
        }

        if (!languagesChosen && !wordsDisplayed) {
            System.out.println("\nType \"exit\" to exit the Program");
        } else {
            System.out.println("\nType \"exit\" to exit the Program");
        }

        String input = scanner.nextLine();

        if (input.equalsIgnoreCase("exit")) {
            System.out.println("\nExited Program...");
            break;
        }

        int decision = 0;

        try {
            decision = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter a valid option.");
            continue;
        }

        if (decision == 1) {
            if (languagesChosen && !wordsDisplayed) {
                displayWordsToLearn();
                wordsDisplayed = true;
            } else if (languagesChosen && wordsDisplayed) {
                startTest();
            } else {
                askForConfig();
                languagesChosen = true;
            }
        } else if (decision == 2) {
            if (!languagesChosen && !wordsDisplayed) {
                System.out.println("Please choose your known language and the language to learn first.");
            } else if (languagesChosen && !wordsDisplayed) {
                displayWordsToLearn();
            } else if (languagesChosen && wordsDisplayed) {
                startTest();
            }
        } else {
            System.out.println("Please choose your known language and the language to learn first.");
        }

        // Check again if exit was entered after the language prompts
        if (languagesChosen && !wordsDisplayed && input.equalsIgnoreCase("exit")) {
            System.out.println("\nExited Program...");
            break;
        }
    }
}

    
    // this method displays a list of words for the different levels
    public void displayWordsToLearn() {
    for (int i = 0; i < level + 1; i++) {
        System.out.println("-------------------------------------------------------");
        String levelText = String.format("\nLevel %d Words:%n", i + 1);
        System.out.println(levelText);
        System.out.printf("%-20s%s\n", "Word", "Translation");
        System.out.println("-------------------------------------------------------");
        
        switch (i) {
            case 0:
                for (String[] levelone : LevelOne.levelone[0]) {
                    String wordText = String.format("%-20s%s\n", levelone[nativeLanguage], levelone[studyLanguage]);
                    System.out.print(wordText);
                }   break;
            case 1:
                for (String[] leveltwo : LevelTwo.leveltwo[0]) {
                    String wordText = String.format("%-20s%s\n", leveltwo[nativeLanguage], leveltwo[studyLanguage]);
                    System.out.print(wordText);
                }   break;
            case 2: 
                for (String[] levelthree : LevelThree.levelthree[0]) {
                    String wordText = String.format("%-20s%s\n", levelthree[nativeLanguage], levelthree[studyLanguage]);
                    System.out.print(wordText);
                }   break;
            case 3: 
                for (String[] levelfour : LevelFour.levelfour[0]) {
                    String wordText = String.format("%-20s%s\n", levelfour[nativeLanguage], levelfour[studyLanguage]);
                    System.out.print(wordText);
                }   break;
            default:
                System.out.println("Level not defined");
                break;
        }
    }

    System.out.println("-------------------------------------------------------");
    }
   

    
    public void startTest() {
    // prints new lines at the beginning to hide the words so the user can not cheat
    for(int i = 0; i < 1000; i++){
        System.out.println("\n");
    }
    int correctAnswers = 0;
    int allowedMistakes = 3;
    int failedAttempts = 0;
    
    // user needs to get 10 words correct in level one
    if(level == 0){
        int requiredCorrectAnswers = LevelOne.levelone[0].length;
        System.out.println("You're allowed to make 3 mistakes\n");
        // Iterate over the required correct answers
        for (int i = 0; i < requiredCorrectAnswers; i++) {
            String nativeWordOne = LevelOne.levelone[level][i][nativeLanguage];
            String studyWordOne = LevelOne.levelone[level][i][studyLanguage];
            System.out.print(nativeWordOne + " - ");
            // Initialize the mistakes and wordDone variables
            int mistakes = 0;
            boolean wordDone = false;
            // Keep asking for input until the user gets the word right or makes too many mistakes
            while(!wordDone && mistakes < allowedMistakes){
                String answerOne = input.nextLine();
                // If the answer is correct, increment the correctAnswers variable and move on to the next word
                if (answerOne.equals(studyWordOne)) {
                    System.out.println("You got it right!\n");
                    correctAnswers++;
                    wordDone = true;
                } else {
                    // If the answer is wrong, increment the mistakes variable and print a message
                    mistakes++;
                    int remainingTries = allowedMistakes - mistakes;
                    // If the user has more tries left, print a message telling them how many tries are left
                    if(remainingTries > 0){
                        System.out.println("Oops, that's not quite correct! (" + remainingTries + " tries left)\n");
                        System.out.print(nativeWordOne + " - ");
                    } else {
                        System.out.println("\nSorry, the correct answer is: " + studyWordOne + "\n");
                        break;
                    }
                }
            }
            // If the user made too many mistakes, go back to the previous word
            if(!wordDone){
               System.out.println("You made too many mistakes.");
               System.out.println("Let's try the test again.\n");
               i--; 
            }
            // If the user got all the required correct answers or made too many mistakes, break out of the loop
            if(correctAnswers >= requiredCorrectAnswers || mistakes == allowedMistakes) {
                break;
            }
        }
        
    // user needs to get 10 words correct in level two
    } else if(level == 1){
        //iterate over the required correct answers
        int requiredCorrectAnswers = LevelTwo.leveltwo[0].length;
        for (int i = 0; i < requiredCorrectAnswers; i++) {
            String nativeWordTwo = LevelTwo.leveltwo[0][i][nativeLanguage];
            String studyWordTwo = LevelTwo.leveltwo[0][i][studyLanguage];
            System.out.print(nativeWordTwo + " - ");
            // Initialize the mistakes and wordDone variables
            int mistakes = 0;
            boolean wordDone = false;
            // Keep asking for input until the user gets the word right or makes too many mistakes
            while(!wordDone && mistakes < allowedMistakes){
                String answerTwo = input.nextLine();
                // If the answer is correct, increment the correctAnswers variable and move on to the next word
                if (answerTwo.equals(studyWordTwo)) {
                    System.out.println("You got it right!\n");
                    correctAnswers++;
                    wordDone = true;
                } else {
                    // If the answer is wrong, increment the mistakes variable and print a message
                    mistakes++;
                    failedAttempts++;
                    int remainingTries = allowedMistakes - mistakes;
                    // If the user has more tries left, print a message telling them how many tries are left
                    if(remainingTries > 0){
                        System.out.println("Oops, that's not quite correct! (" + remainingTries + " tries left)\n");
                        System.out.print(nativeWordTwo + " - ");
                    } else {
                        System.out.println("Sorry, the correct answer is: " + studyWordTwo + "\n");
                        break;
                    }
                }
            }
            // If the user made too many mistakes, go back to the previous word
            if(!wordDone){
               i--; 
            }
            // If the user got all the required correct answers or made too many mistakes, break out of the loop
            if(correctAnswers >= requiredCorrectAnswers || mistakes == allowedMistakes) {
                break;
            }
        }
    // user needs to get 10 words correct in level three
    } else if(level == 2){
        int requiredCorrectAnswers = LevelThree.levelthree[0].length;
        //iterate over the required correct answers
        for (int i = 0; i < requiredCorrectAnswers; i++) {
            String nativeWordThree = LevelThree.levelthree[0][i][nativeLanguage];
            String studyWordThree = LevelThree.levelthree[0][i][studyLanguage];
            System.out.print(nativeWordThree + " - ");
            // Initialize the mistakes and wordDone variables
            int mistakes = 0;
            boolean wordDone = false;
            // Keep asking for input until the user gets the word right or makes too many mistakes
            while(!wordDone && mistakes < allowedMistakes){
                String answerThree = input.nextLine();
                // If the answer is correct, increment the correctAnswers variable and move on to the next word
                if (answerThree.equals(studyWordThree)) {
                    System.out.println("You got it right!\n");
                    correctAnswers++;
                    wordDone = true;
                } else {
                    // If the answer is wrong, increment the mistakes variable and print a message
                    mistakes++;
                    failedAttempts++;
                    int remainingTries = allowedMistakes - mistakes;
                    // If the user has more tries left, print a message telling them how many tries are left
                    if(remainingTries > 0){
                        System.out.println("Oops, that's not quite correct! (" + remainingTries + " tries left)\n");
                        System.out.print(nativeWordThree + " - ");
                    } else {
                        System.out.println("\nSorry, the correct answer is: " + studyWordThree + "\n");
                        break;
                    }
                }
            }
            // If the user made too many mistakes, go back to the previous word
            if(!wordDone){
               i--; 
            }
            // If the user got all the required correct answers or made too many mistakes, break out of the loop
            if(correctAnswers >= requiredCorrectAnswers || mistakes == allowedMistakes) {
                break;
            }
        }
    // user needs to get 10 words correct in level four
    } else if(level == 3){
        int requiredCorrectAnswers = LevelFour.levelfour[0].length;
        //iterate over the required correct answers
        for (int i = 0; i < requiredCorrectAnswers; i++) {
            String nativeWordFour = LevelFour.levelfour[0][i][nativeLanguage];
            String studyWordFour = LevelFour.levelfour[0][i][studyLanguage];
            System.out.print(nativeWordFour + " - ");
            // Initialize the mistakes and wordDone variables
            int mistakes = 0;
            boolean wordDone = false;
            // Keep asking for input until the user gets the word right or makes too many mistakes
            while(!wordDone && mistakes < allowedMistakes){
                String answerFour = input.nextLine();
                // If the answer is correct, increment the correctAnswers variable and move on to the next word
                if (answerFour.equals(studyWordFour)) {
                    System.out.println("You got it right!\n");
                    correctAnswers++;
                    wordDone = true;
                } else {
                    // If the answer is wrong, increment the mistakes variable and print a message
                    mistakes++;
                    failedAttempts++;
                    int remainingTries = allowedMistakes - mistakes;
                    // If the user has more tries left, print a message telling them how many tries are left
                    if(remainingTries > 0){
                        System.out.println("Oops, that's not quite correct! (" + remainingTries + " tries left)\n");
                        System.out.print(nativeWordFour + " - ");
                    } else {
                        System.out.println("Sorry, the correct answer is: " + studyWordFour + "\n");
                        break;
                    }
                }
            }
            // If the user made too many mistakes, go back to the previous word
            if(!wordDone){
               i--; 
            }
            // If the user got all the required correct answers or made too many mistakes, break out of the loop
            if(correctAnswers >= requiredCorrectAnswers || mistakes == allowedMistakes) {
                break;
            }
        }
    }
    // If the user has answered all the questions correctly in level one, two or three
    if (correctAnswers == LevelOne.levelone[0].length || correctAnswers == LevelTwo.leveltwo[0].length || correctAnswers == LevelThree.levelthree[0].length){
        level++;
        System.out.println("-------------------------------------------\n");
        System.out.println("Congratulations! You have completed level " + level + "!\n");
        // If the user has completed level four and answered all questions correctly, print completion message and exit program
        if(level == 4 && correctAnswers == LevelFour.levelfour[0].length){
            System.out.println("You have also completed all the tests!\n");
            System.out.println("Thanks for learning!\n");
            System.out.println("Exiting Program...\n");
            System.exit(0);
        }
        
        System.out.println("-------------------------------------------");
        // If the user has failed the attempted words 3 times
    } else if (failedAttempts == 3) {
        System.out.println("--------------------------------------------------------------------\n");
        System.out.println("Unfortunately, you have made too many mistakes to continue the test.\n");
        // If the user has completed at least level 1, decrement the level and print message indicating the user needs to redo the previous test
        if (level >= 1) {
            level--;
            System.out.println("You need to redo a previous test because you made too many mistakes.\n");
            System.out.println("--------------------------------------------------------------------");
        }
    }
    // Save progress
    languageData.saveProgress();
    System.out.println();
    }

    // this functions asks the user which language he can speak and which language
    // he wants to learn
    public void askForConfig() {
        int numLanguages = Languages.languages.length;

    // prompt user to choose known language
        System.out.println("\nI can already speak: ");
        CoreUtils.displayOptions(Languages.languages);
        nativeLanguage = CoreUtils.saveUserInput(1, numLanguages) - 1;

    // prompt user to choose language to learn
        boolean validInput = false;
        while (!validInput) {
            System.out.println("\nI want to learn: ");
            CoreUtils.displayOptions(Languages.languages);
            studyLanguage = CoreUtils.saveUserInput(1, numLanguages) - 1;
            if (studyLanguage == nativeLanguage) {
                System.out.println("\nYou can't choose the same language!");
            } else {
                validInput = true;
            }
        }
    // save config
    languageData.saveConfig();
    } 
}