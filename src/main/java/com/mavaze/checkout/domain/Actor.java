package com.mavaze.checkout.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Actor {

    @Id
    @GeneratedValue
    @Column(name="ID")
    private long id;

    @Column(name="USERNAME", nullable = false, unique = true)
    private String userName;

    @Column(name="PASSWORD_HASH", nullable = false)
    private String passwordHash;

    @Column(name="ROLE", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

}