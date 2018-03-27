package com.coupon.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Hee
 *
 */
@Entity
@Table(name = "COUPON_BOOK")
public class CouponEntity {

	@Column(name = "ID")
	private Long id;
	
	@Id
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "COUPON_CODE")
	private String couponCode;
	
	@Column(name = "ISSUE_DTTM")
	private String issueDttm;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCouponCode() {
		return couponCode;
	}
	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}
	public String getIssueDttm() {
		return issueDttm;
	}
	public void setIssueDttm(String issueDttm) {
		this.issueDttm = issueDttm;
	}
	
	@Override
	public String toString() {
		return "CouponEntity [id=" + id + ", email=" + email + ", couponCode=" + couponCode + ", issueDttm=" + issueDttm
				+ "]";
	}
}
