package com.robotdreams.discountservice.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Getter
@Setter
@Document
public class CartCoupon extends Coupon implements Serializable {

    @Transient
    public static final String SEQUENCE_NAME = "cart_coupon_sequence";

}