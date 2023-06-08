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
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

// this class provides useful methods for integer input, for storing data to or reading 
// data from a file and for displaying options in a nice way. 
public class CoreUtils {
    private static Scanner input = new Scanner(System.in);

    // this method reads lines from a file and returns the lines as a String array
    public static String[] readFile(File file, int numberOfLines) {
        String[] lines = new String[numberOfLines];

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            for (int i = 0; i < numberOfLines; i++) {
                lines[i] = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    // this method stores strings as lines to a specified file
    public static void writeToFile(File file, String... strings) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (String string : strings) {
                bw.write(string + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // this method can be used as a safe way to get an int value from the user
    public static int saveUserInput(int min, int max) {
        int number = 0;
        boolean inputAccepted = false;
        while(!inputAccepted) {
            System.out.print("Input: ");
            try {
                String userInput = input.nextLine();
                number = Integer.parseInt(userInput);
                if (number > max || number < min) {
                    System.out.println("Please enter a valid argument.");
                } else {
                    inputAccepted = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid argument.");
            }
        }
        return number;
    }

    // this class takes a string array and prints its elements out with numbers
    public static void displayOptions(String[] list) {
        for (int i = 0; i < list.length; i++) {
            System.out.println("[" + (i + 1) + "] " + list[i]);
        }
    }

}
