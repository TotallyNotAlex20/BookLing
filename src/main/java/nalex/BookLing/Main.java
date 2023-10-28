package nalex.BookLing;

import com.formdev.flatlaf.FlatLightLaf;
import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Metadata;
import nl.siegmann.epublib.epub.EpubReader;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Main {

    public static int window_width = 500;
    public static int window_height = 600;

    public static void main(String[] args) throws IOException {

        try {
            UIManager.setLookAndFeel( new FlatLightLaf() );
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
        // Using this process to invoke the constructor,
        // JFileChooser points to user's default directory
        JFileChooser j = new JFileChooser();

        // Open the save dialog
        j.showSaveDialog(null);


        File initialFile = new File("src/main/resources/test123.epub");
        InputStream targetStream = new FileInputStream(initialFile);


        EpubReader epubReader = new EpubReader();
        Book testBook = epubReader.readEpub(targetStream);
        Metadata metadata = testBook.getMetadata();

        JFrame f=new JFrame();

        System.out.println(testBook.getContents().toString());
        JTextComponent testField = new JTextArea(testBook.getContents().toString()); // get the author out of the list, so it looks nice

        testField.setBounds(0,0, window_width, window_height);
        testField.setEditable(false);
        f.add(testField);

        f.setSize(window_width, window_height);
        f.setLayout(null);//using no layout managers
        f.setVisible(true);//making the frame visible

        System.out.println(metadata.getAuthors().toString());
    }
}