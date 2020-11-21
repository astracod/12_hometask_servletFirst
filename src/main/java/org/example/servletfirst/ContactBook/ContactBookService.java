package org.example.servletfirst.ContactBook;


import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class ContactBookService {

    private static ContactBookService contactBookService = null;
    private List<Contact> contacts = new LinkedList<>();
    private int id = 0;

    public static ContactBookService getInstance() {
        if (contactBookService == null) {
            contactBookService = new ContactBookService();
        }
        return contactBookService;
    }

    private ContactBookService() {}

    public List<Contact> addContactBook(Contact contact) {
        contact.setId(++id);
        contacts.add(contact);
        return contacts;
    }

    public List<Contact> showContactBook() {
        return contacts;
    }

    public List<Contact> removeContact(int id) {
        Contact contact1 = null;
        for (Contact contact : contacts) {
            if (id == contact.getId()) {
                contact1 = contact;
            }
        }
        if (contact1 != null) contacts.remove(contact1);
        return contacts;
    }

    public List<Contact> similarInContacts(String similar) {

        List<Contact> similarAll = new LinkedList<>();
        for (Contact contact : contacts) {
            if (contact.getName().startsWith(similar)) {
                similarAll.add(contact);
            }
        }
        return similarAll;
    }
}

