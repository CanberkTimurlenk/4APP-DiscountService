package com.robotdreams.discountservice.controller;

import com.robotdreams.discountservice.dto.CartCouponRequestDto;
import com.robotdreams.discountservice.dto.CartCouponResponseDto;
import com.robotdreams.discountservice.service.CartCouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart-coupons")
public class CartCouponController {

    private final CartCouponService cartCouponService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<CartCouponResponseDto>> findAll() {
        return ResponseEntity.ok(cartCouponService.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CartCouponResponseDto> findById(@PathVariable long id) {

        Optional<CartCouponResponseDto> coupon = cartCouponService.findById(id);

        return coupon.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {

        return cartCouponService.deleteByID(id)
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> save(@RequestBody CartCouponRequestDto couponRequestDto)
            throws URISyntaxException {

        long id = cartCouponService.save(couponRequestDto);

        return ResponseEntity.created(new URI("/coupons" + id)).build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> update(@PathVariable long id, @RequestBody CartCouponRequestDto couponRequestDto) {

        Optional<CartCouponResponseDto> coupon =
                cartCouponService.update(id, couponRequestDto);

        return coupon.isPresent()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();

    }
}