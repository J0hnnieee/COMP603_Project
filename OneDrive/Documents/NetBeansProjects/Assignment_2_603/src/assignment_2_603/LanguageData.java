/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_2_603;
/**
 *
 * @author johnn
 */
import java.io.File;

public class LanguageData {
    private File languageConfigFile;
    private File userProgressFile;
    private int nativeLanguage;
    private int studyLanguage;
    private int level;
    
    // the following four methods save necessary data to a file or load data from the files
    
    public LanguageData(File languageConfigFile, File userProgressFile) {
        this.languageConfigFile = languageConfigFile;
        this.userProgressFile = userProgressFile;
    }
    
    public void saveConfig() {
        CoreUtils.writeToFile(languageConfigFile, String.valueOf(nativeLanguage), 
                String.valueOf(studyLanguage));
    }

    public void loadConfig() {
        String[] config = CoreUtils.readFile(languageConfigFile, 2);
        if (config[0] != null && !config[0].isEmpty()) {
            nativeLanguage = Integer.parseInt(config[0]);
        }
        if (config[1] != null && !config[1].isEmpty()) {
            studyLanguage = Integer.parseInt(config[1]);
        }
    }

    public void saveProgress() {
        CoreUtils.writeToFile(userProgressFile, String.valueOf(level));
    }

    public void loadProgress() {
        String[] progress = CoreUtils.readFile(userProgressFile, 1);
        if (progress.length > 0 && progress[0] != null && !progress[0].isEmpty()) {
            level = Integer.parseInt(progress[0]);
        } else {
        // Handle the case where the progress file is empty or does not exist
            level = 1;
        }
    }
}
