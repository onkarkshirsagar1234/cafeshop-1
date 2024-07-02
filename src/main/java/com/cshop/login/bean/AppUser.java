package com.cshop.login.bean;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_AppUser")
public class AppUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer ID;

	@Column(name = "UserName")
	private String userName;


	@Column(name = "EmailID")
	private String emailID;

	@Column( name  =  "AddressLine1")
	private String addressline1;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmailID() {
		return emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	public Integer getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Integer getIsAllAccess() {
		return isAllAccess;
	}

	public void setIsAllAccess(Integer isAllAccess) {
		this.isAllAccess = isAllAccess;
	}

	public Cafe getCafe() {
		return cafe;
	}

	public void setCafe(Cafe cafe) {
		this.cafe = cafe;
	}

	@Column( name  =  "AddressLine2")
	private String addressline2;

	@Column( name  =  "CityID")
	private String city;

	@Column( name  =  "StateID")
	private String state;

	@Column( name  =  "CountryID")
	private String country;

	@Column( name  =  "Pincode")
	private String pincode;
	
	@Column(name = "IsAdmin")
	protected Integer isAdmin;
	
	@Column(name = "IsAllAccess")
	protected Integer isAllAccess;

	@Column(name = "RecordStatus")
	private Integer recordStatus;

	@Column(name = "Password")
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "PwdExpDate")
	private Date pwdExpDate;
	
	@OneToOne(cascade = CascadeType.ALL ,mappedBy = "")
	@JoinColumn(name = "CafeID")
	private Cafe cafe;
	
	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}


	public String getAddressline1() {
		return addressline1;
	}

	public void setAddressline1(String addressline1) {
		this.addressline1 = addressline1;
	}

	public String getAddressline2() {
		return addressline2;
	}

	public void setAddressline2(String addressline2) {
		this.addressline2 = addressline2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public Integer getRecordStatus() {
		return recordStatus;
	}

	public void setRecordStatus(Integer recordStatus) {
		this.recordStatus = recordStatus;
	}


	public Date getPwdExpDate() {
		return pwdExpDate;
	}

	public void setPwdExpDate(Date pwdExpDate) {
		this.pwdExpDate = pwdExpDate;
	}
	

}
