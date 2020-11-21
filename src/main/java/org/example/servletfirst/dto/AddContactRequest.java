package org.example.servletfirst.dto;

import lombok.Data;

@Data
public class AddContactRequest {
    private String name;
    private String phone;
}
