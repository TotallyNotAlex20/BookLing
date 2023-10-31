package nalex.BookLing;

import nl.siegmann.epublib.domain.*;
import nl.siegmann.epublib.epub.EpubReader;

import java.io.*;
import java.util.List;

import nl.siegmann.epublib.domain.TOCReference;
public class Main {


    public static void main(String[] args) throws IOException {

        LaF.setLaF();

        GUIHandler guiHandler = new GUIHandler();

        File bookFile = guiHandler.chooseFile();
        InputStream targetStream = new FileInputStream(bookFile);

        EpubReader epubReader = new EpubReader();
        Book book = epubReader.readEpub(targetStream);


        logTableOfContents(book.getTableOfContents().getTocReferences(), 0);

        StringBuilder book_string = new StringBuilder();
        int counter = 0;
        for (Resource content : book.getContents()){
            counter += 1;

            if (counter == 6) {
                book_string.append(new String(content.getData()));
                System.out.println(new String(content.getData()));
            }
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
                System.out.println(tocString);
            }
        }
}