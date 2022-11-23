package com.aurum.aurumapp.fixedincome.model;

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
public class FixedIncomeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String paper;

    private String issuer;

    private double yieldRate;

    private String initialDate;

    private String finalDate;

    private double initialValue;

    @ManyToOne
    private Broker broker;
}
