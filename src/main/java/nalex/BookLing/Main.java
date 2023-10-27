package nalex.BookLing;

import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Metadata;
import nl.siegmann.epublib.epub.EpubReader;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) throws IOException {
        File initialFile = new File("src/main/resources/test123.epub");
        InputStream targetStream = new FileInputStream(initialFile);

        EpubReader epubReader = new EpubReader();
        Book testBook = epubReader.readEpub(targetStream);
        Metadata metadata = testBook.getMetadata();

        JFrame f=new JFrame();//creating instance of JFrame

        JTextField testField = new JTextField("kekw");

        testField.setBounds(130,100,100, 40);
        f.add(testField);

        f.setSize(400,500);//400 width and 500 height
        f.setLayout(null);//using no layout managers
        f.setVisible(true);//making the frame visible

        System.out.println(metadata.getAuthors().toString());
    }
}