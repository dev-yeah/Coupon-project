package com.coupon.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coupon.dto.CouponEntity;
import com.coupon.dto.CouponResponse;
import com.coupon.dto.EmailResponse;
import com.coupon.dto.PageResponse;
import com.coupon.service.CouponService;

@RestController
@RequestMapping("/coupon")
public class CouponController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private CouponService couponService;

	@GetMapping(value = "/{email}")
	@Description("Check email to be issued")
	public ResponseEntity<EmailResponse> checkValidEmail(@PathVariable String email) {

		logger.debug("[INPUT] email : {}", email);
		
		EmailResponse emailResponse = null;
		
		try {
			emailResponse = couponService.checkEmailValid(email);
		} catch (IllegalArgumentException e) {
			logger.error("Failed to verify presence. email: {} error message : {}", e.getMessage(), email);
			return new ResponseEntity<EmailResponse>(emailResponse, HttpStatus.BAD_REQUEST);
		} catch (Throwable th) {
			logger.error("throwable message : {}", th.getMessage());
			return new ResponseEntity<EmailResponse>(emailResponse, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<EmailResponse>(emailResponse, HttpStatus.OK);
	}

	@GetMapping(value = "/")
	@Description("Get total coupon list by paging")
	public ResponseEntity<PageResponse> getCouponsByPaging(@RequestParam int pageNum, @RequestParam int pageCount) {

		logger.debug("[INPUT] pageNum : {} / pageCount : {}", pageNum, pageCount);
		
		PageResponse pageResponse = couponService.getCouponsByPaging(pageNum, pageCount);

		if(pageResponse.getTotalRow() == 0) {
			logger.info("There are no record.");
			return new ResponseEntity<PageResponse>(pageResponse, HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<PageResponse>(pageResponse, HttpStatus.OK);
	}

	@PostMapping(value = "/")
	@Description("Issue a coupon")
	public ResponseEntity<CouponResponse> issueCoupon(@RequestBody CouponEntity couponEntity) {

		logger.debug("[INPUT] couponEntity : {}", couponEntity);
		
		CouponResponse couponResponse = null;
		
		try {
			couponResponse = couponService.issueCoupon(couponEntity.getEmail());
		} catch (DuplicateKeyException e) {
			logger.error("Duplicate coupon values could not be entered. error message : {}", e.getMessage());
			return new ResponseEntity<CouponResponse>(couponResponse, HttpStatus.CONFLICT);
		} catch (Throwable th) {
			logger.error("throwable message : {}", th.getMessage(), th);
			return new ResponseEntity<CouponResponse>(couponResponse, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<CouponResponse>(couponResponse, HttpStatus.OK);
	}
}
