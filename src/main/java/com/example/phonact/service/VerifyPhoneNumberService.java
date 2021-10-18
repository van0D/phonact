package com.example.phonact.service;

import com.example.phonact.pojo.ContactPojo;

public interface VerifyPhoneNumberService {

    ContactPojo verifyPhoneNumber(String number);

}
