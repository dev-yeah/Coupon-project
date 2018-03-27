package com.coupon.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.coupon.dto.CouponEntity;


public interface CouponRepository extends JpaRepository<CouponEntity, String>{

	@Query(value = "SELECT * FROM COUPON_BOOK", countQuery = "SELECT COUNT(*) FROM COUPON_BOOK", nativeQuery = true)
	Page<CouponEntity> getCouponsByPaging(Pageable pageable);
	
	@Query(value = "SELECT EMAIL FROM COUPON_BOOK", nativeQuery = true)
	List<String> getAllEmail();

	@Query(value = "SELECT COUPON_CODE FROM COUPON_BOOK", nativeQuery = true)
	List<String> getAllCoupon();
	
	@Query(value = "SELECT COUPON_CODE FROM COUPON_BOOK WHERE COUPON_CODE=?", nativeQuery = true)
	String getCouponByCode(String couponCode);
}
