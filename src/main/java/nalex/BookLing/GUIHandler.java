package nalex.BookLing;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class GUIHandler {

    public static int window_width = 500;
    public static int window_height = 600;
    JTextPane testField = new JTextPane();

    public GUIHandler() {
        setTextArea();
        setWindow();
    }

    public File chooseFile() {
        JFileChooser selectFile = new JFileChooser();
        FileFilter filter = new FileNameExtensionFilter("Ebook files", "epub");
        selectFile.setFileFilter(filter);
        selectFile.showOpenDialog(null);
        return selectFile.getSelectedFile();
    }

    public void setWindow(){
        JFrame windowFrame = new JFrame();
        windowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        windowFrame.add(testField);

        windowFrame.setSize(window_width, window_height);
        windowFrame.setLayout(null);//using no layout managers
        windowFrame.setVisible(true);//making the windowFrame visible
    }

    public void setTextArea(){
        testField.setBounds(0,0, window_width, window_height);
        testField.setEditable(false);
        testField.setContentType("text/html");
    }

}
