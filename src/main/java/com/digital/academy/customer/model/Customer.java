package com.digital.academy.customer.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 1, max = 100, message = "Please type your firstname")
    private String firstName;

    @NotNull
    @Size(min = 1, max = 100, message = "Please type your firstname")
    private String lastName;

    @Email(message = "Please type your email")
    private String email;

    @NotNull
    private  String phoneNo;

    private Integer age;


}
