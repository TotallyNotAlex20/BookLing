package nalex.BookLing;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.epub.EpubParser;
import org.apache.tika.sax.ToHTMLContentHandler;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) throws IOException, TikaException, SAXException {

        LaF.setLaF();

        GUIHandler guiHandler = new GUIHandler();
        File inputFile = guiHandler.chooseFile();
        String inputPath = inputFile.getPath();
        InputStream targetStream = new FileInputStream(inputFile);

        // System.out.println("input stream get: \"" + targetStream + "\"");

        try {
//            System.out.println(extractContent(targetStream));
            String bookString = extractContent(targetStream).toString();
            guiHandler.textField.setText(bookString);
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            targetStream.close();
        }

    }

    public static ToHTMLContentHandler extractContent(InputStream stream) throws IOException, TikaException, SAXException {
        EpubParser parser = new EpubParser();
        Metadata metadata = new Metadata();
        ToHTMLContentHandler handler = new ToHTMLContentHandler();
        ParseContext parseContext = new ParseContext();
        parser.parse(stream, handler, metadata, parseContext);
        return handler;
    }


}