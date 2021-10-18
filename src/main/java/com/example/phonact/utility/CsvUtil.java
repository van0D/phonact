package com.example.phonact.utility;

import com.example.phonact.entity.Contact;
import com.example.phonact.pojo.ContactPojo;
import org.apache.commons.csv.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CsvUtil {

    public static Boolean isCsv(MultipartFile file) {
        return "text/csv".equals(file.getContentType());
    }

    public static List<ContactPojo> extractContactsFromCsv(InputStream inputStream) {
        try {
            List<ContactPojo> contactPojos = new ArrayList<>();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            CSVParser csvParser = new CSVParser(bufferedReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withDelimiter(','));
            List<CSVRecord> rows = csvParser.getRecords();

            rows.stream().map(r -> new ContactPojo(Long.parseLong(r.get(0)), r.get(1))).collect(Collectors.toCollection(()-> contactPojos));

            return contactPojos;
        } catch (IOException e) {
            throw new RuntimeException("import error: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream extractCsvFromContacts(List<Contact> contacts) throws IOException{
        final List<String> headers = Arrays.asList("id","phoneNumber","corrected","originalPhoneNumber");
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        CSVFormat csvFormat = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);
        CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(output), csvFormat);

        csvPrinter.printRecord(headers);
        for (Contact contact : contacts) {
            List<String> data = Arrays.asList(
                    String.valueOf(contact.getId()),
                    String.valueOf(contact.getPhoneNumber()),
                    String.valueOf(contact.getCorrected()),
                    contact.getOriginalPhoneNumber() != null ? String.valueOf(contact.getOriginalPhoneNumber()) : null
            );
            csvPrinter.printRecord(data);
        }
        csvPrinter.flush();
        return new ByteArrayInputStream(output.toByteArray());

    }

}
