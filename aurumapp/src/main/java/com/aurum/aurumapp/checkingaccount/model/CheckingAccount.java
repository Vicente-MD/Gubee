package com.aurum.aurumapp.checkingaccount.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.aurum.aurumapp.broker.model.Broker;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class CheckingAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    private double yieldRate;

    private String initialDate;

    private double initialValue;

    @ManyToOne
    private Broker broker;
}
