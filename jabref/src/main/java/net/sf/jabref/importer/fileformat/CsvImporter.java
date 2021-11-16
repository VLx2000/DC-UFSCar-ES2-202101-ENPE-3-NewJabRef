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
    public List<BibEntry> importEntries(InputStream stream, OutputPrinter status) throws IOException {
        if (stream == null) {
            throw new IOException("No stream given.");
        }

        List<String> entries = new LinkedList<>();
        StringBuilder sb = new StringBuilder();

        try (BufferedReader in = new BufferedReader(ImportFormatReader.getReaderDefaultEncoding(stream))) {
            // Preprocess entries
            String str;

            while ((str = in.readLine()) != null) {
                System.out.println("oba");
                System.out.println(str);
                if (str.length() < 4) {
                    continue;
                }
                /*
                String code = str.substring(0, 4);

                if ("\n".equals(code)) {
                    sb.append('\n').append(str);
                } else {*/
                sb.append(str).append('\n');
                //}
            }
        }

        if (sb.length() > 0) {
            entries.add(sb.toString());
        }
        System.out.println(entries);

        List<BibEntry> results = new LinkedList<>();

        for (String entry : entries) {

            BibEntry b = new BibEntry(DEFAULT_BIBTEXENTRY_ID, "book");

            String[] word = entry.split(",");

            //for (String line1 : lines) {

            /*String line = line1.trim();
            String[] word = line.split(",");*/
            System.out.println(word[0]);
            System.out.println(word[1]);
            System.out.println(word[2]);
            System.out.println(word[3]);
            System.out.println(word[4]);
            System.out.println(word[5]);
            /*
                setOrAppend(b, "title", word[0], " ");
                setOrAppend(b, "author", word[1], " ");
                setOrAppend(b, "year", word[2], " ");
                setOrAppend(b, "publisher", word[3], " ");
                setOrAppend(b, "series", word[4], ", ");
                setOrAppend(b, "isbn", word[5], ", ");
                setOrAppend(b, "keywords", word[6], ", ");
                setOrAppend(b, "note", word[7], ", ");
                setOrAppend(b, "physicaldimensions", word[8].trim(), ", ");
                setOrAppend(b, "documenttype", word[9].trim(), ", ");*/

                //}
            results.add(b);
        }

        return results;
    }

    private static void setOrAppend(BibEntry b, String field, String value, String separator) {
        if (b.hasField(field)) {
            b.setField(field, b.getField(field) + separator + value);
        } else {
            b.setField(field, value);
        }
    }

    @Override
    public boolean isRecognizedFormat(InputStream in) throws IOException {
        // TODO Auto-generated method stub
        return true;
    }
}
