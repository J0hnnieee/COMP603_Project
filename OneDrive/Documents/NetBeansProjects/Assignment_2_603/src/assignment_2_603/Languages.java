/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_2_603;
/**
 *
 * @author johnn
 */
public abstract class Languages {
    public static String languages[] = {"English", "Spanish", "Tagalog", "Malay"};
    
    public static int getLanguageIndex(String language) {
        for (int i = 0; i < languages.length; i++) {
            if (languages[i].equalsIgnoreCase(language)) {
                return i;
            }
        }
        return -1;  // return -1 if the language is not found
    }
}
