package com.example.phonact.utility;

import com.example.phonact.entity.Contact;
import com.example.phonact.pojo.ContactPojo;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class UtilityTest {

    @Test
    @DisplayName("test fro CsvUtil")
    void csvInTest() throws IOException {

        StringBuilder data = new StringBuilder()
                .append("id,sms_phone\n")
                .append("2,84528784843\n")
                .append("3,26371679142\n")
                .append("4,27736529279\n");

        MockMultipartFile mockMultipartFile = new MockMultipartFile("test.csv", "test.csv",
                "text/csv", data.toString().getBytes());

        List<ContactPojo> correctContacts = CsvUtil.extractContactsFromCsv(mockMultipartFile.getInputStream())
                .stream().map(ContactUtil.corrector).collect(Collectors.toList());

        assertTrue(CsvUtil.isCsv(mockMultipartFile), "CsvUtil.isCsv test.");

        final String getCorrectedMsg = "ContactUtil.corrector: 'getCorrected' test.";
        final String getOriginalPhoneNumberMsg = "ContactUtil.corrector: 'getOriginalPhoneNumber' test.";
        final String getPhoneNumberMsg = "ContactUtil.corrector: 'getPhoneNumber' test.";

        assertTrue(correctContacts.get(0).getCorrected(), getCorrectedMsg);
        assertTrue("84528784843".equalsIgnoreCase(correctContacts.get(0).getOriginalPhoneNumber()), getOriginalPhoneNumberMsg);
        assertTrue("27528784843".equalsIgnoreCase(correctContacts.get(0).getPhoneNumber()), getPhoneNumberMsg);

        assertTrue(correctContacts.get(1).getCorrected(), getCorrectedMsg);
        assertTrue("26371679142".equalsIgnoreCase(correctContacts.get(1).getOriginalPhoneNumber()), getOriginalPhoneNumberMsg);
        assertTrue("27371679142".equalsIgnoreCase(correctContacts.get(1).getPhoneNumber()), getPhoneNumberMsg);

        assertFalse(correctContacts.get(2).getCorrected(), getCorrectedMsg);
        assertNull(correctContacts.get(2).getOriginalPhoneNumber(), getOriginalPhoneNumberMsg);
        assertTrue("27736529279".equalsIgnoreCase(correctContacts.get(2).getPhoneNumber()), getPhoneNumberMsg);

    }

    @Test
    void csvOutTest() throws IOException {
        final List<Contact> valueToCheck = new ArrayList<>();
        final StringBuilder valueForCheck = new StringBuilder()
                .append("id,phoneNumber,corrected,originalPhoneNumber\n")
                .append("1,12345678900,true,12987654321\n")
                .append("2,27123456789,false,\n");

        valueToCheck.add(new Contact(1L,12345678900L,true,12987654321L));
        valueToCheck.add(new Contact(2L,27123456789L,false,null));

        ByteArrayInputStream byteArrayInputStream = CsvUtil.extractCsvFromContacts(valueToCheck);

        Path testFile1 = Files.createTempFile("testFile1", ".csv");
        Path testFile2 = Files.createTempFile("testFile2", ".csv");

        Files.writeString(testFile1, valueForCheck.toString());
        Files.writeString(testFile2, IOUtils.toString(byteArrayInputStream, StandardCharsets.UTF_8));

        Reader reader1 = new BufferedReader(new FileReader(testFile1.toFile()));
        Reader reader2 = new BufferedReader(new FileReader(testFile2.toFile()));

        assertTrue(IOUtils.contentEqualsIgnoreEOL(reader1, reader2), "Test for CsvUtil.extractCsvFromContacts");

    }

}
