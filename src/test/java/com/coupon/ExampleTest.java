package com.coupon;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.coupon.controller.CouponController;
import com.coupon.service.CouponService;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(properties = "sever.port=9000")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ExampleTest {

	
	private CouponService couponService;

	@Autowired
	private CouponController couponController;
	
	@Test
	public void serviceTest() {
		
	}
}
