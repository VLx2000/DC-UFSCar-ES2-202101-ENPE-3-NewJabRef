package net.sf.jabref.importer.fileformat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import net.sf.jabref.importer.ImportFormatReader;
import net.sf.jabref.importer.OutputPrinter;
import net.sf.jabref.model.entry.BibEntry;


public class CsvImporter extends ImportFormat {
    @Override
    public String getFormatName() {
        return "CSV";
    }

    @Override
    public String getExtensions() {
        return "csv";
    }

    @Override
    public boolean isRecognizedFormat(InputStream in) throws IOException {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public List<BibEntry> importEntries(InputStream stream, OutputPrinter status) throws IOException {
        if (stream == null) {
            throw new IOException("No stream given.");
        }

        List<String> entries = new LinkedList<>();
        StringBuilder sb = new StringBuilder();

        try (BufferedReader in = new BufferedReader(ImportFormatReader.getReaderDefaultEncoding(stream))) {
            // Preprocess entries
            String str;

            List<BibEntry> results = new LinkedList<>();

            while ((str = in.readLine()) != null) {
                String[] word = str.split(",");
                BibEntry b;
                System.out.println(word[0]);
                if (word[0].contains("book")) {
                    b = new BibEntry(DEFAULT_BIBTEXENTRY_ID, "book");
                } else {
                    b = new BibEntry(DEFAULT_BIBTEXENTRY_ID, "article");
                }

                System.out.println(word[1]);
                System.out.println(word[2]);
                System.out.println(word[3]);
                System.out.println(word[4]);
                System.out.println(word[5]);
                System.out.println(word[6]);
                System.out.println(word[7]);
                System.out.println(word[8]);
                System.out.println(word[9]);

                setOrAppend(b, "title", word[1], " ");
                setOrAppend(b, "author", word[2], " ");
                setOrAppend(b, "year", word[3], " ");
                setOrAppend(b, "publisher", word[4], " ");
                setOrAppend(b, "series", word[5], ", ");
                setOrAppend(b, "isbn", word[6], ", ");
                setOrAppend(b, "keywords", word[7], ", ");
                setOrAppend(b, "note", word[8], ", ");
                results.add(b);
            }
            return results;
        }
    }

    private static void setOrAppend(BibEntry b, String field, String value, String separator) {
        if (b.hasField(field)) {
            b.setField(field, b.getField(field) + separator + value);
        } else {
            b.setField(field, value);
        }
    }
}
