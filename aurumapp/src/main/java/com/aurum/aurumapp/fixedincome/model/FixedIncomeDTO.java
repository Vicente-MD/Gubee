package com.aurum.aurumapp.fixedincome.model;

import com.aurum.aurumapp.user.model.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FixedIncomeDTO {
    private FixedIncomeModel fixedIncome;
    private User user;
}
