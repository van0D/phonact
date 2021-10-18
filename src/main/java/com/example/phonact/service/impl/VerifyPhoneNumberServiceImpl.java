package com.example.phonact.service.impl;

import com.example.phonact.pojo.ContactPojo;
import com.example.phonact.service.VerifyPhoneNumberService;
import com.example.phonact.utility.ContactUtil;
import org.springframework.stereotype.Service;

@Service
public class VerifyPhoneNumberServiceImpl implements VerifyPhoneNumberService {

    @Override
    public ContactPojo verifyPhoneNumber(String number) {
        return ContactUtil.corrector.apply(new ContactPojo(number));
    }

}
