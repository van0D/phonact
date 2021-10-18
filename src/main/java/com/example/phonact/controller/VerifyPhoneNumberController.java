package com.example.phonact.controller;

import com.example.phonact.pojo.ContactPojo;
import com.example.phonact.service.VerifyPhoneNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("verify")
public class VerifyPhoneNumberController {

    @Autowired
    private VerifyPhoneNumberService verifyPhoneNumberService;

    @GetMapping("/{number}")
    public ContactPojo verifyPhoneNumber(@PathVariable String number) {
        return verifyPhoneNumberService.verifyPhoneNumber(number);
      }

}
