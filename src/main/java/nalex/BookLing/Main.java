package nalex.BookLing;

import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Metadata;
import nl.siegmann.epublib.epub.EpubReader;

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

        System.out.println(metadata.getAuthors().toString());
    }
}