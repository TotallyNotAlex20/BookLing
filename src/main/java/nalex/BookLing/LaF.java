package nalex.BookLing;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;

public class LaF {
    public static void setLaF() {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
    }
}
