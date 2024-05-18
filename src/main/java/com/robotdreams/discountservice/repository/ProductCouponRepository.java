package com.robotdreams.discountservice.repository;

import com.robotdreams.discountservice.entity.CartCoupon;
import com.robotdreams.discountservice.entity.ProductCoupon;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Date;
import java.util.Optional;

public interface ProductCouponRepository extends ListCrudRepository<ProductCoupon, Long> {

    Optional<ProductCoupon> findAllByExpirationDateAfter(Date date);

    Optional<ProductCoupon> findByIdAndExpirationDateAfter(Long id, Date date);
}
