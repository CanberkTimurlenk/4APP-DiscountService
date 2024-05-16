package com.robotdreams.discountservice.service;


import com.robotdreams.discountservice.dto.CouponRequestDto;
import com.robotdreams.discountservice.dto.CouponResponseDto;
import com.robotdreams.discountservice.entity.Coupon;
import com.robotdreams.discountservice.repository.CouponRepository;
import com.robotdreams.discountservice.service.mapper.CouponMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponRepository couponRepository;
    private final CouponMapper couponMapper;
    private final SequenceGeneratorService sequenceGenerator;

    public Long save(CouponRequestDto couponRequestDto) {

        Coupon coupon = couponMapper.couponRequestDtoToCoupon(couponRequestDto);
        coupon.setId(sequenceGenerator.generateSequence(Coupon.SEQUENCE_NAME));
        return couponRepository.save(coupon).getId();
    }

    public boolean deleteByID(long couponId) {

        if (!couponRepository.existsById(couponId))
            return false;

        couponRepository.deleteById(couponId);
        return true;
    }

    public Optional<CouponResponseDto> update(long couponId, CouponRequestDto couponRequestDto) {

        var couponToUpdate = couponRepository.findByIdAndExpirationDateAfter(couponId, new Date());

        if (couponToUpdate.isPresent()) {
            Coupon coupon = couponToUpdate.get();
            couponMapper.updateCoupon(coupon, couponRequestDto);
            couponRepository.save(coupon);
            return Optional.of(couponMapper.couponToCouponResponseDto(coupon));
        }
        return Optional.empty();
    }

    public Optional<CouponResponseDto> findById(long couponId) {

        Optional<Coupon> coupon = couponRepository.findByIdAndExpirationDateAfter(couponId, new Date());
        return coupon.map(couponMapper::couponToCouponResponseDto);
    }

    public List<CouponResponseDto> findAll() {

        return couponRepository.findAllByExpirationDateAfter(new Date()).stream()
                .map(couponMapper::couponToCouponResponseDto).toList();
    }
}