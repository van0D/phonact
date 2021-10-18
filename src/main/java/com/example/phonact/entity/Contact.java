package com.example.phonact.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Contact {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "sms_phone")
    private Long phoneNumber;

    @Column(name = "corrected")
    private Boolean corrected;

    @Column(name = "original_sms_phone")
    private Long originalPhoneNumber;

    public Contact() {

    }

    public Contact(Long phoneNumber) {
        this.id = null;
        this.phoneNumber = phoneNumber;
        this.corrected = false;
        this.originalPhoneNumber = null;
    }

    public Contact(Long id, Long phoneNumber) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.corrected = false;
        this.originalPhoneNumber = null;
    }

    public Contact(Long id, Long phoneNumber, Boolean corrected, Long originalPhoneNumber) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.corrected = corrected;
        this.originalPhoneNumber = originalPhoneNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean getCorrected() {
        return corrected;
    }

    public void setCorrected(Boolean corrected) {
        this.corrected = corrected;
    }

    public Long getOriginalPhoneNumber() {
        return originalPhoneNumber;
    }

    public void setOriginalPhoneNumber(Long originalPhoneNumber) {
        this.originalPhoneNumber = originalPhoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(id, contact.id) && Objects.equals(phoneNumber, contact.phoneNumber) && Objects.equals(corrected, contact.corrected) && Objects.equals(originalPhoneNumber, contact.originalPhoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, phoneNumber, corrected, originalPhoneNumber);
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", phoneNumber=" + phoneNumber +
                ", corrected=" + corrected +
                ", originalPhoneNumber=" + originalPhoneNumber +
                '}';
    }

}
