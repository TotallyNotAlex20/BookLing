package nalex.BookLing;

import com.formdev.flatlaf.FlatLightLaf;
import nl.siegmann.epublib.domain.*;
import nl.siegmann.epublib.epub.EpubReader;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.util.List;

import nl.siegmann.epublib.domain.TOCReference;
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
        FileFilter filter = new FileNameExtensionFilter("Ebook files", "epub");
        j.setFileFilter(filter);
        j.showOpenDialog(null);

//        File initialFile = new File("src/main/resources/test123.epub");
        File initialFile = j.getSelectedFile();
        InputStream targetStream = new FileInputStream(initialFile);

        EpubReader epubReader = new EpubReader();
        Book testBook = epubReader.readEpub(targetStream);
        Metadata metadata = testBook.getMetadata();

        JFrame f=new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        List<String> titles = testBook.getMetadata().getTitles();

        System.out.println("book title:" + (titles.isEmpty() ? "book has no title" : titles.get(0)));

//        Spine spine = new Spine(testBook.getTableOfContents());

        //new try ig?

        logTableOfContents(testBook.getTableOfContents().getTocReferences(), 0);

        JTextPane testField = new JTextPane();
//        testField.setLineWrap(true);
        testField.setContentType("text/html");
        System.out.println(testField.isFontSet());
        StringBuilder book_string = new StringBuilder();
        int counter = 0;
        for (Resource content : testBook.getContents()){
            System.out.println(counter);
            counter += 1;

            if (counter > 5) {
                book_string.append(new String(content.getData()));
            }
        }
        testField.setText(book_string.toString());

        testField.setBounds(0,0, window_width, window_height);
        testField.setEditable(false);
        f.add(testField);

        f.setSize(window_width, window_height);
        f.setLayout(null);//using no layout managers
        f.setVisible(true);//making the frame visible

        // prints authors? idk lmao
        for (int i = 0; metadata.getAuthors().size() > i; i++){
            System.out.println(metadata.getAuthors().get(i));
        }
    }
    private static void logTableOfContents(List<TOCReference> tocReferences, int depth) {
            if (tocReferences == null) {
                return;
            }
            for (TOCReference tocReference : tocReferences) {
                StringBuilder tocString = new StringBuilder();
                for (int i = 0; i < depth; i++) {
                    tocString.append("\t");
                }
                tocString.append(tocReference.getTitle());

                logTableOfContents(tocReference.getChildren(), depth + 1);
//                System.out.println(tocString.toString());
            }
        }
}