package com.aurum.aurumapp.stock.model;

import com.aurum.aurumapp.user.model.User;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StockDTO {
    private Stock stock;
    private User user;
}
