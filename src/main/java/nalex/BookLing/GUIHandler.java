package nalex.BookLing;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class GUIHandler {

    public static int window_width = 500;
    public static int window_height = 600;
    public JTextPane textField = new JTextPane();
    public JScrollPane scrollField = (new JScrollPane(textField));

    JFrame windowFrame = new JFrame();

    public GUIHandler() {
        setTextArea();
        setWindow();

        textField.addHyperlinkListener(e -> {
            if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                String chapterId = e.getDescription();
                System.out.println(chapterId);
            }
        });
    }

    public File chooseFile() {
        JFileChooser selectFile = new JFileChooser();
        FileFilter filter = new FileNameExtensionFilter("Ebook files", "epub");

        selectFile.setFileFilter(filter);
        selectFile.showOpenDialog(null);

        return selectFile.getSelectedFile();
    }

    public void setWindow() {
        windowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        windowFrame.setSize(window_width, window_height);
        windowFrame.getContentPane().add(scrollField);
        windowFrame.setVisible(true);
    }

    public void setTextArea() {
        textField.setEditable(false);
        textField.setContentType("text/html");
        scrollField.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }
}
