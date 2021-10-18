package com.example.phonact.pojo;

import java.util.Objects;

public class ContactPojo {

    private Long id;
    private String phoneNumber;
    private Boolean corrected;
    private String originalPhoneNumber;
    private Boolean usable;

    public ContactPojo() {

    }

    public ContactPojo(Long id, String phoneNumber) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.corrected = false;
        this.originalPhoneNumber = null;
        this.usable = true;
    }

    public ContactPojo(String phoneNumber) {
        this.id = null;
        this.phoneNumber = phoneNumber;
        this.corrected = false;
        this.originalPhoneNumber = null;
        this.usable = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean getCorrected() {
        return corrected;
    }

    public void setCorrected(Boolean corrected) {
        this.corrected = corrected;
    }

    public String getOriginalPhoneNumber() {
        return originalPhoneNumber;
    }

    public void setOriginalPhoneNumber(String originalPhoneNumber) {
        this.originalPhoneNumber = originalPhoneNumber;
    }

    public Boolean getUsable() {
        return usable;
    }

    public void setUsable(Boolean usable) {
        this.usable = usable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactPojo that = (ContactPojo) o;
        return Objects.equals(id, that.id) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(corrected, that.corrected) && Objects.equals(originalPhoneNumber, that.originalPhoneNumber) && Objects.equals(usable, that.usable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, phoneNumber, corrected, originalPhoneNumber, usable);
    }

    @Override
    public String toString() {
        return "ContactPojo{" +
                "id=" + id +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", corrected=" + corrected +
                ", originalPhoneNumber='" + originalPhoneNumber + '\'' +
                ", usable=" + usable +
                '}';
    }

}
