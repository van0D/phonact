package com.example.phonact.repository;

import com.example.phonact.entity.Contact;

import java.util.List;

public interface ContactCustomRepository {

    List<Contact> getContacts(Boolean corrected);

}
