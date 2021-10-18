package com.example.phonact.controller;

import com.example.phonact.controller.response.ImportResponseMessage;
import com.example.phonact.controller.response.ResponseMessage;
import com.example.phonact.entity.Contact;
import com.example.phonact.service.PhoncatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("phonact-api")
public class PhonactController {

    @Autowired
    private PhoncatService phoncatService;

    @GetMapping("/contacts")
    public List<Contact> getContacts(@RequestParam(value = "corrected", required = false) Boolean corrected) {
        return phoncatService.getContacts(corrected);
    }

    @GetMapping("/contacts/{id}")
    public Optional<Contact> getContact(@PathVariable Long id) {
        return phoncatService.getContact(id);
    }

    @PostMapping("/contacts")
    public Contact saveContact(@RequestBody Contact contact) throws Exception {
        return phoncatService.saveContact(contact);
    }

    @DeleteMapping("/contacts/{id}")
    public ResponseEntity<ResponseMessage> deleteContact(@PathVariable Long id) {
        return phoncatService.deleteContact(id);
    }

    @PostMapping("/contacts/upload")
    public ResponseEntity<ImportResponseMessage> uploadCsv(@RequestParam("file") MultipartFile file) {
        return phoncatService.upload(file);
    }

    @GetMapping("/contacts/download")
    public ResponseEntity<Resource> downloadCsv() {
        return phoncatService.downloadCsv();
    }

    //ExceptionHandler
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ResponseMessage> handleException(Exception exception) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseMessage(exception.getMessage()));
    }

}
