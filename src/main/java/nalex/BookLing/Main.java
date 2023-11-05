package nalex.BookLing;

import java.io.*;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.epub.EpubParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

public class Main {
    public static void main(String[] args) throws IOException, TikaException, SAXException {

        LaF.setLaF();

        GUIHandler guiHandler = new GUIHandler();

        InputStream targetStream = new FileInputStream(guiHandler.chooseFile());
        System.out.println("input stream get: \"" + targetStream + "\"");

        try {
            // Parse the EPUB file
            System.out.println(extractContentUsingFacade(targetStream));
            System.out.println("kekw probably?");
        } catch (Exception e) {
            System.err.println("weeewooooo" + e);
        } finally {
            targetStream.close();
        }

    }
    public static BodyContentHandler extractContentUsingFacade(InputStream stream) throws IOException, TikaException, SAXException {
        EpubParser parser = new EpubParser();
        Metadata metadata = new Metadata();
        BodyContentHandler handler = new BodyContentHandler(-1);
        ParseContext parseContext = new ParseContext();
        parser.parse(stream, handler, metadata, parseContext);
        return handler;
    }
}