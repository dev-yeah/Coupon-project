package com.coupon;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.coupon.dto.CouponEntity;
import com.coupon.dto.EmailResponse;
import com.coupon.repository.CouponRepository;
import com.coupon.service.CouponService;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(properties = "sever.port=9999")
public class ServiceTestcase {

	@Autowired
	private CouponRepository repository;
	
	@Autowired
	private CouponService couponService;

	
	@Test
	@Description("Service unit")
	public void validCheckTest() throws Exception {

		insertCoupon("testcase@test.com", "aaaa-aaaa-AAAA-AAAA");
		
		EmailResponse res; 
		res = couponService.checkEmailValid("testcase@test.com");
		assertFalse(res.isValid());
		
		res = couponService.checkEmailValid("testcase002@test.com");
		assertTrue(res.isValid());
	}
	
	@Test(expected=DuplicateKeyException.class)
	@Description("Service unit")
	public void duplicateCouponTest() throws Exception {
		couponService.issueCoupon("testcase@test.com");
		couponService.issueCoupon("testcase@test.com"); 
	}
	
	private void insertCoupon(String email, String coupon) {
		CouponEntity origin = new CouponEntity();
		origin.setId(1000);
		origin.setEmail(email);
		origin.setCouponCode(coupon);
		origin.setIssueDttm(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		
		repository.save(origin); //insert
	}
}
