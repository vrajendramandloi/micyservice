package com.uni.micy.service.model;

import java.util.Date;

public class UserDetails {
	String uid;
	String fullname;
	String emailid;
	String mobilenum;
	String dob;

	public UserDetails() {
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public String getMobilenum() {
		return mobilenum;
	}

	public void setMobilenum(String mobilenum) {
		this.mobilenum = mobilenum;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	@Override
	public String toString() {
		return "UserDetails [uid=" + uid + ", fullname=" + fullname + ", emailid=" + emailid + ", mobilenum="
				+ mobilenum + ", dob=" + dob + "]";
	}
}
