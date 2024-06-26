package com.robotdreams.discountservice.service.mapper;

import com.robotdreams.discountservice.dto.CartCouponRequestDto;
import com.robotdreams.discountservice.dto.CartCouponResponseDto;
import com.robotdreams.discountservice.dto.ProductCouponRequestDto;
import com.robotdreams.discountservice.dto.ProductCouponResponseDto;
import com.robotdreams.discountservice.entity.Coupon;
import com.robotdreams.discountservice.entity.ProductCoupon;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ProductCouponMapper {

    ProductCoupon productCouponRequestDtoToProductCoupon(ProductCouponRequestDto productCouponRequestDto);

    ProductCouponResponseDto productCouponToProductCouponResponseDto(ProductCoupon productCoupon);

    ProductCoupon updateProductCoupon(@MappingTarget ProductCoupon productCoupon, ProductCouponRequestDto dto);

}