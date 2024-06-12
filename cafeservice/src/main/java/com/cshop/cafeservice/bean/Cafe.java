package com.cshop.cafeservice.bean;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_cafe")
public class Cafe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer cafeID;

	@Column(name = "OwnerName")
	private String ownerName;

	@Column(name = "CafeName")
	private String cafeName;

	@Column(name = "GSTNo")
	private String gstNO;

	@Column(name = "PhoneNo")
	private String phoneNO;

	@Column(name = "EmailID")
	private String emailID;

	@Column(name = "UserName")
	private String username;

	@Column(name = "UserPass")
	private String userPass;

	@Column(name = "AadharNo")
	private String aadharNo;

	@NotFound(action = NotFoundAction.IGNORE)
	@OneToOne(targetEntity = CafeAddress.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "ID", referencedColumnName = "addressID")
	private CafeAddress cafeAddress;

	public Integer getCafeID() {
		return cafeID;
	}

	public void setCafeID(Integer cafeID) {
		this.cafeID = cafeID;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getCafeName() {
		return cafeName;
	}

	public void setName(String cafeName) {
		this.cafeName = cafeName;
	}

	public String getGstNO() {
		return gstNO;
	}

	public void setGstNO(String gstNO) {
		this.gstNO = gstNO;
	}

	public String getPhoneNO() {
		return phoneNO;
	}

	public void setPhoneNO(String phoneNO) {
		this.phoneNO = phoneNO;
	}

	public String getEmailID() {
		return emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	public String getAadharNo() {
		return aadharNo;
	}

	public void setAadharNo(String aadharNo) {
		this.aadharNo = aadharNo;
	}

	public CafeAddress getCafeAddress() {
		return cafeAddress;
	}

	public void setCafeAddress(CafeAddress cafeAddress) {
		this.cafeAddress = cafeAddress;
	}

}
