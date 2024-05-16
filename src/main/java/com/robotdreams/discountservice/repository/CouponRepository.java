package com.robotdreams.discountservice.repository;

import com.robotdreams.discountservice.entity.Coupon;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface CouponRepository extends MongoRepository<Coupon, Long> {

    Optional<Coupon> findAllByExpirationDateAfter(Date date);

    Optional<Coupon> findByIdAndExpirationDateAfter(Long id, Date date);

}
