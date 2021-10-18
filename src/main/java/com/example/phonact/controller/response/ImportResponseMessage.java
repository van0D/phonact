package com.example.phonact.controller.response;

import com.example.phonact.pojo.ContactPojo;

import java.util.List;

public class ImportResponseMessage {

    private String message;
    private List<ContactPojo> importedContacts;
    private List<ContactPojo> excludedContacts;

    public ImportResponseMessage(String message) {
        this.message = message;
        this.importedContacts = null;
        this.excludedContacts = null;
    }

    public ImportResponseMessage(String message, List<ContactPojo> importedContacts, List<ContactPojo> excludedContacts) {
        this.message = message;
        this.importedContacts = importedContacts;
        this.excludedContacts = excludedContacts;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ContactPojo> getImportedContactsContacts() {
        return importedContacts;
    }

    public void setImportedContactsContacts(List<ContactPojo> importedContacts) {
        this.importedContacts = importedContacts;
    }

    public List<ContactPojo> getExcludedContacts() {
        return excludedContacts;
    }

    public void setExcludedContacts(List<ContactPojo> excludedContacts) {
        this.excludedContacts = excludedContacts;
    }

}
