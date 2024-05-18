package com.robotdreams.discountservice.service;

import com.robotdreams.discountservice.dto.ProductCouponRequestDto;
import com.robotdreams.discountservice.dto.ProductCouponResponseDto;
import com.robotdreams.discountservice.entity.ProductCoupon;
import com.robotdreams.discountservice.repository.ProductCouponRepository;
import com.robotdreams.discountservice.service.mapper.ProductCouponMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductCouponService {

    private final ProductCouponRepository productCouponRepository;
    private final ProductCouponMapper productCouponMapper;
    private final SequenceGeneratorService sequenceGenerator;

    public Long save(ProductCouponRequestDto productCouponRequestDto) {

        ProductCoupon productCoupon = productCouponMapper.productCouponRequestDtoToProductCoupon(productCouponRequestDto);
        productCoupon.setId(sequenceGenerator.generateSequence(ProductCoupon.SEQUENCE_NAME));
        return productCouponRepository.save(productCoupon).getId();
    }

    public boolean deleteByID(long productCouponId) {

        if (!productCouponRepository.existsById(productCouponId))
            return false;

        productCouponRepository.deleteById(productCouponId);
        return true;
    }

    public Optional<ProductCouponResponseDto> update(long productCouponId, ProductCouponRequestDto productCouponRequestDto) {

        var productCouponToUpdate = productCouponRepository.findByIdAndExpirationDateAfter(productCouponId, new Date());

        if (productCouponToUpdate.isPresent()) {
            ProductCoupon productCoupon = productCouponToUpdate.get();
            productCouponMapper.updateProductCoupon(productCoupon, productCouponRequestDto);
            productCouponRepository.save(productCoupon);
            return Optional.of(productCouponMapper.productCouponToProductCouponResponseDto(productCoupon));
        }
        return Optional.empty();
    }

    public Optional<ProductCouponResponseDto> findById(long productCouponId) {

        Optional<ProductCoupon> productCoupon = productCouponRepository.findByIdAndExpirationDateAfter(productCouponId, new Date());
        return productCoupon.map(productCouponMapper::productCouponToProductCouponResponseDto);
    }

    public List<ProductCouponResponseDto> findAll() {

        return productCouponRepository.findAllByExpirationDateAfter(new Date()).stream()
                .map(productCouponMapper::productCouponToProductCouponResponseDto).toList();
    }

}
