package com.example.phonact.service;

import com.example.phonact.controller.response.ImportResponseMessage;
import com.example.phonact.controller.response.ResponseMessage;
import com.example.phonact.entity.Contact;
import com.example.phonact.pojo.ContactPojo;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface PhoncatService {

    List<Contact> getContacts(Boolean corrected);

    Optional<Contact> getContact(Long id);

    Contact saveContact(Contact contact) throws Exception;

    ResponseEntity<ResponseMessage> deleteContact(Long id);

    ResponseEntity<ImportResponseMessage> upload(MultipartFile file);

    ResponseEntity<Resource> downloadCsv();

}
