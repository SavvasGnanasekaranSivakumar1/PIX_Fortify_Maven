/**
 * Copyright 2009 by Pearson,
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Pearson ("Confidential Information").
 * You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement
 * you entered into with Pearson.
 *
 * Title		: 	NameAddress.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   8 October, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.inventorychange.dto;
/**
 * LPNameAddress is a data transfer object to store the 
 * Location Party details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class LPNameAddress implements java.io.Serializable {
	private static final long serialVersionUID = -6082720296544336025L;

	private String name1 = null;
	private String name2 = null;
	private String name3 = null;
	
	private String address1 = null;
	private String address2 = null;
	private String address3 = null;
	private String address4 = null;
	
	private String city = null;
	private String stateOrProvince = null;
	private String postalCode = null;
	private String country = null;
	
	private ICOrganisationUnit orgUnit = null;
	/**
	 * Default constructor.
	 */
	public LPNameAddress() {
		super();
	}
	/**
	 * @return the name1
	 */
	public String getName1() {
		return name1;
	}
	/**
	 * @param name1 the name1 to set
	 */
	public void setName1(String name1) {
		this.name1 = name1;
	}
	/**
	 * @return the name2
	 */
	public String getName2() {
		return name2;
	}
	/**
	 * @param name2 the name2 to set
	 */
	public void setName2(String name2) {
		this.name2 = name2;
	}
	/**
	 * @return the name3
	 */
	public String getName3() {
		return name3;
	}
	/**
	 * @param name3 the name3 to set
	 */
	public void setName3(String name3) {
		this.name3 = name3;
	}
	/**
	 * @return the address1
	 */
	public String getAddress1() {
		return address1;
	}
	/**
	 * @param address1 the address1 to set
	 */
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	/**
	 * @return the address2
	 */
	public String getAddress2() {
		return address2;
	}
	/**
	 * @param address2 the address2 to set
	 */
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	/**
	 * @return the address3
	 */
	public String getAddress3() {
		return address3;
	}
	/**
	 * @param address3 the address3 to set
	 */
	public void setAddress3(String address3) {
		this.address3 = address3;
	}
	/**
	 * @return the address4
	 */
	public String getAddress4() {
		return address4;
	}
	/**
	 * @param address4 the address4 to set
	 */
	public void setAddress4(String address4) {
		this.address4 = address4;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the stateOrProvince
	 */
	public String getStateOrProvince() {
		return stateOrProvince;
	}
	/**
	 * @param stateOrProvince the stateOrProvince to set
	 */
	public void setStateOrProvince(String stateOrProvince) {
		this.stateOrProvince = stateOrProvince;
	}
	/**
	 * @return the postalCode
	 */
	public String getPostalCode() {
		return postalCode;
	}
	/**
	 * @param postalCode the postalCode to set
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * @return the orgUnit
	 */
	public ICOrganisationUnit getOrgUnit() {
		return orgUnit;
	}
	/**
	 * @param orgUnit the orgUnit to set
	 */
	public void setOrgUnit(ICOrganisationUnit orgUnit) {
		this.orgUnit = orgUnit;
	}	
}