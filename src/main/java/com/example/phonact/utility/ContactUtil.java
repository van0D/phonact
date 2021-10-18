package com.example.phonact.utility;

import com.example.phonact.entity.Contact;
import com.example.phonact.pojo.ContactPojo;

import java.util.function.Function;

public class ContactUtil {

    private final static String PATTERN_WITH_PREFIX = "^27[0-9]{9}$";
    private final static String PATTERN_WITHOUT_PREFIX = "^\\d{9}$";
    private final static String PATTERN_WRONG_PREFIX = "\\b((?!27)[0-9]{11})\\b";

    public final static Function<ContactPojo, ContactPojo> corrector = c -> {
        if (!c.getPhoneNumber().matches(PATTERN_WITH_PREFIX)) {
            if (c.getPhoneNumber().matches(PATTERN_WITHOUT_PREFIX)) {
                c.setOriginalPhoneNumber(c.getPhoneNumber());
                c.setPhoneNumber("27" + c.getOriginalPhoneNumber());
                c.setCorrected(true);
                c.setUsable(true);
            } else if (c.getPhoneNumber().matches(PATTERN_WRONG_PREFIX)) {
                c.setOriginalPhoneNumber(c.getPhoneNumber());
                c.setPhoneNumber("27" + c.getPhoneNumber().substring(2));
                c.setCorrected(true);
                c.setUsable(true);
            } else {
                c.setUsable(false);
            }
        }
        return c;
    };

    public final static Function<Contact, ContactPojo> correctorContact = c ->
            corrector.apply(ContactUtil.convertToPojo.apply(c));

    public final static Function<ContactPojo, Contact> convertFromPojo = p -> {
        Contact c = new Contact();
        c.setId(p.getId());
        c.setPhoneNumber(Long.valueOf(p.getPhoneNumber()));
        c.setCorrected(p.getCorrected());
        c.setOriginalPhoneNumber(p.getOriginalPhoneNumber() != null ? Long.valueOf(p.getOriginalPhoneNumber()) : null);
        return c;
    };

    public final static Function<Contact, ContactPojo> convertToPojo = c -> {
        ContactPojo p = new ContactPojo();
        p.setId(c.getId());
        p.setPhoneNumber(String.valueOf(c.getPhoneNumber()));
        p.setCorrected(c.getCorrected());
        p.setOriginalPhoneNumber(c.getOriginalPhoneNumber() != null ? String.valueOf(c.getOriginalPhoneNumber()) : null);
        p.setUsable(true);
        return p;
    };

}
