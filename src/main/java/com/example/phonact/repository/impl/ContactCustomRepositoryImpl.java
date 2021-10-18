package com.example.phonact.repository.impl;

import com.example.phonact.entity.Contact;
import com.example.phonact.repository.ContactCustomRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ContactCustomRepositoryImpl implements ContactCustomRepository {

    @Autowired
    EntityManager em;

    public List<Contact> getContacts(Boolean corrected) {
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<Contact> cq = cb.createQuery(Contact.class);
        final Root<Contact> root =cq.from(Contact.class);
        List<Predicate> predicates = new ArrayList<>();

        if (corrected != null) {
            predicates.add(cb.equal(root.get("corrected"), corrected));
        }

        cq.where(predicates.toArray(new Predicate[0]));

        return em.createQuery(cq).getResultList();

    }

}
