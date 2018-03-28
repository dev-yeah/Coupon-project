package com.coupon.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.coupon.dto.CouponEntity;
import com.coupon.dto.CouponResponse;
import com.coupon.dto.EmailResponse;
import com.coupon.dto.PageResponse;
import com.coupon.repository.CouponRepository;

@Service
public class CouponService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private CouponRepository couponRepository;

	private int id = 0;
	private char[] validChars;

	public PageResponse getCouponsByPaging(int pageNum, int pageCount) {

		PageResponse out = new PageResponse();

		PageRequest pageRequest = PageRequest.of(pageNum - 1, pageCount, new Sort(Direction.DESC, "ISSUE_DTTM"));
		Page<CouponEntity> result = couponRepository.getCouponsByPaging(pageRequest);
		
		List<CouponEntity> list = result.getContent();
		long totalRow = result.getTotalElements();
		int totalPage = result.getTotalPages();
		
		out.setList(list);
		out.setTotalRow(totalRow);
		out.setTotalPage(totalPage);
		out.setPageNum(pageNum);
		out.setPageCount(pageCount);

		return out;
	}

	
	public EmailResponse checkEmailValid(String email) throws Exception {

		EmailResponse response = new EmailResponse();
		
		boolean isValid = false;
		
		try {
			isValid = (couponRepository.existsById(email)) ? false : true;
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Email value may be null or emty.", e);
		}
		
		response.setEmail(email);
		response.setValid(isValid);
		
		return response;
	}

	@Transactional
	public CouponResponse issueCoupon(String email) throws Exception {

		CouponResponse response = new CouponResponse();
		CouponEntity couponEntity = new CouponEntity();
		
		String coupon, isExist;

		// 한번 더 체크해주는 건, 똑같은 email에 대해 validation check를 한 후, 발급할 때 텀이 존재. 먼저 추가한 사용자는
		// 등록되고 이후에 추가한 사용자는 등록이 안됐다고 alert를 줘야 할 듯
		boolean isValid = (couponRepository.existsById(email)) ? false : true;
		if(!isValid) {
			throw new DuplicateKeyException("Duplicate email address.");
		}
		
		do {
			coupon = makeUniqueCoupon();
			isExist = couponRepository.getCouponByCode(coupon);
		} while (isExist != null && !isExist.isEmpty());
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

		couponEntity.setId(id++);
		couponEntity.setEmail(email);
		couponEntity.setCouponCode(coupon);
		couponEntity.setIssueDttm(formatter.format(new Date()));
		try {
			couponRepository.save(couponEntity);
		} catch (Exception e) {
			throw new Exception("An error occurred while handling data.", e);
		}


		logger.info("Coupon Information :: {}", couponEntity.toString());
		
		response.setCouponCode(coupon);
		return response;
	}

	private String makeUniqueCoupon() {

		if (validChars == null) 
			makeValidChars();

		int couponLength = 16;
		String couponCode;
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < couponLength; i++) {

			Random random = new Random();
			int index = random.nextInt(validChars.length);

			sb.append(validChars[index]);
			if ((i + 1) % 4 == 0 && (i + 1) != couponLength) {
				sb.append('-');
			}
		}

		couponCode = sb.toString();
		
		return couponCode;
	}

	private void makeValidChars() {

		IntStream alphabet = IntStream.concat(IntStream.rangeClosed('a', 'z'), IntStream.rangeClosed('A', 'Z'));
		IntStream number = IntStream.rangeClosed('0', '9');

		validChars = IntStream.concat(alphabet, number).mapToObj(c -> "" + (char) c).collect(Collectors.joining()).toCharArray();
	}
}
