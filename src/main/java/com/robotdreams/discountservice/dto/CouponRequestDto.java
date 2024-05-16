package com.robotdreams.discountservice.dto;

import java.math.BigDecimal;
import java.util.Date;

public record CouponRequestDto(Long productId, String description,
                               BigDecimal amount, String couponCode, Date expirationDate) {

}
