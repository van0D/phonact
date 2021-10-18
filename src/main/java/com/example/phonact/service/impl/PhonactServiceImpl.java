package com.example.phonact.service.impl;

import com.example.phonact.controller.response.ImportResponseMessage;
import com.example.phonact.controller.response.ResponseMessage;
import com.example.phonact.entity.Contact;
import com.example.phonact.pojo.ContactPojo;
import com.example.phonact.repository.ContactRepository;
import com.example.phonact.service.PhoncatService;
import com.example.phonact.utility.ContactUtil;
import com.example.phonact.utility.CsvUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PhonactServiceImpl implements PhoncatService {

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public List<Contact> getContacts(Boolean corrected) {
        return contactRepository.getContacts(corrected);
    }

    @Override
    public Optional<Contact> getContact(Long id) {
        return contactRepository.findById(id);
    }

    @Override
    public Contact saveContact(Contact contact) throws Exception {
        ContactPojo correctedContactPojo = ContactUtil.correctorContact.apply(contact);
        if (correctedContactPojo.getUsable()) {
            return contactRepository.save(ContactUtil.convertFromPojo.apply(correctedContactPojo));
        } else {
            throw new Exception("'phoneNumber' non valido, non è stato possibile correggere automaticamnete il valore.");
        }
    }

    @Override
    public ResponseEntity<ResponseMessage> deleteContact(Long id) {
        try {
            contactRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMessage("successfully contact deleted (Id = " + id + ")."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseMessage("delete error for item with Id = " + id + "."));
        }
    }

    @Override
    public ResponseEntity<ImportResponseMessage> upload(MultipartFile file) {
        if (CsvUtil.isCsv(file)) {
            try {
                List<ContactPojo> correctContactPojos = CsvUtil.extractContactsFromCsv(file.getInputStream())
                        .stream().map(ContactUtil.corrector).collect(Collectors.toList());
                List<ContactPojo> exludedContactPojos = correctContactPojos.stream().filter(c -> !c.getUsable()).collect(Collectors.toList());
                List<ContactPojo> usableContactPojos = correctContactPojos.stream().filter(ContactPojo::getUsable).collect(Collectors.toList());
                List<Contact> contacts = usableContactPojos.stream().map(ContactUtil.convertFromPojo).collect(Collectors.toList());
                contactRepository.saveAll(contacts);
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new ImportResponseMessage(file.getName() + " successfully imported.", usableContactPojos, exludedContactPojos));
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new ImportResponseMessage("import error for " + file.getName()));
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ImportResponseMessage("only *.csv file accepted."));
        }

    }

    @Override
    public ResponseEntity<Resource> downloadCsv() {
        final String filename = "usableContacts.csv";
        List<Contact> usableContacts = contactRepository.getContacts(null);
        try {
            InputStreamResource contactsCsvFile = new InputStreamResource(CsvUtil.extractCsvFromContacts(usableContacts));
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                    .contentType(MediaType.parseMediaType("application/csv"))
                    .body(contactsCsvFile);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }

    }

}
