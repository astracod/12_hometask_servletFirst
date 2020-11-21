package org.example.servletfirst.ContactBook;

import lombok.EqualsAndHashCode;
import lombok.ToString;


@EqualsAndHashCode
@ToString
public class Contact {
    private String name;
    private String number;
    private Integer id = 0;

    public Contact(String name, String number, Integer id) {
        this.name = name;
        this.number = number;
        this.id = setId(id);
    }

    public Integer setId(Integer id) {
       return this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
